package net.dialingspoon.speedcap.registry;

import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.dialingspoon.speedcap.SpeedCap;
import net.dialingspoon.speedcap.item.SpeedCapItem;
import net.minecraft.client.color.item.ItemColors;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(SpeedCap.MOD_ID, Registries.ITEM);
    public static final RegistrySupplier<Item> SPEEDCAP = ITEMS.register("speed_cap", () -> new SpeedCapItem(ModMaterials.SPEEDCAP, ArmorItem.Type.HELMET, new Item.Properties().arch$tab(CreativeModeTabs.COMBAT)));

    public static void register() {
        ITEMS.register();
    }
}
