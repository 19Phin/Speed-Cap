package net.dialingspoon.speedcap.mixin;

import net.dialingspoon.speedcap.Util;
import net.dialingspoon.speedcap.interfaces.EntityInterface;
import net.dialingspoon.speedcap.interfaces.LivingEntityInterface;
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
                ((EntityInterface) player).speedcap$couldSpeed(false);
                speedcap$localTick = gameTime;
            }
        }

        AttributeInstance movementAttribute = player.getAttribute(attribute);
        AttributeModifier sprintModifier = ((LivingEntityInterface)player).getSPEED_MODIFIER_SPRINTING();
        double speed = movementAttribute.getValue();

        boolean isSprinting = movementAttribute.hasModifier(sprintModifier);
        if (isSprinting) {
            speed /= 1.0 + sprintModifier.getAmount();
        }

        ItemStack cap = Util.getActiveCap(player);
        if (!cap.isEmpty() && ((EntityInterface) player).getSpeedcap$data().getBoolean("moveActive") && ((EntityInterface) player).getSpeedcap$data().getBoolean("modifiable")) {
            float maxSpeed = ((EntityInterface) player).getSpeedcap$data().getFloat("moveSpeed") / 44f;
            if (speed > maxSpeed) {
                speed = maxSpeed;
                ((EntityInterface) player).speedcap$couldSpeed(true);
            }
        }

        if (isSprinting) {
            speed *= 1.0 + sprintModifier.getAmount();
        }

        return speed;
    }
}
