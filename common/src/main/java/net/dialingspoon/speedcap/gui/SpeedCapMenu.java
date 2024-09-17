package net.dialingspoon.speedcap.gui;

import net.dialingspoon.speedcap.registry.ModItems;
import net.dialingspoon.speedcap.registry.ModMenuTypes;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;

public class SpeedCapMenu extends AbstractContainerMenu {
    private final ItemStack cap;

    public SpeedCapMenu(int i, Inventory playerInventory) {
        super(ModMenuTypes.SPEEDCAP.get(), i);
        cap = findSpeedCap(playerInventory);
    }

    private ItemStack findSpeedCap(Inventory playerInventory) {
        for (ItemStack stack : playerInventory.player.getHandSlots()) {
            if (stack.is(ModItems.SPEEDCAP.get())) return stack;
        }
        return ItemStack.EMPTY;
    }

    @Override
    public boolean stillValid(Player player) {
        return !cap.isEmpty();
    }

    @Override
    public ItemStack quickMoveStack(Player player, int i) {
        return ItemStack.EMPTY;
    }

    public ItemStack getCap() {
        return cap;
    }
}
