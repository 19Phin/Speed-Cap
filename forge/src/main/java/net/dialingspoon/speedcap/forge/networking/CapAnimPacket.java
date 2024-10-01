package net.dialingspoon.speedcap.forge.networking;

import io.netty.buffer.ByteBuf;
import net.dialingspoon.speedcap.interfaces.EntityInterface;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class CapAnimPacket {
    private boolean speeding;

    public CapAnimPacket(boolean move) {
        this.speeding = move;
    }

    public CapAnimPacket(ByteBuf buf) {
        this.speeding = buf.readBoolean();
    }

    public void toBytes(FriendlyByteBuf buf) {
        buf.writeBoolean(speeding);
    }

    public void handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            Player player = context.getSender();
            ((EntityInterface)player).speedcap$setSpeeding(speeding);
        });
    }
}

