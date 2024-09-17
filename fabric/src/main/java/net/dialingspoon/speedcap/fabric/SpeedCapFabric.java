package net.dialingspoon.speedcap.fabric;

import net.dialingspoon.speedcap.SpeedCap;
import net.fabricmc.api.ModInitializer;

public final class SpeedCapFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        SpeedCap.init();
    }
}
