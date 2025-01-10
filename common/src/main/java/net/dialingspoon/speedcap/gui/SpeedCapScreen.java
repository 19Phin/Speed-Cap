package net.dialingspoon.speedcap.gui;

import com.google.common.collect.Lists;
import io.netty.buffer.Unpooled;
import net.dialingspoon.speedcap.PlatformSpecific;
import net.dialingspoon.speedcap.SpeedCap;
import net.dialingspoon.speedcap.Util;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.AbstractButton;
import net.minecraft.client.gui.components.AbstractSliderButton;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.components.Tooltip;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Inventory;

import java.util.List;

@Environment(value=EnvType.CLIENT)
public class SpeedCapScreen extends AbstractContainerScreen<SpeedCapMenu> {
    private static final ResourceLocation TEXTURE_LOCATION = ResourceLocation.tryBuild(SpeedCap.MOD_ID,"textures/gui/cap_menu.png");
    private static final List<CapSlider> sliderWidgets = Lists.newArrayList();
    private static final List<CapScreenButton> buttonWidgets = Lists.newArrayList();
    private static CapResetButton resetWidget;
    private boolean isMovementTabActive = true;

    public SpeedCapScreen(final SpeedCapMenu capMenu, Inventory inventory, Component component) {
        super(capMenu, inventory, component);
        this.imageWidth = 150;
        this.imageHeight = 150;
    }

    @Override
    protected void init() {
        super.init();
        sliderWidgets.clear();
        buttonWidgets.clear();

        addWidget(new CapCancelButton(this.leftPos + this.imageWidth - 13, this.topPos + 4));
        addWidget(new CapTabButton(this.leftPos - 22, this.topPos + 10, MobEffects.MOVEMENT_SPEED, Component.translatable("item.speedcap.gui.moveTab"), true));
        addWidget(new CapTabButton(this.leftPos - 22, this.topPos + 40, MobEffects.DIG_SPEED, Component.translatable("item.speedcap.gui.mineTab"), false));

        initControls();
    }

    private void initControls() {
        CompoundTag tag = Util.getOrCreateTag(this.getMenu().getCap());

        addWidget(new CapResetButton(this.leftPos + 105, this.topPos - 19, Component.translatable("item.speedcap.gui.reset")));

        addWidget(new CapSlider(this.leftPos + this.imageWidth/2 - 31, this.topPos - 18, tag.getFloat("moveSpeed"), true));
        addWidget(new CapSlider(this.leftPos + this.imageWidth/2 - 31, this.topPos - 18, tag.getFloat("mineSpeed"), false));

        addWidget(new CapScreenButton(this.leftPos + 35, this.topPos + 28, Component.translatable("item.speedcap.gui.moveActive"), tag.getBoolean("moveActive"), true, Component.translatable("item.speedcap.gui.moveActiveDesc")));
        addWidget(new CapScreenButton(this.leftPos + 95, this.topPos + 28, Component.translatable("item.speedcap.gui.modifiable"), tag.getBoolean("modifiable"), true, Component.translatable("item.speedcap.gui.modifiableDesc")));
        addWidget(new CapScreenButton(this.leftPos + 35, this.topPos + 80, Component.translatable("item.speedcap.gui.jump"), tag.getBoolean("jump"), true, Component.translatable("item.speedcap.gui.jumpDesc")));
        addWidget(new CapScreenButton(this.leftPos + 95, this.topPos + 80, Component.translatable("item.speedcap.gui.stoponadime"), tag.getBoolean("stoponadime"), true, Component.translatable("item.speedcap.gui.stoponadimeDesc")));

        addWidget(new CapScreenButton(this.leftPos + 35, this.topPos + 54, Component.translatable("item.speedcap.gui.mineActive"), tag.getBoolean("mineActive"), false, Component.translatable("item.speedcap.gui.mineActiveDesc")));
        addWidget(new CapScreenButton(this.leftPos + 95, this.topPos + 54, Component.translatable("item.speedcap.gui.creative"), tag.getBoolean("creative"), false, Component.translatable("item.speedcap.gui.creativeDesc")));

        updateVisibility();
    }

    private void addWidget(AbstractWidget widget) {
        this.addRenderableWidget(widget);
        if (widget instanceof CapScreenButton button) {
            buttonWidgets.add(button);
        } else if(widget instanceof CapSlider slider) {
            sliderWidgets.add(slider);
        } else if (widget instanceof CapResetButton reset) {
            resetWidget = reset;
        }
    }

