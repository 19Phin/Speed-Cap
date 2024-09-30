package net.dialingspoon.speedcap.fabric.networking;

import net.dialingspoon.speedcap.SpeedCap;
import net.dialingspoon.speedcap.fabric.registry.ModItems;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.game.ServerPacketListener;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.item.ItemStack;

public class ServerboundCapSettingsPacket {
    public static final ResourceLocation ID = ResourceLocation.tryBuild(SpeedCap.MOD_ID, "cap_menu");

    public static void receive(MinecraftServer client, ServerPlayer player, ServerPacketListener handler,
                               FriendlyByteBuf buf, PacketSender responseSender) {
        ItemStack cap = player.getItemInHand(InteractionHand.MAIN_HAND);
        if (!cap.is(ModItems.SPEEDCAP)) {
            cap = player.getItemInHand(InteractionHand.OFF_HAND);
            if (!cap.is(ModItems.SPEEDCAP)) return;
        }
        CompoundTag tag = cap.getTag().getCompound("SpeedCap");
        tag.putFloat("moveSpeed", buf.readFloat());
        tag.putFloat("mineSpeed", buf.readFloat());
        tag.putBoolean("moveActive", buf.readBoolean());
        tag.putBoolean("modifiable", buf.readBoolean());
        tag.putBoolean("jump", buf.readBoolean());
        tag.putBoolean("stoponadime", buf.readBoolean());
        tag.putBoolean("mineActive", buf.readBoolean());
        tag.putBoolean("creative", buf.readBoolean());
    }
}
