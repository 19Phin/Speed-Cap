package net.dialingspoon.speedcap.registry;

import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.dialingspoon.speedcap.SpeedCap;
import net.dialingspoon.speedcap.gui.SpeedCapMenu;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.inventory.MenuType;

public class ModMenuTypes {
    public static final DeferredRegister<MenuType<?>> MENUS = DeferredRegister.create(SpeedCap.MOD_ID, Registries.MENU);
    public static final RegistrySupplier<MenuType<SpeedCapMenu>> SPEEDCAP = MENUS.register("speed_cap", () -> new MenuType<>(SpeedCapMenu::new, FeatureFlags.VANILLA_SET));

    public static void register() {
        MENUS.register();
    }
}