    private void updateVisibility() {
        for (VisibilityToggleable widget : buttonWidgets) {
            widget.setVisible(isMovementTabActive == widget.isMovementRelated());
        }
        for (VisibilityToggleable widget : sliderWidgets) {
            widget.setVisible(isMovementTabActive == widget.isMovementRelated());
        }
    }

    @Override
    public boolean mouseReleased(double d, double e, int i) {
        resetWidget.onRelease();
        sliderWidgets.forEach(CapSlider::onRelease);
        return super.mouseReleased(d, e, i);
    }

    @Override
    protected void renderBg(GuiGraphics guiGraphics, float partialTicks, int mouseX, int mouseY) {
        guiGraphics.blit(TEXTURE_LOCATION, this.leftPos, this.topPos-25, 0, 0, this.imageWidth, this.imageHeight);
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(guiGraphics);
        super.render(guiGraphics, mouseX, mouseY, partialTicks);
    }

    @Override
    protected void renderLabels(GuiGraphics guiGraphics, int mouseX, int mouseY) {}

    @Override
    public void onClose() {
        FriendlyByteBuf buf = new FriendlyByteBuf(Unpooled.buffer());
        for (CapSlider slider : sliderWidgets) {
            buf.writeFloat(Math.max(slider.getSpeed() - .5f, 0.1f));
        }
        for (CapScreenButton button : buttonWidgets) {
            buf.writeBoolean(button.isSelected());
        }
        PlatformSpecific.sendToServer(buf);
        super.onClose();
    }

    public void switchTab(boolean movementTab) {
        isMovementTabActive = movementTab;
        updateVisibility();
    }

    @Override
    protected void containerTick() {
        sliderWidgets.forEach(CapSlider::tick);
    }

    @Override
    public boolean mouseDragged(double d, double e, int i, double f, double g) {
        sliderWidgets.stream().filter(w -> w.movementRelated == isMovementTabActive).forEach((capSlider) -> capSlider.onDrag(d, e ,f ,g));
        return super.mouseDragged(d, e, i, f, g);
    }



    interface VisibilityToggleable {
        boolean isMovementRelated();
        default void setVisible(boolean visible) {}
    }


    @Environment(value=EnvType.CLIENT)
    class CapTabButton extends AbstractButton implements VisibilityToggleable {
        private final boolean movementRelated;
        private final TextureAtlasSprite sprite;

        public CapTabButton(int x, int y, MobEffect effect, Component name, boolean movementRelated) {
            super(x, y, 25, 25, name);
            this.movementRelated = movementRelated;
            this.sprite = Minecraft.getInstance().getMobEffectTextures().get(effect);
        }

        @Override
        public void onPress() {
            SpeedCapScreen.this.switchTab(this.movementRelated);
        }

        @Override
        public void renderWidget(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
            guiGraphics.blit(SpeedCapScreen.TEXTURE_LOCATION, this.getX(), this.getY(), this.movementRelated == isMovementTabActive ? 0 : 25, 150, 25, 25);
            guiGraphics.blit(this.getX() + 4, this.getY() + 3, 0, 18, 18, this.sprite);
        }

        @Override
        protected void updateWidgetNarration(NarrationElementOutput narrationElementOutput) {
            this.defaultButtonNarrationText(narrationElementOutput);
        }

        @Override
        public boolean isMovementRelated() {
            return movementRelated;
        }
    }


    @Environment(value=EnvType.CLIENT)
    static class CapSlider extends AbstractSliderButton implements VisibilityToggleable {
        private float speedValue;
        private final boolean movementRelated;
        private int tick;
        private int moving;
        private int increase;

        public CapSlider(int x, int y, float initialSpeed, boolean movementRelated) {
            super(x, y, 60, 20, Component.literal(String.format("%.1f", initialSpeed)), getFirstValue(initialSpeed));
            this.speedValue = initialSpeed + .5f;
            this.movementRelated = movementRelated;
            this.setTooltip(Tooltip.create(Component.translatable("item.speedcap.gui.bps")));
        }

        private static float getFirstValue(float value) {
            float decimal = value - (int) value;
            return (decimal < 0.5f) ? decimal + 0.5f : decimal - 0.5f;
        }


        @Override
        protected void updateMessage() {
            this.setMessage(Component.literal(String.format("%.1f", Math.max(speedValue - .5f, 0.1f))));
        }

        @Override
        protected void applyValue() {
            float change = Float.parseFloat(String.format("%.1f", value));
            change = Math.min(change, 0.9f);

            if (value >= 1) {
                moving = 1;
            } else if (value <= 0) {
                moving = -1;
            } else {
                moving = 0;
                tick = 0;
                increase = 0;
            }

            speedValue = ((int) speedValue) + change;
        }

