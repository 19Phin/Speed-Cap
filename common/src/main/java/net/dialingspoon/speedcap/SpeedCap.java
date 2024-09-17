package net.dialingspoon.speedcap;

import dev.architectury.platform.Platform;
import dev.architectury.utils.Env;
import net.dialingspoon.speedcap.networking.Packets;
import net.dialingspoon.speedcap.registry.ModKeys;
import net.dialingspoon.speedcap.registry.ModItems;
import net.dialingspoon.speedcap.registry.ModMenuTypes;

public final class SpeedCap {
    public static final String MOD_ID = "speedcap";
    public static void init() {
        ModItems.register();
        ModMenuTypes.register();
        Packets.registerPackets();
        if (Platform.getEnvironment() == Env.CLIENT) {
            ModKeys.register();;
        }
    }
}
