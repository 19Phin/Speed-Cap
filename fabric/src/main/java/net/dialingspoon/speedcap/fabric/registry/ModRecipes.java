package net.dialingspoon.speedcap.fabric.registry;

import net.dialingspoon.speedcap.SpeedCap;
import net.dialingspoon.speedcap.item.CapRecipe;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.SimpleCraftingRecipeSerializer;

public class ModRecipes {
    public static final RecipeSerializer<CapRecipe> CAP_RECIPE = Registry.register(BuiltInRegistries.RECIPE_SERIALIZER, ResourceLocation.tryBuild(SpeedCap.MOD_ID, "crafting_special_speedcap"), new SimpleCraftingRecipeSerializer<>(CapRecipe::new));
    public static void register() {}
}
