package net.dialingspoon.speedcap.neoforge.networking;

import io.netty.buffer.ByteBuf;
import net.dialingspoon.speedcap.item.CapSettingsComponent;
import net.dialingspoon.speedcap.neoforge.registry.ModDataComponents;
import net.dialingspoon.speedcap.neoforge.registry.ModItems;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.network.handling.IPayloadContext;

public record ServerboundCapSettingsPacket(float moveSpeed, float mineSpeed, boolean moveActive, boolean modifiable,
                                           boolean jump, boolean stoponadime, boolean mineActive, boolean creative) implements CustomPacketPayload {
    public static final Type<ServerboundCapSettingsPacket> TYPE = new Type<>(new ResourceLocation("mymod", "cap_menu"));

    public static final StreamCodec<ByteBuf, ServerboundCapSettingsPacket> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.FLOAT,
            ServerboundCapSettingsPacket::moveSpeed,
            ByteBufCodecs.FLOAT,
            ServerboundCapSettingsPacket::mineSpeed,
            ByteBufCodecs.BYTE,
            ServerboundCapSettingsPacket::packBooleans,
            (minespeed, movespeed, bytes) -> {
                boolean[] bools = unpackBooleans(bytes);
                return new ServerboundCapSettingsPacket(movespeed, minespeed, bools[0], bools[1], bools[2], bools[3], bools[4], bools[5]);
            });


    public byte packBooleans() {
        byte packed = 0;
        if (moveActive) packed |= 1;
        if (modifiable) packed |= 1 << 1;
        if (jump) packed |= 1 << 2;
        if (stoponadime) packed |= 1 << 3;
        if (mineActive) packed |= 1 << 4;
        if (creative) packed |= 1 << 5;
        return packed;
    }

    public static boolean[] unpackBooleans(byte packed) {
        boolean moveActive = (packed & (1)) != 0;
        boolean modifiable = (packed & (1 << 1)) != 0;
        boolean jump = (packed & (1 << 2)) != 0;
        boolean stoponadime = (packed & (1 << 3)) != 0;
        boolean mineActive = (packed & (1 << 4)) != 0;
        boolean creative = (packed & (1 << 5)) != 0;
        return new boolean[]{moveActive, modifiable, jump, stoponadime, mineActive, creative};
    }

    public void handle(IPayloadContext context) {
        Player player = context.player();
        ItemStack cap = player.getItemInHand(InteractionHand.MAIN_HAND);
        if (!cap.is(ModItems.SPEEDCAP.get())) {
            cap = player.getItemInHand(InteractionHand.OFF_HAND);
            if (!cap.is(ModItems.SPEEDCAP.get())) return;
        }
        cap.set(ModDataComponents.SPEEDCAP_DATA, new CapSettingsComponent(mineSpeed, moveSpeed, moveActive, modifiable, jump, stoponadime, mineActive, creative));
    }

    @Override
    public Type<ServerboundCapSettingsPacket> type() {
        return TYPE;
    }
}
