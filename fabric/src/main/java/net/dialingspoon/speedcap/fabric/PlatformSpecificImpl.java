package net.dialingspoon.speedcap.fabric;

import dev.emi.trinkets.api.SlotReference;
import dev.emi.trinkets.api.TrinketsApi;
import net.minecraft.util.Tuple;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import java.util.List;

public class PlatformSpecificImpl {
    public static ItemStack getItemFromModdedSlots(LivingEntity livingEntity, Item item) {
        if (TrinketsApi.getTrinketComponent(livingEntity).isPresent()) {
            List<Tuple<SlotReference, ItemStack>> stacks = TrinketsApi.getTrinketComponent(livingEntity).get().getEquipped(item);
            if (!stacks.isEmpty()) return stacks.get(0).getB();
        }
        return ItemStack.EMPTY;
    }
}
