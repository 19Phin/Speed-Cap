package net.dialingspoon.speedcap.item;

import net.dialingspoon.speedcap.registry.ModMenuTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.DyeableArmorItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class SpeedCapItem extends DyeableArmorItem {

    public SpeedCapItem(ArmorMaterial material, Type type, Properties settings) {
        super(material, type, settings);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand interactionHand) {
        if(player.isShiftKeyDown()) {
            player.openMenu(new SimpleMenuProvider((i, playerInventory, playerEntity) ->
                    ModMenuTypes.SPEEDCAP.get().create(i, playerInventory), Component.literal("Speed Cap")));
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

}
