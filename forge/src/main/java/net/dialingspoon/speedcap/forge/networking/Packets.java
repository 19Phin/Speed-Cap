package net.dialingspoon.speedcap.forge.networking;

import net.dialingspoon.speedcap.SpeedCap;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;

public class Packets {
    private static SimpleChannel INSTANCE;

    private static int packetId = 0;
    private static int id() {
        return packetId++;
    }

    public static void registerPackets() {
        SimpleChannel net = NetworkRegistry.ChannelBuilder
                .named(new ResourceLocation(SpeedCap.MOD_ID, "messages"))
                .networkProtocolVersion(() -> "1.0")
                .clientAcceptedVersions(s -> true)
                .serverAcceptedVersions(s -> true)
                .simpleChannel();

        INSTANCE = net;
        net.messageBuilder(ServerboundCapSettingsPacket.class, id(), NetworkDirection.PLAY_TO_SERVER)
                .decoder(ServerboundCapSettingsPacket::new)
                .encoder(ServerboundCapSettingsPacket::toBytes)
                .consumerMainThread(ServerboundCapSettingsPacket::handle)
                .add();
        net.messageBuilder(CapKeybindPacket.class, id(), NetworkDirection.PLAY_TO_SERVER)
                .decoder(CapKeybindPacket::new)
                .encoder(CapKeybindPacket::toBytes)
                .consumerMainThread(CapKeybindPacket::handle)
                .add();
        net.messageBuilder(CapAnimPacket.class, id(), NetworkDirection.PLAY_TO_SERVER)
                .decoder(CapAnimPacket::new)
                .encoder(CapAnimPacket::toBytes)
                .consumerMainThread(CapAnimPacket::handle)
                .add();
    }

    public static <MSG> void sendToServer(MSG message) {
        INSTANCE.sendToServer(message);
    }
}
