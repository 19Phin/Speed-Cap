package net.dialingspoon.speedcap.mixin;

import net.dialingspoon.speedcap.PlatformSpecific;
import net.dialingspoon.speedcap.interfaces.EntityInterface;
import net.dialingspoon.speedcap.registry.KeyRegistry;
import net.dialingspoon.speedcap.registry.ModItems;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.network.protocol.game.ClientboundAnimatePacket;
import net.minecraft.server.level.ServerChunkCache;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
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
public class EntityMixin implements EntityInterface {

    @Shadow
    public Level level() {
        return null;
    }

    @Unique
    private boolean speedcap$speeding = false;

    @Unique
    private long speedcap$localTick = 0;

    @Unique
    boolean speedcap$sailDirection = false;
    @Unique
    float speedcap$sailTick = 0;

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

    @ModifyVariable(method = "setDeltaMovement(Lnet/minecraft/world/phys/Vec3;)V", at = @At("HEAD"), argsOnly = true)
    private Vec3 slowDown(Vec3 vec3) {
        boolean isClient = ((Object)this instanceof LocalPlayer);
        if (this.level() instanceof ServerLevel || isClient) {
            long gameTime = Minecraft.getInstance().level != null ? Minecraft.getInstance().level.getGameTime() : 0;
            if (speedcap$localTick != gameTime) {
                speedcap$speeding = false;
                speedcap$localTick = gameTime;
            }
        }

        if ((Object)this instanceof LivingEntity entity) {
            boolean hasSpeedCap = entity.getSlot(103).get().is(ModItems.SPEEDCAP.get()) || PlatformSpecific.IsInCurios(entity, ModItems.SPEEDCAP.get());
            if (hasSpeedCap && (!isClient || KeyRegistry.isActive)) {
                Vector3d modifiedVec = new Vector3d(vec3.x, 0, vec3.z);

                if (modifiedVec.length() >= 0.5) {
                    modifiedVec.normalize().mul(0.5f);
                    speedcap$speeding = true;
                }
                double cappedY = Math.min(vec3.y, 0.5);
                if (vec3.y != cappedY) {
                    speedcap$speeding = true;
                }
                return new Vec3(modifiedVec.x, cappedY, modifiedVec.z);
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
