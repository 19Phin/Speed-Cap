package net.dialingspoon.speedcap.mixin;

import net.dialingspoon.speedcap.Util;
import net.dialingspoon.speedcap.interfaces.EntityInterface;
import net.minecraft.network.protocol.game.ClientboundAnimatePacket;
import net.minecraft.server.level.ServerChunkCache;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import org.joml.Vector3d;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Entity.class)
public abstract class EntityMixin implements EntityInterface {
    @Shadow
    public Level level() {
        return null;
    }

    @Unique
    private boolean speedcap$moving;
    @Unique
    private boolean speedcap$speeding;
    @Unique
    private boolean speedcap$couldSpeed;
    @Unique
    private long speedcap$localTick;
    @Unique
    boolean speedcap$sailDirection;
    @Unique
    float speedcap$sailTick;


    @Override
    public boolean speedcap$isSpeeding() {
        return speedcap$speeding;
    }

    @Override
    public boolean speedcap$getSailDirection() {
        return speedcap$sailDirection;
    }

    @Override
    public float speedcap$getSailTick() {
        return speedcap$sailTick;
    }

    @Override
    public void speedcap$setSailDirection(boolean direction) {
        speedcap$sailDirection = direction;
    }

    @Override
    public void speedcap$setSailTick(float tick) {
        speedcap$sailTick = tick;
    }

    @Override
    public void speedcap$setSpeeding(boolean bl) {
        speedcap$speeding = bl;
    }

    @Override
    public void speedcap$couldSpeed(boolean b) {
        speedcap$couldSpeed = b;
    }

    @Override
    public void speedcap$moving(boolean b) {
        speedcap$moving = b;
    }

    @ModifyVariable(method = "setDeltaMovement(Lnet/minecraft/world/phys/Vec3;)V", at = @At("HEAD"), argsOnly = true)
    private Vec3 slowDown(Vec3 vec3) {
        if ((Object)this instanceof LivingEntity entity) {
            boolean isClient =  Util.isClientPlayer(entity);
            if (this.level() instanceof ServerLevel || isClient) {
                long gameTime = entity.level() != null ? entity.level().getGameTime() : 0;
                if (speedcap$localTick != gameTime) {
                    speedcap$speeding = false;
                    speedcap$localTick = gameTime;
                }

                ItemStack cap = Util.getActiveCap(entity);
                if (!cap.isEmpty() && cap.getTag().getBoolean("moveActive")) {
                    Vector3d modifiedVec = new Vector3d(vec3.x, 0, vec3.z);

                    float f = cap.getTag().getFloat("moveSpeed") / 20.5f;
                    if (cap.getTag().getBoolean("modifiable")) {
                        if(speedcap$couldSpeed && speedcap$moving) {
                            speedcap$speeding = true;
                        }
                    }else if (modifiedVec.length() >= f) {
                        modifiedVec.normalize().mul(f);
                        speedcap$speeding = true;
                    }

                    double cappedY = vec3.y;
                    if (cap.getTag().getBoolean("jump")) {
                        cappedY = Math.min(vec3.y, .44);
                        if (vec3.y != cappedY) {
                            speedcap$speeding = true;
                        }
                    }
                    return new Vec3(modifiedVec.x, cappedY, modifiedVec.z);
                }
            }
        }
        return vec3;
    }

    @Inject(at = @At(value = "TAIL", ordinal = -2), method = "baseTick")
    private void broadcastSpeed(CallbackInfo ci) {
        if (this.level() instanceof ServerLevel) {
            ClientboundAnimatePacket clientboundanimatepacket = new ClientboundAnimatePacket((Entity) (Object) this, speedcap$speeding ? 47 : 46);
            ServerChunkCache serverchunkcache = ((ServerLevel) this.level()).getChunkSource();
            serverchunkcache.broadcast((Entity) (Object) this, clientboundanimatepacket);
        }
    }

}
