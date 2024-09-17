package net.dialingspoon.speedcap.fabric.trinkets;

import com.mojang.blaze3d.vertex.PoseStack;
import dev.emi.trinkets.api.SlotReference;
import net.dialingspoon.speedcap.fabric.client.SpeedCapFabricClient;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;

public class TrinketRenderer implements dev.emi.trinkets.api.client.TrinketRenderer {

    @Override
    public void render(ItemStack stack, SlotReference slotReference, EntityModel<? extends LivingEntity> contextModel, PoseStack matrixStack, MultiBufferSource renderTypeBuffer,
                       int light, LivingEntity livingEntity, float limbSwing, float limbSwingAmount, float tickDelta, float animationProgress, float netHeadYaw, float headPitch) {
        if (contextModel instanceof HumanoidModel<?>) {
            HumanoidModel<LivingEntity> humanoidModel = (HumanoidModel<LivingEntity>)contextModel;
            SpeedCapFabricClient.getCapModel().render(matrixStack, renderTypeBuffer, stack, livingEntity, light, humanoidModel);
        }
    }
}
