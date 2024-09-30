package net.dialingspoon.speedcap.fabric;

import net.dialingspoon.speedcap.SpeedCap;
import net.dialingspoon.speedcap.fabric.networking.Packets;
import net.dialingspoon.speedcap.fabric.registry.*;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.api.FabricLoader;

public final class SpeedCapFabric implements ModInitializer {
    public static boolean trinketsLoaded;
    @Override
    public void onInitialize() {
        ModItems.register();
        ModMenuTypes.register();
        ModRecipes.register();
        Packets.registerPackets();
        ModDataComponents.register();
        ModKeys.register();

        trinketsLoaded = FabricLoader.getInstance().isModLoaded("trinkets");
        SpeedCap.init();
    }
}
