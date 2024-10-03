package net.dialingspoon.speedcap.neoforge.registry;

import net.dialingspoon.speedcap.SpeedCap;
import net.dialingspoon.speedcap.gui.SpeedCapMenu;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.inventory.MenuType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModMenuTypes {
    public static final DeferredRegister<MenuType<?>> MENUS = DeferredRegister.create(Registries.MENU, SpeedCap.MOD_ID);
    public static final DeferredHolder<MenuType<?>, MenuType<SpeedCapMenu>> SPEEDCAP = MENUS.register("speed_cap", () -> {
        MenuType<SpeedCapMenu> menu = new MenuType<>(SpeedCapMenu::new, FeatureFlags.VANILLA_SET);
        if (FMLEnvironment.dist == Dist.CLIENT) {
            MenuScreens.register(menu, SpeedCapScreen::new);
        }
        return menu;
    });

    public static void register(IEventBus eventBus) {
        MENUS.register(eventBus);
    }
}
