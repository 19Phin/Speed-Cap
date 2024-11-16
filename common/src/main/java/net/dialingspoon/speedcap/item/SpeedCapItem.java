package net.dialingspoon.speedcap.item;

import dev.architectury.injectables.annotations.PlatformOnly;
import net.dialingspoon.speedcap.PlatformSpecific;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.DyeableArmorItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.function.Consumer;

public class SpeedCapItem extends DyeableArmorItem {

    public SpeedCapItem(ArmorMaterial material, Type type, Properties settings) {
        super(material, type, settings);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand interactionHand) {
        if(player.isShiftKeyDown()) {
            player.openMenu(new SimpleMenuProvider((i, playerInventory, playerEntity) ->
                    PlatformSpecific.getMenu().create(i, playerInventory), Component.literal("Speed Cap")));
            return InteractionResultHolder.pass(player.getItemInHand(interactionHand));
        }
        return super.use(level, player, interactionHand);
    }

    @Override
    public int getColor(ItemStack arg) {
        CompoundTag compoundTag = arg.getTagElement(TAG_DISPLAY);
        if (compoundTag != null && compoundTag.contains(TAG_COLOR, 99)) {
            return compoundTag.getInt(TAG_COLOR);
        }
        return 16777215;
    }

    /**
     *This @Overrides Item.initializeClient(Consumer<IClientItemExtensions>) through type-erasure shenanigans.
     */
    @PlatformOnly(PlatformOnly.FORGE)
    public void initializeClient(Consumer<Object> consumer) {
        consumer.accept(PlatformSpecific.itemExtension());
    }

    @Override
    public void appendHoverText(ItemStack arg, @Nullable Level arg2, List<Component> list, TooltipFlag arg3) {
        list.add(Component.translatable("tooltip.speedcap.speed_cap.tooltip"));
        super.appendHoverText(arg, arg2, list, arg3);
    }
}
