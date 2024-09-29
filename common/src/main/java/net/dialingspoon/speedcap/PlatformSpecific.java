package net.dialingspoon.speedcap;

import dev.architectury.injectables.annotations.ExpectPlatform;
import net.dialingspoon.speedcap.gui.SpeedCapMenu;
import net.dialingspoon.speedcap.item.CapRecipe;
import net.dialingspoon.speedcap.item.CapSettingsComponent;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.Item;
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
    public static void sendToServer(float moveSpeed, float mineSpeed, boolean moveActive, boolean modifiable,
                                    boolean jump, boolean stoponadime, boolean mineActive, boolean creative) {
        throw new AssertionError();
    }

    @ExpectPlatform
    public static Item getItem() {
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

    @ExpectPlatform
    public static DataComponentType<CapSettingsComponent> getDataComponent() {
        throw new AssertionError();
    }
}
