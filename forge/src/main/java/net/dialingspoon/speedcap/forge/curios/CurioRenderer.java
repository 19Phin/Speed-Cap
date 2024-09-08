package net.dialingspoon.speedcap.forge.curios;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.dialingspoon.speedcap.SpeedCap;
import net.dialingspoon.speedcap.forge.client.SpeedCapForgeClientEvents;
import net.dialingspoon.speedcap.render.SailModel;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.client.ICurioRenderer;

public class CurioRenderer implements ICurioRenderer {

    public static final ResourceLocation TEXTURE = new ResourceLocation(SpeedCap.MOD_ID, "textures/models/armor/speed_cap_layer_1.png");


    @Override
    public <T extends LivingEntity, M extends EntityModel<T>> void render(ItemStack stack,
            SlotContext slotContext, PoseStack matrixStack, RenderLayerParent<T, M> renderLayerParent,
            MultiBufferSource renderTypeBuffer, int light, float limbSwing, float limbSwingAmount,
            float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {

        M model = renderLayerParent.getModel();
        if (model instanceof HumanoidModel<?>) {
            HumanoidModel<T> humanoidModel = (HumanoidModel<T>)model;
            SailModel<LivingEntity> sailModel = SpeedCapForgeClientEvents.sailModel;
            LivingEntity livingEntity = slotContext.entity();

            humanoidModel.copyPropertiesTo((HumanoidModel<T>) sailModel);

            sailModel.setupAnim(livingEntity);

            boolean hasFoil = stack.hasFoil();
            VertexConsumer vertexConsumer = ItemRenderer.getFoilBuffer(renderTypeBuffer, sailModel.renderType(TEXTURE), false, hasFoil);
            sailModel.renderToBuffer(matrixStack, vertexConsumer, light, OverlayTexture.NO_OVERLAY, 1,1,1, 1.0F);
        }
    }
}
