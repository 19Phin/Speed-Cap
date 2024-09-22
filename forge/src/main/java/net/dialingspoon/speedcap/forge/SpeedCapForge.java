package net.dialingspoon.speedcap.forge;

import net.dialingspoon.speedcap.SpeedCap;
import net.dialingspoon.speedcap.forge.curios.CurioRenderer;
import net.dialingspoon.speedcap.forge.networking.Packets;
import net.dialingspoon.speedcap.forge.registry.ModCreativeTabs;
import net.dialingspoon.speedcap.forge.registry.ModItems;
import net.dialingspoon.speedcap.forge.registry.ModMenuTypes;
import net.dialingspoon.speedcap.forge.registry.ModRecipes;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import top.theillusivec4.curios.api.client.CuriosRendererRegistry;

@Mod(SpeedCap.MOD_ID)
public final class SpeedCapForge {
    public static boolean curiosLoaded;
    public SpeedCapForge() {
        final IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();

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
