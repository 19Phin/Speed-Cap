package net.dialingspoon.speedcap.neoforge;

import net.dialingspoon.speedcap.SpeedCap;
import net.dialingspoon.speedcap.neoforge.curios.CurioRenderer;
import net.dialingspoon.speedcap.neoforge.networking.Packets;
import net.dialingspoon.speedcap.neoforge.registry.ModCreativeTabs;
import net.dialingspoon.speedcap.neoforge.registry.ModItems;
import net.dialingspoon.speedcap.neoforge.registry.ModMenuTypes;
import net.dialingspoon.speedcap.neoforge.registry.ModRecipes;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModList;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import top.theillusivec4.curios.api.client.CuriosRendererRegistry;

@Mod(SpeedCap.MOD_ID)
public final class SpeedCapNeoForge {
    public static boolean curiosLoaded;
    public SpeedCapNeoForge(IEventBus eventBus) {
        ModItems.register(eventBus);
        ModCreativeTabs.register(eventBus);
        ModMenuTypes.register(eventBus);
        ModRecipes.register(eventBus);
        Packets.registerPackets();

        curiosLoaded = ModList.get().isLoaded("curios");
        if (curiosLoaded) {
            eventBus.addListener(this::clientSetup);
        }
        SpeedCap.init();
    }

    private void clientSetup(final FMLClientSetupEvent evt) {
        CuriosRendererRegistry.register(ModItems.SPEEDCAP.get(), CurioRenderer::new);
    }
}
