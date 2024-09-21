package net.dialingspoon.speedcap.fabric.trinkets;

import com.mojang.blaze3d.vertex.PoseStack;
import dev.emi.trinkets.api.SlotReference;
import dev.emi.trinkets.api.client.TrinketRendererRegistry;
import net.dialingspoon.speedcap.fabric.Model;
import net.dialingspoon.speedcap.fabric.registry.ModItems;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;

public class TrinketRenderer implements dev.emi.trinkets.api.client.TrinketRenderer {

    public static void register() {
        TrinketRendererRegistry.registerRenderer(ModItems.SPEEDCAP, new TrinketRenderer());
    }

    @Override
    public void render(ItemStack stack, SlotReference slotReference, EntityModel<? extends LivingEntity> contextModel, PoseStack matrixStack, MultiBufferSource renderTypeBuffer,
                       int light, LivingEntity livingEntity, float limbSwing, float limbSwingAmount, float tickDelta, float animationProgress, float netHeadYaw, float headPitch) {
        if (contextModel instanceof HumanoidModel<?>) {
            HumanoidModel<LivingEntity> humanoidModel = (HumanoidModel<LivingEntity>)contextModel;
            Model.capModel.render(matrixStack, renderTypeBuffer, stack, livingEntity, light, humanoidModel);
        }
    }
}
