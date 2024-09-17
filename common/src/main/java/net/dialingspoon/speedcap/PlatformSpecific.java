package net.dialingspoon.speedcap;

import dev.architectury.injectables.annotations.ExpectPlatform;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class PlatformSpecific {
    @ExpectPlatform
    public static ItemStack getItemFromModdedSlots(LivingEntity livingEntity, Item item) {
        throw new AssertionError();
    }
}
