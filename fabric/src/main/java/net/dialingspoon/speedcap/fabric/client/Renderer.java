package net.dialingspoon.speedcap.fabric.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.dialingspoon.speedcap.SpeedCap;
import net.dialingspoon.speedcap.render.CapModel;
import net.fabricmc.fabric.api.client.rendering.v1.ArmorRenderer;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.DyeableLeatherItem;
import net.minecraft.world.item.ItemStack;

public class Renderer implements ArmorRenderer {

    private static final ResourceLocation TEXTURE = new ResourceLocation(SpeedCap.MOD_ID, "textures/models/armor/speed_cap_layer_1.png");
    private static final ResourceLocation OVERLAY_TEXTURE = new ResourceLocation(SpeedCap.MOD_ID, "textures/models/armor/speed_cap_layer_1_overlay.png");

    @Override
    public void render(PoseStack matrixStack, MultiBufferSource renderTypeBuffer, ItemStack stack, LivingEntity livingEntity, EquipmentSlot slot, int light, HumanoidModel<LivingEntity> contextModel) {
        CapModel capModel = SpeedCapFabricClient.getCapModel();

        contextModel.copyPropertiesTo(capModel);

        capModel.setupAnim(livingEntity);
        int i = ((DyeableLeatherItem)stack.getItem()).getColor(stack);
        float f = (float)(i >> 16 & 255) / 255.0F;
        float f1 = (float)(i >> 8 & 255) / 255.0F;
        float f2 = (float)(i & 255) / 255.0F;

        VertexConsumer vertexConsumer = ItemRenderer.getArmorFoilBuffer(renderTypeBuffer, capModel.renderType(TEXTURE), false, stack.hasFoil());
        capModel.renderToBuffer(matrixStack, vertexConsumer, light, OverlayTexture.NO_OVERLAY, f, f1, f2, 1.0F);
        vertexConsumer = ItemRenderer.getArmorFoilBuffer(renderTypeBuffer, capModel.renderType(OVERLAY_TEXTURE), false, false);
        capModel.renderToBuffer(matrixStack, vertexConsumer, light, OverlayTexture.NO_OVERLAY, 1,1,1, 1.0F);
    }
}
