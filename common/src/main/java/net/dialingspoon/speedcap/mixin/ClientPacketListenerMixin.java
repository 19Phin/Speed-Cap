package net.dialingspoon.speedcap.mixin;

import net.dialingspoon.speedcap.interfaces.EntityInterface;
import net.minecraft.client.multiplayer.ClientPacketListener;
import net.minecraft.network.protocol.game.ClientboundAnimatePacket;
import net.minecraft.world.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(ClientPacketListener.class)
public class ClientPacketListenerMixin {

    @Inject(at = @At("TAIL"), method = "handleAnimate", locals = LocalCapture.CAPTURE_FAILHARD)
    public void sail(ClientboundAnimatePacket arg, CallbackInfo ci, Entity entity) {
        if (entity != null) {
            if (arg.getAction() == 47) {
                ((EntityInterface)entity).speedcap$setSpeeding(true);
            } else if (arg.getAction() == 46) {
                ((EntityInterface)entity).speedcap$setSpeeding(false);
            }
        }
    }
}
