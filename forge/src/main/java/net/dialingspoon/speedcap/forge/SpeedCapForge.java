package net.dialingspoon.speedcap.forge;

import dev.architectury.platform.forge.EventBuses;
import net.dialingspoon.speedcap.SpeedCap;
import net.dialingspoon.speedcap.forge.curios.CurioRenderer;
import net.dialingspoon.speedcap.registry.ModItems;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import top.theillusivec4.curios.api.client.CuriosRendererRegistry;

@Mod(SpeedCap.MOD_ID)
public final class SpeedCapForge {
    public SpeedCapForge() {
        EventBuses.registerModEventBus(SpeedCap.MOD_ID, FMLJavaModLoadingContext.get().getModEventBus());
        SpeedCap.init();

        final IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();
        eventBus.addListener(this::clientSetup);
    }

    private void clientSetup(final FMLClientSetupEvent evt) {
        CuriosRendererRegistry.register(ModItems.SPEEDCAP.get(), CurioRenderer::new);
    }
}
