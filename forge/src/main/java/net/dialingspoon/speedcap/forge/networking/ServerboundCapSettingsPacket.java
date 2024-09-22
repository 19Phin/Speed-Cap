package net.dialingspoon.speedcap.forge.networking;

import net.dialingspoon.speedcap.forge.registry.ModItems;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.network.CustomPayloadEvent;

public class ServerboundCapSettingsPacket {
    private final float mineSpeed;
    private final float moveSpeed;
    private final boolean moveActive;
    private final boolean modifiable;
    private final boolean jump;
    private final boolean stoponadime;
    private final boolean mineActive;
    private final boolean creative;

    public ServerboundCapSettingsPacket(FriendlyByteBuf buf) {
        this.moveSpeed = buf.readFloat();
        this.mineSpeed = buf.readFloat();
        this.moveActive = buf.readBoolean();
        this.modifiable = buf.readBoolean();
        this.jump = buf.readBoolean();
        this.stoponadime = buf.readBoolean();
        this.mineActive = buf.readBoolean();
        this.creative = buf.readBoolean();
    }

    public void toBytes(FriendlyByteBuf buf) {
        buf.writeFloat(moveSpeed);
        buf.writeFloat(mineSpeed);
        buf.writeBoolean(moveActive);
        buf.writeBoolean(modifiable);
        buf.writeBoolean(jump);
        buf.writeBoolean(stoponadime);
        buf.writeBoolean(mineActive);
        buf.writeBoolean(creative);
    }

    public void handle(CustomPayloadEvent.Context context) {
        context.enqueueWork(() -> {
            ServerPlayer player = context.getSender();
            ItemStack cap = player.getItemInHand(InteractionHand.MAIN_HAND);
            if (!cap.is(ModItems.SPEEDCAP.get())) {
                cap = player.getItemInHand(InteractionHand.OFF_HAND);
                if (!cap.is(ModItems.SPEEDCAP.get())) return;
            }
            CompoundTag tag = cap.getTag().getCompound("SpeedCap");
            tag.putFloat("moveSpeed", moveSpeed);
            tag.putFloat("mineSpeed", mineSpeed);
            tag.putBoolean("moveActive", moveActive);
            tag.putBoolean("modifiable", modifiable);
            tag.putBoolean("jump", jump);
            tag.putBoolean("stoponadime", stoponadime);
            tag.putBoolean("mineActive", mineActive);
            tag.putBoolean("creative", creative);
        });
    }
}
