package net.dialingspoon.speedcap.forge;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import top.theillusivec4.curios.api.CuriosApi;

public class PlatformSpecificImpl {
    public static boolean IsInCurios(LivingEntity livingEntity, Item item) {
        return CuriosApi.getCuriosInventory(livingEntity).isPresent() && CuriosApi.getCuriosInventory(livingEntity).resolve().get().isEquipped(item);
    }
}
