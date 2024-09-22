package net.dialingspoon.speedcap.forge.registry;

import net.dialingspoon.speedcap.SpeedCap;
import net.dialingspoon.speedcap.item.ModMaterials;
import net.dialingspoon.speedcap.item.SpeedCapItem;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, SpeedCap.MOD_ID);
    public static final RegistryObject<SpeedCapItem> SPEEDCAP = ITEMS.register("speed_cap", () -> new SpeedCapItem(ModMaterials.SPEEDCAP, ArmorItem.Type.HELMET, new Item.Properties()));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
