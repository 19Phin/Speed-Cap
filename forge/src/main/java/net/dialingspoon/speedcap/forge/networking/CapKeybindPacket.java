package net.dialingspoon.speedcap.forge.networking;

import io.netty.buffer.ByteBuf;
import net.dialingspoon.speedcap.Util;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.network.CustomPayloadEvent;

public class CapKeybindPacket {
    private boolean move;

    public CapKeybindPacket(boolean move) {
        this.move = move;
    }

    public CapKeybindPacket(ByteBuf buf) {
        this.move = buf.readBoolean();
    }

    public void toBytes(FriendlyByteBuf buf) {
        buf.writeBoolean(move);
    }

    public void handle(CustomPayloadEvent.Context context) {
        context.enqueueWork(() -> {
            Player player = context.getSender();
            ItemStack cap = Util.getActiveCap(player);
            CompoundTag tag = cap.getTag().getCompound("SpeedCap");
            tag.putBoolean("moveActive", tag.getBoolean("moveActive") != move);
            tag.putBoolean("mineActive", tag.getBoolean("mineActive") == move);
        });
    }
}

