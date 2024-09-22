package net.dialingspoon.speedcap.item;

import net.dialingspoon.speedcap.PlatformSpecific;
import net.minecraft.core.NonNullList;
import net.minecraft.core.RegistryAccess;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.inventory.CraftingContainer;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.CraftingBookCategory;
import net.minecraft.world.item.crafting.CustomRecipe;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
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
    public boolean matches(CraftingContainer craftingContainer, Level arg2) {
        reloadCarpetTag();
        for (int i = 0; i <= craftingContainer.getWidth() - 3; ++i) {
            for (int j = 0; j <= craftingContainer.getHeight() - 2; ++j) {
                if (this.matches(craftingContainer, i, j, true)) {
                    return true;
                }
                if (!this.matches(craftingContainer, i, j, false)) continue;
                return true;
            }
        }
        return false;
    }

    private boolean matches(CraftingContainer craftingContainer, int m, int n, boolean bl) {
        for (int i = 0; i < craftingContainer.getWidth(); ++i) {
            for (int j = 0; j < craftingContainer.getHeight(); ++j) {
                int k = i - m;
                int l = j - n;
                Ingredient ingredient = Ingredient.EMPTY;
                if (k >= 0 && l >= 0 && k < 3 && l < 2) {
                    ingredient = bl ? recipeItems.get(3 - k - 1 + l * 3) : recipeItems.get(k + l * 3);
                }
                if (ingredient.test(craftingContainer.getItem(i + j * craftingContainer.getWidth()))) continue;
                return false;
            }
        }
        return true;
    }

    @Override
    public ItemStack assemble(CraftingContainer recipeInputInventory, RegistryAccess wrapperLookup) {
        ItemStack carpet = recipeInputInventory.getItems().stream().filter(m -> m.is(ItemTags.WOOL_CARPETS)).findFirst().get();
        ItemStack cap = new ItemStack(PlatformSpecific.getItem());

        float[] textureDiffuseColors = ((WoolCarpetBlock)((BlockItem)carpet.getItem()).getBlock()).getColor().getTextureDiffuseColors();

        int r = (int) (textureDiffuseColors[0] * 255.0F);
        int g = (int) (textureDiffuseColors[1] * 255.0F);
        int b = (int) (textureDiffuseColors[2] * 255.0F);

        int color = (r << 16) | (g << 8) | b;

        ((SpeedCapItem)PlatformSpecific.getItem()).setColor(cap, color);
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
