package net.dialingspoon.speedcap.item;

import net.dialingspoon.speedcap.PlatformSpecific;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.core.component.DataComponents;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.component.DyedItemColor;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.WoolCarpetBlock;

public class CapRecipe extends CustomRecipe {
    static final NonNullList<Ingredient> recipeItems = NonNullList.withSize(6, Ingredient.EMPTY);
    static {
        recipeItems.set(0, Ingredient.of(Items.STICK));
        reloadCarpetTag();
        recipeItems.set(2, Ingredient.of(Items.STICK));
        recipeItems.set(3, Ingredient.of(Items.REDSTONE));
        recipeItems.set(4, Ingredient.of(Items.IRON_HELMET));
        recipeItems.set(5, Ingredient.of(Items.REDSTONE));
    }

    public CapRecipe(CraftingBookCategory craftingBookCategory) {
        super(craftingBookCategory);
    }

    @Override
    public boolean matches(CraftingInput recipeInput, Level level) {
        reloadCarpetTag();
        for (int i = 0; i <= recipeInput.width() - 3; ++i) {
            for (int j = 0; j <= recipeInput.height() - 2; ++j) {
                if (this.matches(recipeInput, i, j, true)) {
                    return true;
                }
                if (!this.matches(recipeInput, i, j, false)) continue;
                return true;
            }
        }
        return false;
    }

    private boolean matches(CraftingInput recipeInput, int m, int n, boolean bl) {
        for (int i = 0; i < recipeInput.width(); ++i) {
            for (int j = 0; j < recipeInput.height(); ++j) {
                int k = i - m;
                int l = j - n;
                Ingredient ingredient = Ingredient.EMPTY;
                if (k >= 0 && l >= 0 && k < 3 && l < 2) {
                    ingredient = bl ? recipeItems.get(3 - k - 1 + l * 3) : recipeItems.get(k + l * 3);
                }
                if (ingredient.test(recipeInput.getItem(i + j * recipeInput.width()))) continue;
                return false;
            }
        }
        return true;
    }

    @Override
    public ItemStack assemble(CraftingInput recipeInput, HolderLookup.Provider provider) {
        ItemStack carpet = recipeInput.items().stream().filter(m -> m.is(ItemTags.WOOL_CARPETS)).findFirst().get();
        ItemStack cap = new ItemStack(PlatformSpecific.getItem());

        int color = ((WoolCarpetBlock)((BlockItem)carpet.getItem()).getBlock()).getColor().getTextureDiffuseColor();

        cap.set(DataComponents.DYED_COLOR, new DyedItemColor(color, true));
        return cap;
    }

    @Override
    public boolean canCraftInDimensions(int width, int height) {
        return width >= 3 && height >= 3;
    }

    @Override
    public RecipeSerializer<CapRecipe> getSerializer() {
        return PlatformSpecific.getRecipeSerializer();
    }

    public static void reloadCarpetTag() {
        recipeItems.set(1, Ingredient.of(ItemTags.WOOL_CARPETS));
    }
}
