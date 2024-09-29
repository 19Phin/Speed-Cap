package net.dialingspoon.speedcap.fabric.registry;

import net.dialingspoon.speedcap.SpeedCap;
import net.dialingspoon.speedcap.item.ModMaterials;
import net.dialingspoon.speedcap.item.SpeedCapItem;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ArmorItem;

public class ModItems {
    public static final SpeedCapItem SPEEDCAP = Registry.register(BuiltInRegistries.ITEM, ResourceLocation.tryBuild(SpeedCap.MOD_ID, "speed_cap"), new SpeedCapItem(ModMaterials.SPEEDCAP, ArmorItem.Type.HELMET, SpeedCapItem.DEFAULT_PROPERTIES));

    public static void register() {}
}
