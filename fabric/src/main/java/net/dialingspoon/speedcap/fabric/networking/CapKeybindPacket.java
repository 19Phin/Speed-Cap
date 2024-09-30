package net.dialingspoon.speedcap.fabric.networking;

import net.dialingspoon.speedcap.SpeedCap;
import net.dialingspoon.speedcap.Util;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.game.ServerPacketListener;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;

public class CapKeybindPacket {

    public static final ResourceLocation ID = ResourceLocation.tryBuild(SpeedCap.MOD_ID, "cap_key");

    public static void receive(MinecraftServer client, ServerPlayer player, ServerPacketListener handler,
                               FriendlyByteBuf buf, PacketSender responseSender) {
        ItemStack cap = Util.getActiveCap(player);
        boolean move = buf.readBoolean();
        CompoundTag tag = cap.getTag().getCompound("SpeedCap");
        tag.putBoolean("moveActive", tag.getBoolean("moveActive") != move);
        tag.putBoolean("mineActive", tag.getBoolean("mineActive") == move);
    }
}

