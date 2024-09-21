package net.dialingspoon.speedcap.fabric;

import net.dialingspoon.speedcap.models.CapModel;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.LivingEntity;

import net.dialingspoon.speedcap.fabric.client.SpeedCapFabricClient;

public class Model {
    public static CapModel<LivingEntity> capModel = new CapModel<>(Minecraft.getInstance().getEntityModels().bakeLayer(SpeedCapFabricClient.LAYER));
}
