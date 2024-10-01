package net.dialingspoon.speedcap;

import net.dialingspoon.speedcap.interfaces.EntityInterface;
import net.minecraft.client.Minecraft;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

public class Util {

    public static ItemStack getActiveCap(LivingEntity entity) {
        ItemStack head = entity.getSlot(103).get();
        ItemStack item = ItemStack.EMPTY;

        if (head.is(PlatformSpecific.getItem())) {
            item = head;
        } else {
            ItemStack curiosItem = PlatformSpecific.getItemFromModdedSlots(entity);
            if (curiosItem != null && !curiosItem.isEmpty()) {
                item = curiosItem;
            }
        }

        if (item != null) {
            EntityInterface entityInterface = (EntityInterface) entity;
            if (entityInterface.speedcap$getCapStack() != item) {
                entityInterface.speedcap$setCapStack(item);

                entityInterface.speedcap$setData(item.get(PlatformSpecific.getDataComponent()));
            }
        }

        return item;
    }

    public static boolean shouldHandleSelf(LivingEntity entity) {
        if (entity.level() instanceof ServerLevel) {
            return true;
        } else if (entity instanceof Player player) {
            return player.equals(Minecraft.getInstance().player);
        }
        return false;
    }
}
