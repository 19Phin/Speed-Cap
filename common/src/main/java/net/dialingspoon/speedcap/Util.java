package net.dialingspoon.speedcap;

import dev.architectury.utils.EnvExecutor;
import net.dialingspoon.speedcap.registry.ModItems;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

public class Util {

    public static ItemStack getActiveCap(LivingEntity entity) {
        ItemStack head = entity.getSlot(103).get();
        ItemStack item = ItemStack.EMPTY;

        if (head.is(ModItems.SPEEDCAP.get())) {
            item = head;
        } else {
            ItemStack curiosItem = PlatformSpecific.getItemFromModdedSlots(entity, ModItems.SPEEDCAP.get());
            if (curiosItem != null && !curiosItem.isEmpty()) {
                item = curiosItem;
            }
        }

        return item;
    }

    public static boolean isClientPlayer(LivingEntity entity) {
        if (entity instanceof Player player) {
            return EnvExecutor.getEnvSpecific(
                    () -> () -> player instanceof LocalPlayer,
                    () -> () -> false
            );
        }
        return false;
    }
}
