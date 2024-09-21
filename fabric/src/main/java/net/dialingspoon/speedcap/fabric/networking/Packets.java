package net.dialingspoon.speedcap.fabric.networking;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.network.FriendlyByteBuf;

public class Packets {

    public static void registerPackets() {
        ServerPlayNetworking.registerGlobalReceiver(ServerboundCapSettingsPacket.ID, ServerboundCapSettingsPacket::receive);
    }

    public static void sendToServer(FriendlyByteBuf buf) {
        ClientPlayNetworking.send(ServerboundCapSettingsPacket.ID, buf);
    }
}
