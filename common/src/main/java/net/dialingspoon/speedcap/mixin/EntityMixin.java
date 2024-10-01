package net.dialingspoon.speedcap.mixin;

import net.dialingspoon.speedcap.PlatformSpecific;
import net.dialingspoon.speedcap.Util;
import net.dialingspoon.speedcap.interfaces.EntityInterface;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import org.joml.Vector3d;
import org.spongepowered.asm.mixin.Final;
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

    @Shadow 
    @Final protected SynchedEntityData entityData;
    @Unique
    private static final EntityDataAccessor<Boolean> DATA_SPEEDING = SynchedEntityData.defineId(Entity.class, EntityDataSerializers.BOOLEAN);
    
    @Unique
    private boolean speedcap$moving;
    @Unique
    private boolean speedcap$couldSpeed;
    @Unique
    private long speedcap$localTick;
    @Unique
    private boolean speedcap$clientSpeeding;

    @Unique
    private ItemStack speedcap$cap;
    @Unique
    private CompoundTag speedcap$data;

    @Override
    public boolean speedcap$isSpeeding() {
        return this.entityData.get(DATA_SPEEDING);
    }
    @Override
    public void speedcap$setSpeeding(boolean bl) {
        this.entityData.set(DATA_SPEEDING, bl);
    }

    @Override
    public void speedcap$moving(boolean b) {
        speedcap$moving = b;
    }
    @Override
    public void speedcap$couldSpeed(boolean b) {
        speedcap$couldSpeed = b;
    }

    @Override
    public void speedcap$setData(CompoundTag tag) {
        speedcap$data = tag;
    }
    @Override
    public CompoundTag speedcap$getData() {
        return this.speedcap$data;
    }
    @Override
    public void speedcap$setCapStack(ItemStack stack) {
        this.speedcap$cap = stack;
    }
    @Override
    public ItemStack speedcap$getCapStack() {
        return this.speedcap$cap;
    }


    @ModifyVariable(method = "setDeltaMovement(Lnet/minecraft/world/phys/Vec3;)V", at = @At("HEAD"), argsOnly = true)
    private Vec3 slowDown(Vec3 vec3) {
        if ((Object)this instanceof LivingEntity entity) {

            if (Util.shouldHandleSelf(entity)) {
                boolean client = entity instanceof Player;
                boolean oldSpeeding = false;
                if (client) {
                    oldSpeeding = speedcap$clientSpeeding;
                }

                long gameTime = entity.level() != null ? entity.level().getGameTime() : 0;
                if (speedcap$localTick != gameTime) {
                    if (client) {
                        speedcap$clientSpeeding = false;
                    } else {
                        speedcap$setSpeeding(false);
                    }
                    speedcap$localTick = gameTime;
                }

                ItemStack cap = Util.getActiveCap(entity);
                if (!cap.isEmpty() && speedcap$data.getBoolean("moveActive")) {
                    Vector3d modifiedVec = new Vector3d(vec3.x, 0, vec3.z);

                    float f = speedcap$data.getFloat("moveSpeed") / 20.5f;
                    if (speedcap$data.getBoolean("modifiable")) {
                        if(speedcap$couldSpeed && speedcap$moving) {
                            if (client) {
                                speedcap$clientSpeeding = true;
                            } else {
                                speedcap$setSpeeding(true);
                            }
                        }

                    } else if (modifiedVec.length() >= f) {
                        modifiedVec.normalize().mul(f);
                        if (client) {
                            speedcap$clientSpeeding = true;
                        } else {
                            speedcap$setSpeeding(true);
                        }
                    }

                    double cappedY = vec3.y;
                    if (speedcap$data.getBoolean("jump")) {
                        cappedY = Math.min(vec3.y, .44);
                        if (vec3.y != cappedY) {
                            if (client) {
                                speedcap$clientSpeeding = true;
                            } else {
                                speedcap$setSpeeding(true);
                            }
                        }
                    }
                    if (client && oldSpeeding != speedcap$clientSpeeding) {
                        PlatformSpecific.sendAnimToServer(speedcap$clientSpeeding);
                    }
                    return new Vec3(modifiedVec.x, cappedY, modifiedVec.z);
                }
                if (client && oldSpeeding != speedcap$clientSpeeding) {
                    PlatformSpecific.sendAnimToServer(speedcap$clientSpeeding);
                }
            }
        }
        return vec3;
    }

    @Inject(at = @At(value = "RETURN"), method = "<init>")
    private void syncSpeeding(EntityType entityType, Level level, CallbackInfo ci) {
        this.entityData.define(DATA_SPEEDING, false);
    }
}
