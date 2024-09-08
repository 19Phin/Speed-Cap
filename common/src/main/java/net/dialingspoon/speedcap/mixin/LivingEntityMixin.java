package net.dialingspoon.speedcap.mixin;

import net.dialingspoon.speedcap.PlatformSpecific;
import net.dialingspoon.speedcap.registry.KeyRegistry;
import net.dialingspoon.speedcap.registry.ModItems;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(LivingEntity.class)
public class LivingEntityMixin{

    @Shadow public float xxa;

    @Shadow public float zza;

    @Redirect(method = "aiStep", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/LivingEntity;setDeltaMovement(DDD)V"))
    private void Stoponadime(LivingEntity instance, double x, double y, double z) {
        LivingEntity entity = (LivingEntity) (Object)this;
        boolean hasSpeedCap = entity.getSlot(103).get().is(ModItems.SPEEDCAP.get()) || PlatformSpecific.IsInCurios(entity, ModItems.SPEEDCAP.get());
        if (hasSpeedCap && (!((Object)this instanceof LocalPlayer) || KeyRegistry.dime)) {
            if (this.xxa == 0.0f && this.zza == 0.0f) {
                instance.setDeltaMovement(0, y, 0);
            }
        } else {
            instance.setDeltaMovement(x, y, z);
        }
    }
}
