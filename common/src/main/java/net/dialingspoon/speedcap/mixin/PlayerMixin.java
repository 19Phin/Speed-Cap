package net.dialingspoon.speedcap.mixin;

import dev.architectury.platform.Platform;
import dev.architectury.utils.EnvExecutor;
import net.dialingspoon.speedcap.Util;
import net.dialingspoon.speedcap.interfaces.EntityInterface;
import net.dialingspoon.speedcap.interfaces.LivingEntityInterface;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(Player.class)
public class PlayerMixin {

    @Unique
    private long speedcap$localTick = 0;

    @Redirect(method = "getSpeed", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/player/Player;getAttributeValue(Lnet/minecraft/world/entity/ai/attributes/Attribute;)D"))
    private double checkSpeed(Player player, Attribute attribute) {

        if (player.level() instanceof ServerLevel || Util.isClientPlayer(player)) {
            long gameTime = player.level().getGameTime();
            if (speedcap$localTick != gameTime) {
                ((EntityInterface) this).speedcap$couldSpeed(false);
                speedcap$localTick = gameTime;
            }
        }

        AttributeInstance movementAttribute = player.getAttribute(attribute);
        AttributeModifier sprintModifier = ((LivingEntityInterface)this).getSPEED_MODIFIER_SPRINTING();
        double speed = movementAttribute.getValue();

        boolean isSprinting = movementAttribute.hasModifier(sprintModifier);
        if (isSprinting) {
            speed /= 1.0 + sprintModifier.getAmount();
        }

        ItemStack cap = Util.getActiveCap(player);
        if (!cap.isEmpty() && cap.getTag().getBoolean("moveActive") && cap.getTag().getBoolean("modifiable")) {
            float maxSpeed = cap.getTag().getFloat("moveSpeed") / 44f;
            if (speed > maxSpeed) {
                speed = maxSpeed;
                ((EntityInterface) this).speedcap$couldSpeed(true);
            }
        }

        if (isSprinting) {
            speed *= 1.0 + sprintModifier.getAmount();
        }

        return speed;
    }
}
