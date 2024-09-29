package net.dialingspoon.speedcap.fabric;

import net.dialingspoon.speedcap.SpeedCap;
import net.dialingspoon.speedcap.fabric.networking.Packets;
import net.dialingspoon.speedcap.fabric.registry.*;
import net.dialingspoon.speedcap.item.SpeedCapItem;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.world.item.component.DyedItemColor;

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
        ColorProviderRegistry.ITEM.register((stack, i) -> i > 0 ? -1 : DyedItemColor.getOrDefault(stack, SpeedCapItem.DEFAULT_COLOR), ModItems.SPEEDCAP);
        SpeedCap.init();
    }
}
