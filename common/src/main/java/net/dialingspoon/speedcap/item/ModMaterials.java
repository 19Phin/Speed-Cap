package net.dialingspoon.speedcap.item;

import net.dialingspoon.speedcap.SpeedCap;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.function.Supplier;

public class ModMaterials {
    public static final Holder<ArmorMaterial> SPEEDCAP = register("speed_cap", 15, 2, 9, SoundEvents.ARMOR_EQUIP_IRON, 0f, 0f, () -> Ingredient.of(Items.STRING));

    private static Holder<ArmorMaterial> register(String name, int durabilityMultiplier, int protectionAmount, int enchantability, Holder<SoundEvent> soundEvent, float toughness, float knockbackResistence, Supplier<Ingredient> repairMaterial) {
        EnumMap<ArmorItem.Type, Integer> defenseMap = new EnumMap<>(ArmorItem.Type.class);
        for (ArmorItem.Type type : ArmorItem.Type.values()) {
            defenseMap.put(type, protectionAmount);
        }
        List<ArmorMaterial.Layer> layers = new ArrayList<>();
        layers.add(new ArmorMaterial.Layer(ResourceLocation.tryBuild(SpeedCap.MOD_ID, name), "", true));
        layers.add(new ArmorMaterial.Layer(ResourceLocation.tryBuild(SpeedCap.MOD_ID, name), "_overlay", false));
        return Registry.registerForHolder(BuiltInRegistries.ARMOR_MATERIAL, ResourceLocation.tryBuild(SpeedCap.MOD_ID, name),
                new ArmorMaterial(defenseMap, enchantability, soundEvent, repairMaterial, layers, toughness, knockbackResistence)
        );
    }
}
