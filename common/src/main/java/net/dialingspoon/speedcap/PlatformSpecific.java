package net.dialingspoon.speedcap;

import dev.architectury.injectables.annotations.ExpectPlatform;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;

public class PlatformSpecific {
    @ExpectPlatform
    public static boolean IsInCurios(LivingEntity livingEntity, Item item) {
        throw new AssertionError();
    }
}
