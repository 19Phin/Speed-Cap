package net.dialingspoon.speedcap.neoforge.networking;

import io.netty.buffer.ByteBuf;
import net.dialingspoon.speedcap.SpeedCap;
import net.dialingspoon.speedcap.interfaces.EntityInterface;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.neoforged.neoforge.network.handling.PlayPayloadContext;

public class CapAnimPacket implements CustomPacketPayload {
    public static final ResourceLocation ID = ResourceLocation.tryBuild(SpeedCap.MOD_ID, "cap_anim");
    public boolean speeding;

    public CapAnimPacket(boolean move) {
        this.speeding = move;
    }

    public CapAnimPacket(ByteBuf buf) {
        this.speeding = buf.readBoolean();
    }

    @Override
    public void write(FriendlyByteBuf buf) {
        buf.writeBoolean(speeding);
    }

    public static void handle(CapAnimPacket packet, PlayPayloadContext context) {
        Player player = context.player().get();
        ((EntityInterface)player).speedcap$setSpeeding(packet.speeding);
    }

    @Override
    public ResourceLocation id() {
        return ID;
    }
}

