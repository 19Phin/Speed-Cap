package net.dialingspoon.speedcap.forge.networking;

import net.dialingspoon.speedcap.SpeedCap;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.network.*;

public class Packets {
    private static SimpleChannel INSTANCE;

    public static final int PROTOCOL_VERSION = 1;

    private static int packetId = 0;
    private static int id() {
        return packetId++;
    }

    public static void registerPackets() {
        SimpleChannel net = ChannelBuilder.named(ResourceLocation.tryBuild(SpeedCap.MOD_ID, "main")).networkProtocolVersion(PROTOCOL_VERSION).acceptedVersions(Channel.VersionTest.exact(PROTOCOL_VERSION)).simpleChannel();

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
        INSTANCE.send(message, PacketDistributor.SERVER.noArg());
    }
}
