package net.dialingspoon.speedcap.interfaces;

import net.minecraft.world.entity.ai.attributes.AttributeModifier;

public interface LivingEntityInterface {
    long speedcap$getSailTick();

    boolean speedcap$sailDirection();

    AttributeModifier getSPEED_MODIFIER_SPRINTING();
}