        public void reset() {
            speedValue = movementRelated ? 5.3f :  5.0f;
            value = movementRelated ? .3 : 0;
            moving = 0;
            updateMessage();
        }

        public void onRelease() {
            if (moving != 0) {
                moving = 0;
                tick = 0;
                increase = 0;
            }
            if (speedValue <= 0.4f) {
                value = 0.6f;
                speedValue = 0.4f;
            }
        }

        public void onDrag(double d, double e, double f, double g) {
            if (this.isHovered) {
                super.onDrag(d, e, f, g);
            }
        }

        @Override
        public boolean isMovementRelated() {
            return movementRelated;
        }

        @Override
        public void setVisible(boolean visible) {
            this.visible = visible;
        }

        public void tick() {
            tick++;
            if (tick >= 10) {
                increase++;
                if (increase >= 10) {
                    moving *= 10;
                    increase = 0;
                }

                if (moving > 0 && speedValue + moving > 1000000) {
                    speedValue = 1000000.9f;
                } else if (moving < 0 && speedValue + moving < 0) {
                    speedValue = 0;
                } else {
                    speedValue += moving;
                }

                updateMessage();
                tick = 0;
            }
        }

        public float getSpeed() {
            return speedValue;
        }
    }


    @Environment(value=EnvType.CLIENT)
    class CapResetButton extends AbstractButton {
        private boolean selected = false;

        protected CapResetButton(int x, int y, Component name) {
            super(x, y, 4, 6, name);
            this.setTooltip(Tooltip.create(name, null));
        }

        @Override
        public void onPress() {
            this.selected = true;
            sliderWidgets.stream()
                    .filter(w -> w.isMovementRelated() == SpeedCapScreen.this.isMovementTabActive)
                    .findFirst().get().reset();
        }

        public void onRelease() {
            this.selected = false;
        }
        @Override
        public void renderWidget(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
            int iconOffset = this.selected ? 59 :  59 + this.width;
            guiGraphics.blit(SpeedCapScreen.TEXTURE_LOCATION, this.getX(), this.getY(), iconOffset, 162, this.width, this.height);
        }

        @Override
        public void updateWidgetNarration(NarrationElementOutput narrationElementOutput) {
            this.defaultButtonNarrationText(narrationElementOutput);
        }
    }


    @Environment(value=EnvType.CLIENT)
    class CapScreenButton extends AbstractButton implements VisibilityToggleable {
        private boolean selected;
        private final Component name;
        private final boolean movementRelated;

        protected CapScreenButton(int x, int y, Component name, boolean selected, boolean movementRelated, Component description) {
            super(x, y, 22, 12, name);
            this.name = name;
            this.selected = selected;
            this.movementRelated = movementRelated;
            this.setTooltip(Tooltip.create(description, null));
        }

        @Override
        public void onPress() {
            this.selected = !this.selected;
        }
        @Override
        public void renderWidget(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
            int iconOffset = this.selected ? 50 :  50 + this.width;
            guiGraphics.blit(SpeedCapScreen.TEXTURE_LOCATION, this.getX(), this.getY(), iconOffset, 150, this.width, this.height);
            guiGraphics.drawCenteredString(SpeedCapScreen.this.font, name, this.getX() + this.width / 2, this.getY() - 10, 0xffffff);
        }

        public boolean isSelected() {
            return this.selected;
        }

        @Override
        public void updateWidgetNarration(NarrationElementOutput narrationElementOutput) {
            this.defaultButtonNarrationText(narrationElementOutput);
        }

        @Override
        public boolean isMovementRelated() {
            return movementRelated;
        }

        @Override
        public void setVisible(boolean visible) {
            this.visible = visible;
        }
    }


    @Environment(value=EnvType.CLIENT)
    class CapCancelButton extends AbstractButton {
        public CapCancelButton(int x, int y) {
            super(x, y, 9, 9, CommonComponents.GUI_CANCEL);
        }

        @Override
        public void onPress() {
            onClose();
        }

        @Override
        public void renderWidget(GuiGraphics guiGraphics, int i, int j, float f) {
            guiGraphics.blit(TEXTURE_LOCATION, this.getX(), this.getY(), 50, 162, width, height);
        }

        @Override
        protected void updateWidgetNarration(NarrationElementOutput narrationElementOutput) {
            this.defaultButtonNarrationText(narrationElementOutput);
        }
    }
}
