package net.dialingspoon.speedcap.fabric.registry;

import net.dialingspoon.speedcap.SpeedCap;
import net.dialingspoon.speedcap.item.ModMaterials;
import net.dialingspoon.speedcap.item.SpeedCapItem;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;

public class ModItems {
    public static final Item SPEEDCAP = Registry.register(BuiltInRegistries.ITEM, new ResourceLocation(SpeedCap.MOD_ID, "speed_cap"), new SpeedCapItem(ModMaterials.SPEEDCAP, ArmorItem.Type.HELMET, new Item.Properties()));

    public static void register() {}
}
