package net.dialingspoon.speedcap.networking;

import dev.architectury.networking.NetworkManager;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;

public class Packets {

    public static void registerPackets() {
        NetworkManager.registerReceiver(NetworkManager.Side.C2S, ServerboundCapSettingsPacket.ID, (buf, context) -> {
            ServerboundCapSettingsPacket packet = new ServerboundCapSettingsPacket(buf);
            context.queue(() -> ServerboundCapSettingsPacket.handle(packet, (ServerPlayer) context.getPlayer()));
        });
    }

    public static void sendToServer(FriendlyByteBuf buf) {
        NetworkManager.sendToServer(ServerboundCapSettingsPacket.ID, buf);
    }

}
