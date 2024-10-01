package net.dialingspoon.speedcap.neoforge.networking;

import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.neoforged.neoforge.network.PacketDistributor;
import net.neoforged.neoforge.network.registration.IPayloadRegistrar;

public class PacketHandler {
    public static void registerPackets(IPayloadRegistrar registrar) {
        registrar.play(
                ServerboundCapSettingsPacket.ID,
                ServerboundCapSettingsPacket::new,
                handler -> handler.server(ServerboundCapSettingsPacket::handle)
                );
        registrar.play(
                CapKeybindPacket.ID,
                CapKeybindPacket::new,
                handler -> handler.server(CapKeybindPacket::handle)
        );
        registrar.play(
                CapAnimPacket.ID,
                CapAnimPacket::new,
                handler -> handler.server(CapAnimPacket::handle)
        );
    }

    public static void sendToServer(CustomPacketPayload message) {
        PacketDistributor.SERVER.noArg().send(message);
    }
}
