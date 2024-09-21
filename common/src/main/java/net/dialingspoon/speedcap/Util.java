package net.dialingspoon.speedcap;

import net.dialingspoon.speedcap.interfaces.EntityInterface;
import net.minecraft.client.Minecraft;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.Entity;
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
            ItemStack curiosItem = PlatformSpecific.getItemFromModdedSlots(entity, PlatformSpecific.getItem());
            if (curiosItem != null && !curiosItem.isEmpty()) {
                item = curiosItem;
            }
        }

        if (item != null) {
            EntityInterface entityInterface = (EntityInterface) entity;
            if (entityInterface.getSpeedcap$capStack() != item) {
                entityInterface.setSpeedcap$capStack(item);
                if (!item.getOrCreateTag().contains("SpeedCap")) {
                    CompoundTag tag = new CompoundTag();
                    tag.putFloat("moveSpeed", 4.8f);
                    tag.putFloat("mineSpeed", 4);
                    tag.putBoolean("moveActive", true);
                    tag.putBoolean("modifiable", false);
                    tag.putBoolean("jump", true);
                    tag.putBoolean("stoponadime", false);
                    tag.putBoolean("mineActive", true);
                    tag.putBoolean("creative", true);
                    item.getTag().put("SpeedCap", tag);
                }
                entityInterface.setSpeedcap$data((CompoundTag) item.getOrCreateTag().get("SpeedCap"));
            }
        }

        return item;
    }

    public static boolean isClientPlayer(LivingEntity entity) {
        if (entity instanceof Player player) {
            return player.equals(Minecraft.getInstance().player);
        }
        return false;
    }
}
