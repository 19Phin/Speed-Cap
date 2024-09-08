package net.dialingspoon.speedcap.fabric.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.dialingspoon.speedcap.SpeedCap;
import net.dialingspoon.speedcap.render.SailModel;
import net.fabricmc.fabric.api.client.rendering.v1.ArmorRenderer;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;

public class Renderer implements ArmorRenderer {

    public static final ResourceLocation TEXTURE = new ResourceLocation(SpeedCap.MOD_ID, "textures/models/armor/speed_cap_layer_1.png");

    @Override
    public void render(PoseStack matrices, MultiBufferSource vertexConsumers, ItemStack stack, LivingEntity entity, EquipmentSlot slot, int light, HumanoidModel<LivingEntity> contextModel) {
        SailModel sailModel = SpeedCapFabricClient.getSailModel();

        contextModel.copyPropertiesTo(sailModel);

        sailModel.setupAnim(entity);

        boolean hasFoil = stack.hasFoil();
        VertexConsumer vertexConsumer = ItemRenderer.getFoilBuffer(vertexConsumers, sailModel.renderType(TEXTURE), false, hasFoil);
        sailModel.renderToBuffer(matrices, vertexConsumer, light, OverlayTexture.NO_OVERLAY, 1, 1, 1, 1.0F);
    }
}
