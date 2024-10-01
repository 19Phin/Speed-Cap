package net.dialingspoon.speedcap.interfaces;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ItemStack;

public interface EntityInterface {

    boolean speedcap$isSpeeding();

    void speedcap$couldSpeed(boolean b);

    void speedcap$setSpeeding(boolean bl);

    void speedcap$moving(boolean b);

    CompoundTag speedcap$getData();

    void speedcap$setData(CompoundTag tag);

    ItemStack speedcap$getCapStack();

    void speedcap$setCapStack(ItemStack stack);
}
