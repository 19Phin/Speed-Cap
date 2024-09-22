package net.dialingspoon.speedcap.forge.registry;

import net.dialingspoon.speedcap.SpeedCap;
import net.dialingspoon.speedcap.item.CapRecipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.SimpleCraftingRecipeSerializer;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModRecipes {
    private static final DeferredRegister<RecipeSerializer<?>> RECIPE_SERIALIZER = DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, SpeedCap.MOD_ID);
    public static final RegistryObject<RecipeSerializer<CapRecipe>> CAP_RECIPE = RECIPE_SERIALIZER.register("crafting_special_speedcap", () -> new SimpleCraftingRecipeSerializer<>(CapRecipe::new));

    public static void register(IEventBus eventBus) {
        RECIPE_SERIALIZER.register(eventBus);
    }
}
