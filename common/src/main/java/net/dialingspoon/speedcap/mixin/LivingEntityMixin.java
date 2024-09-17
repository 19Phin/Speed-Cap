package net.dialingspoon.speedcap.mixin;

import net.dialingspoon.speedcap.Util;
import net.dialingspoon.speedcap.interfaces.EntityInterface;
import net.dialingspoon.speedcap.interfaces.LivingEntityInterface;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.Vec3;
import org.joml.Vector3d;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public class LivingEntityMixin implements LivingEntityInterface {

    @Shadow
    public float xxa;

    @Shadow
    public float zza;

    @Shadow
    @Final private static AttributeModifier SPEED_MODIFIER_SPRINTING;
    @Unique
    private long speedcap$localTick;

    @Redirect(method = "aiStep", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/LivingEntity;setDeltaMovement(DDD)V"))
    private void stoponadime(LivingEntity instance, double x, double y, double z) {
        ItemStack cap = Util.getActiveCap(instance);
        if (!cap.isEmpty() && cap.getTag().getBoolean("moveActive") && cap.getTag().getBoolean("stoponadime")) {
            if (this.xxa == 0.0f && this.zza == 0.0f) {
                instance.setDeltaMovement(0, y, 0);
            }
        } else {
            instance.setDeltaMovement(x, y, z);
        }
    }

    @Inject(method = "travel", at = @At(value = "HEAD"))
    private void checkMoving(Vec3 vec3, CallbackInfo ci) {
        LivingEntity entity = (LivingEntity)(Object)this;
        if (entity.level() instanceof ServerLevel || Util.isClientPlayer(entity)) {

            long gameTime = entity.level().getGameTime();
            if (speedcap$localTick != gameTime) {
                ((EntityInterface) this).speedcap$moving(false);
                speedcap$localTick = gameTime;
            }

            ItemStack cap = Util.getActiveCap(entity);
            if (!cap.isEmpty() && cap.getTag().getBoolean("moveActive")) {
                if (cap.getTag().getBoolean("modifiable") && !(vec3.x == 0 && vec3.z == 0)) {
                    ((EntityInterface) this).speedcap$moving(true);
                }
            }

        }
    }

    @Inject(method = "getSpeed", at = @At(value = "RETURN"), cancellable = true)
    private void checkSpeed(CallbackInfoReturnable<Float> cir) {
        float speed = cir.getReturnValue();
        ItemStack cap = Util.getActiveCap((LivingEntity)(Object)this);

        if (!cap.isEmpty() && cap.getTag().getBoolean("moveActive") && cap.getTag().getBoolean("modifiable")) {
            float maxSpeed = cap.getTag().getFloat("moveSpeed") / 44f;
            if (speed > maxSpeed) {
                speed = maxSpeed;
                ((EntityInterface) this).speedcap$couldSpeed(true);
            }
        }
        cir.setReturnValue(speed);
    }

    @Override
    public AttributeModifier getSPEED_MODIFIER_SPRINTING() {
        return SPEED_MODIFIER_SPRINTING;
    }
}
