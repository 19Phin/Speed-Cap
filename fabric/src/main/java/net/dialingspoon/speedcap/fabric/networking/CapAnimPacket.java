package net.dialingspoon.speedcap.fabric.networking;

import net.dialingspoon.speedcap.SpeedCap;
import net.dialingspoon.speedcap.interfaces.EntityInterface;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.game.ServerPacketListener;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;

public class CapAnimPacket {

    public static final ResourceLocation ID = ResourceLocation.tryBuild(SpeedCap.MOD_ID, "cap_key");

    public static void receive(MinecraftServer client, ServerPlayer player, ServerPacketListener handler,
                               FriendlyByteBuf buf, PacketSender responseSender) {
        ((EntityInterface)player).speedcap$setSpeeding(buf.readBoolean());
    }
}

