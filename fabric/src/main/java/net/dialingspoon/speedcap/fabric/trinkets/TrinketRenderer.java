package net.dialingspoon.speedcap.fabric.trinkets;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import dev.emi.trinkets.api.SlotReference;
import net.dialingspoon.speedcap.SpeedCap;
import net.dialingspoon.speedcap.fabric.client.SpeedCapFabricClient;
import net.dialingspoon.speedcap.render.CapModel;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.DyeableLeatherItem;
import net.minecraft.world.item.ItemStack;

public class TrinketRenderer implements dev.emi.trinkets.api.client.TrinketRenderer {

    public static final ResourceLocation TEXTURE = new ResourceLocation(SpeedCap.MOD_ID, "textures/models/armor/speed_cap_layer_1.png");
    public static final ResourceLocation OVERLAY_TEXTURE = new ResourceLocation(SpeedCap.MOD_ID, "textures/models/armor/speed_cap_layer_1_overlay.png");

    @Override
    public void render(ItemStack stack, SlotReference slotReference, EntityModel<? extends LivingEntity> contextModel, PoseStack matrixStack, MultiBufferSource renderTypeBuffer,
                       int light, LivingEntity livingEntity, float limbSwing, float limbSwingAmount, float tickDelta, float animationProgress, float netHeadYaw, float headPitch) {
        if (contextModel instanceof HumanoidModel<?>) {
            HumanoidModel<LivingEntity> humanoidModel = (HumanoidModel<LivingEntity>)contextModel;
            CapModel<LivingEntity> capModel = SpeedCapFabricClient.getCapModel();

            humanoidModel.copyPropertiesTo(capModel);

            capModel.setupAnim(livingEntity);
            int i = ((DyeableLeatherItem)stack.getItem()).getColor(stack);
            float f = (float)(i >> 16 & 255) / 255.0F;
            float f1 = (float)(i >> 8 & 255) / 255.0F;
            float f2 = (float)(i & 255) / 255.0F;

            boolean hasFoil = stack.hasFoil();
            VertexConsumer vertexConsumer = ItemRenderer.getArmorFoilBuffer(renderTypeBuffer, capModel.renderType(TEXTURE), false, hasFoil);
            capModel.renderToBuffer(matrixStack, vertexConsumer, light, OverlayTexture.NO_OVERLAY, f, f1, f2, 1.0F);
            vertexConsumer = ItemRenderer.getArmorFoilBuffer(renderTypeBuffer, capModel.renderType(OVERLAY_TEXTURE), false, false);
            capModel.renderToBuffer(matrixStack, vertexConsumer, light, OverlayTexture.NO_OVERLAY, 1,1,1, 1.0F);
        }
    }
}
