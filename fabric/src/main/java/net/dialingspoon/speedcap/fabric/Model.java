package net.dialingspoon.speedcap.fabric;

import net.dialingspoon.speedcap.fabric.client.SpeedCapFabricClient;
import net.dialingspoon.speedcap.models.CapModel;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.LivingEntity;

public class Model {
    public static CapModel<LivingEntity> capModel = new CapModel<>(Minecraft.getInstance().getEntityModels().bakeLayer(SpeedCapFabricClient.CAP_LAYER));
}
