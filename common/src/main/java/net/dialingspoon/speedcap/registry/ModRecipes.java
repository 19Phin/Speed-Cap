package net.dialingspoon.speedcap.registry;

import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.dialingspoon.speedcap.SpeedCap;
import net.dialingspoon.speedcap.item.CapRecipe;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.SimpleCraftingRecipeSerializer;

public class ModRecipes {
    private static final DeferredRegister<RecipeSerializer<?>> RECIPE_SERIALIZER = DeferredRegister.create(SpeedCap.MOD_ID, Registries.RECIPE_SERIALIZER);
    public static final RegistrySupplier<RecipeSerializer<CapRecipe>> CAP_RECIPE = RECIPE_SERIALIZER.register("crafting_special_speedcap", () -> new SimpleCraftingRecipeSerializer<>(CapRecipe::new));

    public static void register() {
        RECIPE_SERIALIZER.register();
    }
}