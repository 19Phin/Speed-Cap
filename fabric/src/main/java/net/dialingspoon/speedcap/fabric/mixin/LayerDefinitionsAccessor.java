package net.dialingspoon.speedcap.fabric.mixin;

import net.minecraft.client.model.geom.LayerDefinitions;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(LayerDefinitions.class)
public interface LayerDefinitionsAccessor {
    @Accessor
    CubeDeformation getOUTER_ARMOR_DEFORMATION();
}
