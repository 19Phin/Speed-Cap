package net.dialingspoon.speedcap.neoforge.registry;

import net.dialingspoon.speedcap.SpeedCap;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.DyedItemColor;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModCreativeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, SpeedCap.MOD_ID);
    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> SPEEDCAP_TAB = CREATIVE_MODE_TABS.register("speedcap_tab", () -> CreativeModeTab.builder().icon(() -> createColoredCap(DyeColor.BLUE))
            .title(Component.translatable("creativetab.speedcap_tab"))
            .displayItems((pParameters, pOutput) -> {
                pOutput.accept(new ItemStack(ModItems.SPEEDCAP.get()));
                createColoredCap(DyeColor.LIGHT_GRAY);
                createColoredCap(DyeColor.GRAY);
                createColoredCap(DyeColor.BLACK);
                createColoredCap(DyeColor.BROWN);
                createColoredCap(DyeColor.RED);
                createColoredCap(DyeColor.ORANGE);
                createColoredCap(DyeColor.YELLOW);
                createColoredCap(DyeColor.LIME);
                createColoredCap(DyeColor.GREEN);
                createColoredCap(DyeColor.CYAN);
                createColoredCap(DyeColor.LIGHT_BLUE);
                createColoredCap(DyeColor.BLUE);
                createColoredCap(DyeColor.PURPLE);
                createColoredCap(DyeColor.MAGENTA);
                createColoredCap(DyeColor.PINK);
            })
            .build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }

    private static ItemStack createColoredCap(DyeColor color) {
        ItemStack cap = new ItemStack(ModItems.SPEEDCAP.get());
        float[] textureDiffuseColors = color.getTextureDiffuseColors();

        int r = (int) (textureDiffuseColors[0] * 255.0F);
        int g = (int) (textureDiffuseColors[1] * 255.0F);
        int b = (int) (textureDiffuseColors[2] * 255.0F);

        int colorhash = (r << 16) | (g << 8) | b;
        cap.set(DataComponents.DYED_COLOR, new DyedItemColor(colorhash, true));
        return cap;
    }
}
