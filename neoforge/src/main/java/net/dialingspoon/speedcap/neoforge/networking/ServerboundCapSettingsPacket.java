package net.dialingspoon.speedcap.neoforge.networking;

import net.dialingspoon.speedcap.SpeedCap;
import net.dialingspoon.speedcap.neoforge.registry.ModItems;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.network.handling.PlayPayloadContext;

public class ServerboundCapSettingsPacket implements CustomPacketPayload {
    public static final ResourceLocation ID = ResourceLocation.tryBuild(SpeedCap.MOD_ID, "cap_menu");
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

    @Override
    public void write(FriendlyByteBuf buf) {
        buf.writeFloat(moveSpeed);
        buf.writeFloat(mineSpeed);
        buf.writeBoolean(moveActive);
        buf.writeBoolean(modifiable);
        buf.writeBoolean(jump);
        buf.writeBoolean(stoponadime);
        buf.writeBoolean(mineActive);
        buf.writeBoolean(creative);
    }

    public static void handle(ServerboundCapSettingsPacket packet, PlayPayloadContext context) {
        Player player = context.player().get();
        ItemStack cap = player.getItemInHand(InteractionHand.MAIN_HAND);
        if (!cap.is(ModItems.SPEEDCAP.get())) {
            cap = player.getItemInHand(InteractionHand.OFF_HAND);
            if (!cap.is(ModItems.SPEEDCAP.get())) return;
        }
        CompoundTag tag = cap.getTag().getCompound("SpeedCap");
        tag.putFloat("moveSpeed", packet.moveSpeed);
        tag.putFloat("mineSpeed", packet.mineSpeed);
        tag.putBoolean("moveActive", packet.moveActive);
        tag.putBoolean("modifiable", packet.modifiable);
        tag.putBoolean("jump", packet.jump);
        tag.putBoolean("stoponadime", packet.stoponadime);
        tag.putBoolean("mineActive", packet.mineActive);
        tag.putBoolean("creative", packet.creative);
    }

    @Override
    public ResourceLocation id() {
        return ID;
    }
}
