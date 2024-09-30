package net.dialingspoon.speedcap;

import dev.architectury.injectables.annotations.ExpectPlatform;
import net.dialingspoon.speedcap.gui.SpeedCapMenu;
import net.dialingspoon.speedcap.item.CapRecipe;
import net.dialingspoon.speedcap.item.SpeedCapItem;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeSerializer;

public class PlatformSpecific {
    @ExpectPlatform
    public static ItemStack getItemFromModdedSlots(LivingEntity livingEntity) {
        throw new AssertionError();
    }

    @ExpectPlatform
    public static Object itemExtension() {
        throw new AssertionError();
    }

    @ExpectPlatform
    public static void sendToServer(FriendlyByteBuf buf) {
        throw new AssertionError();
    }

    @ExpectPlatform
    public static SpeedCapItem getItem() {
        throw new AssertionError();
    }

    @ExpectPlatform
    public static MenuType<SpeedCapMenu> getMenu() {
        throw new AssertionError();
    }

    @ExpectPlatform
    public static RecipeSerializer<CapRecipe> getRecipeSerializer() {
        throw new AssertionError();
    }
}
