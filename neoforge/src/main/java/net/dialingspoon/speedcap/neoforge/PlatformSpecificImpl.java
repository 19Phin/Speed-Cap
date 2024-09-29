package net.dialingspoon.speedcap.neoforge;

import net.dialingspoon.speedcap.gui.SpeedCapMenu;
import net.dialingspoon.speedcap.item.CapRecipe;
import net.dialingspoon.speedcap.item.CapSettingsComponent;
import net.dialingspoon.speedcap.neoforge.client.ItemExtension;
import net.dialingspoon.speedcap.neoforge.networking.PacketHandler;
import net.dialingspoon.speedcap.neoforge.networking.ServerboundCapSettingsPacket;
import net.dialingspoon.speedcap.neoforge.registry.ModDataComponents;
import net.dialingspoon.speedcap.neoforge.registry.ModItems;
import net.dialingspoon.speedcap.neoforge.registry.ModMenuTypes;
import net.dialingspoon.speedcap.neoforge.registry.ModRecipes;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeSerializer;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotResult;

import java.util.List;

public class PlatformSpecificImpl {
    public static ItemStack getItemFromModdedSlots(LivingEntity livingEntity) {
        if (SpeedCapNeoForge.curiosLoaded) {
            if (CuriosApi.getCuriosInventory(livingEntity).isPresent()) {
                List<SlotResult> stacks = CuriosApi.getCuriosInventory(livingEntity).get().findCurios(ModItems.SPEEDCAP.get());
                if (!stacks.isEmpty()) return stacks.get(0).stack();
            }
        }
        return ItemStack.EMPTY;
    }

    public static Object itemExtension() {
        return new ItemExtension();
    }

    public static void sendToServer(float moveSpeed, float mineSpeed, boolean moveActive, boolean modifiable,
                                    boolean jump, boolean stoponadime, boolean mineActive, boolean creative) {
        PacketHandler.sendToServer(new ServerboundCapSettingsPacket(moveSpeed, mineSpeed, moveActive, modifiable, jump, stoponadime, mineActive, creative));
    }

    public static Item getItem() {
        return ModItems.SPEEDCAP.get();
    }

    public static MenuType<SpeedCapMenu> getMenu() {
        return ModMenuTypes.SPEEDCAP.get();
    }

    public static RecipeSerializer<CapRecipe> getRecipeSerializer() {
        return ModRecipes.CAP_RECIPE.get();
    }

    public static DataComponentType<CapSettingsComponent> getDataComponent() {
        return ModDataComponents.SPEEDCAP_DATA.get();
    }
}
