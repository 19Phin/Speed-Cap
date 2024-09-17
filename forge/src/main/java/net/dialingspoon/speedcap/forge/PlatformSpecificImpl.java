package net.dialingspoon.speedcap.forge;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotResult;

import java.util.List;

public class PlatformSpecificImpl {
    public static ItemStack getItemFromModdedSlots(LivingEntity livingEntity, Item item) {
        if (CuriosApi.getCuriosInventory(livingEntity).isPresent()) {
            List<SlotResult> stacks = CuriosApi.getCuriosInventory(livingEntity).resolve().get().findCurios(item);
            if (!stacks.isEmpty()) return stacks.get(0).stack();
        }
        return ItemStack.EMPTY;
    }
}
