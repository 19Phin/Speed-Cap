package net.dialingspoon.speedcap.neoforge.registry;

import net.dialingspoon.speedcap.SpeedCap;
import net.dialingspoon.speedcap.item.ModMaterials;
import net.dialingspoon.speedcap.item.SpeedCapItem;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(SpeedCap.MOD_ID);
    public static final DeferredItem<SpeedCapItem> SPEEDCAP = ITEMS.register("speed_cap", () -> new SpeedCapItem(ModMaterials.SPEEDCAP, ArmorItem.Type.HELMET, new Item.Properties()));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
