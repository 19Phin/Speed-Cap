package net.dialingspoon.speedcap;

import net.dialingspoon.speedcap.registry.KeyRegistry;
import net.dialingspoon.speedcap.registry.ModItems;

public final class SpeedCap {
    public static final String MOD_ID = "speedcap";
    public static void init() {
        ModItems.register();
        KeyRegistry.register();
    }
}
