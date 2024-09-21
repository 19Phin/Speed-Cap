package net.dialingspoon.speedcap.forge.registry;

import net.dialingspoon.speedcap.SpeedCap;
import net.dialingspoon.speedcap.gui.SpeedCapMenu;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModMenuTypes {
    public static final DeferredRegister<MenuType<?>> MENUS = DeferredRegister.create(ForgeRegistries.MENU_TYPES, SpeedCap.MOD_ID);
    public static final RegistryObject<MenuType<SpeedCapMenu>> SPEEDCAP = MENUS.register("speed_cap", () -> new MenuType<>(SpeedCapMenu::new, FeatureFlags.VANILLA_SET));

    public static void register(IEventBus eventBus) {
        MENUS.register(eventBus);
    }
}
