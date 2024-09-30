package net.dialingspoon.speedcap.neoforge.networking;

import io.netty.buffer.ByteBuf;
import net.dialingspoon.speedcap.SpeedCap;
import net.dialingspoon.speedcap.Util;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.network.handling.PlayPayloadContext;

public class CapKeybindPacket implements CustomPacketPayload {
    public static final ResourceLocation ID = ResourceLocation.tryBuild(SpeedCap.MOD_ID, "cap_key");
    private boolean move;

    public CapKeybindPacket(boolean move) {
        this.move = move;
    }

    public CapKeybindPacket(ByteBuf buf) {
        this.move = buf.readBoolean();
    }

    @Override
    public void write(FriendlyByteBuf buf) {
        buf.writeBoolean(move);
    }

    public static void handle(CapKeybindPacket packet, PlayPayloadContext context) {
        Player player = context.player().get();
        ItemStack cap = Util.getActiveCap(player);
        CompoundTag tag = cap.getTag().getCompound("SpeedCap");
        tag.putBoolean("moveActive", tag.getBoolean("moveActive") != packet.move);
        tag.putBoolean("mineActive", tag.getBoolean("mineActive") == packet.move);
    }

    @Override
    public ResourceLocation id() {
        return ID;
    }
}

