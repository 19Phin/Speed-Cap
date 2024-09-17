package net.dialingspoon.speedcap.networking;

import net.dialingspoon.speedcap.SpeedCap;
import net.dialingspoon.speedcap.registry.ModItems;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.item.ItemStack;

public class ServerboundCapSettingsPacket {
    public static final ResourceLocation ID = new ResourceLocation(SpeedCap.MOD_ID, "cap_menu");
    private final float mineSpeed;
    private final float moveSpeed;
    private final boolean moveActive;
    private final boolean crouch;
    private final boolean jump;
    private final boolean Stoponadime;
    private final boolean mineActive;
    private final boolean creative;

    public ServerboundCapSettingsPacket(FriendlyByteBuf buf) {
        this.moveSpeed = buf.readFloat();
        this.mineSpeed = buf.readFloat();
        this.moveActive = buf.readBoolean();
        this.crouch = buf.readBoolean();
        this.jump = buf.readBoolean();
        this.Stoponadime = buf.readBoolean();
        this.mineActive = buf.readBoolean();
        this.creative = buf.readBoolean();
    }

    public static void handle(ServerboundCapSettingsPacket packet, ServerPlayer player) {
        ItemStack cap = player.getItemInHand(InteractionHand.MAIN_HAND);
        if (!cap.is(ModItems.SPEEDCAP.get())) {
            cap = player.getItemInHand(InteractionHand.OFF_HAND);
            if (!cap.is(ModItems.SPEEDCAP.get())) return;
        }
        cap.getTag().putFloat("moveSpeed", packet.moveSpeed);
        cap.getTag().putFloat("mineSpeed", packet.mineSpeed);
        cap.getTag().putBoolean("moveActive", packet.moveActive);
        cap.getTag().putBoolean("modifiable", packet.crouch);
        cap.getTag().putBoolean("jump", packet.jump);
        cap.getTag().putBoolean("stoponadime", packet.Stoponadime);
        cap.getTag().putBoolean("mineActive", packet.mineActive);
        cap.getTag().putBoolean("creative", packet.creative);
        System.out.println(cap.getTag());
    }
}
