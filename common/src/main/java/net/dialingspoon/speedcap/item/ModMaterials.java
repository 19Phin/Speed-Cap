package net.dialingspoon.speedcap.item;

import net.dialingspoon.speedcap.SpeedCap;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import org.jetbrains.annotations.NotNull;

import java.util.function.Supplier;

public enum ModMaterials implements ArmorMaterial {
    SPEEDCAP("speed_cap", 15, 2, 9, SoundEvents.ARMOR_EQUIP_IRON, 0f, 0f, () -> Ingredient.of(Items.STRING));

    private final String name;
    private final int durabilityMultiplier;
    private final int protectionAmount;
    private final int enchantability;
    private final SoundEvent soundEvent;
    private final float toughness;
    private final float knockbackResistence;
    private final Supplier<Ingredient> repairMaterial;

    private static final int BASE_DURA = 11;

    ModMaterials(String name, int durabilityMultiplier, int protectionAmount, int enchantability, SoundEvent soundEvent, float toughness, float knockbackResistence, Supplier<Ingredient> repairMaterial) {
        this.name = name;
        this.durabilityMultiplier = durabilityMultiplier;
        this.protectionAmount = protectionAmount;
        this.enchantability = enchantability;
        this.soundEvent = soundEvent;
        this.toughness = toughness;
        this.knockbackResistence = knockbackResistence;
        this.repairMaterial = repairMaterial;
    }

    @Override
    public int getDurabilityForType(ArmorItem.Type type) {
        return BASE_DURA * durabilityMultiplier;
    }

    @Override
    public int getDefenseForType(ArmorItem.Type type) {
        return this.protectionAmount;
    }

    @Override
    public int getEnchantmentValue() {
        return this.enchantability;
    }

    @Override
    @NotNull
    public SoundEvent getEquipSound() {
        return this.soundEvent;
    }

    @Override
    @NotNull
    public Ingredient getRepairIngredient() {
        return this.repairMaterial.get();
    }

    @Override
    @NotNull
    public String getName() {
        return SpeedCap.MOD_ID + ":" + this.name;
    }

    @Override
    public float getToughness() {
        return this.toughness;
    }

    @Override
    public float getKnockbackResistance() {
        return this.knockbackResistence;
    }
}
