package net.dialingspoon.speedcap.interfaces;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ItemStack;

public interface EntityInterface {

    boolean speedcap$isSpeeding();

    void speedcap$setSpeeding(boolean b);

    void speedcap$couldSpeed(boolean b);

    void speedcap$moving(boolean b);

    CompoundTag getSpeedcap$data();

    void setSpeedcap$data(CompoundTag tag);

    ItemStack getSpeedcap$capStack();

    void setSpeedcap$capStack(ItemStack stack);
}
