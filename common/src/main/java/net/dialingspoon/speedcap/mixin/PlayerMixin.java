package net.dialingspoon.speedcap.mixin;

import net.dialingspoon.speedcap.Util;
import net.dialingspoon.speedcap.interfaces.EntityInterface;
import net.dialingspoon.speedcap.interfaces.LivingEntityInterface;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(Player.class)
public class PlayerMixin {

    @Redirect(method = "getSpeed", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/player/Player;getAttributeValue(Lnet/minecraft/world/entity/ai/attributes/Attribute;)D"))
    private double checkSpeed(Player player, Attribute attribute) {
        AttributeInstance movementAttribute = player.getAttribute(attribute);
        AttributeModifier sprintModifier = ((LivingEntityInterface)player).getSPEED_MODIFIER_SPRINTING();
        double speed = movementAttribute.getValue();

        boolean isSprinting = movementAttribute.hasModifier(sprintModifier);
        if (isSprinting) {
            speed /= 1.0 + sprintModifier.getAmount();
        }

        ItemStack cap = Util.getActiveCap(player);
        CompoundTag data = ((EntityInterface) player).speedcap$getData();

        float maxSpeed = ((EntityInterface) player).speedcap$getData().getFloat("moveSpeed") / 44f;
        if (!cap.isEmpty() && data.getBoolean("moveActive") && data.getBoolean("modifiable") && speed > maxSpeed) {
            speed = maxSpeed;
            ((EntityInterface) player).speedcap$couldSpeed(true);
        } else {
            ((EntityInterface) player).speedcap$couldSpeed(false);
        }

        if (isSprinting) {
            speed *= 1.0 + sprintModifier.getAmount();
        }

        return speed;
    }
}
