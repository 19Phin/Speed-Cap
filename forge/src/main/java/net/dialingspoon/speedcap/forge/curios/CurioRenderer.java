package net.dialingspoon.speedcap.forge.curios;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.dialingspoon.speedcap.SpeedCap;
import net.dialingspoon.speedcap.forge.client.SpeedCapForgeClientEvents;
import net.dialingspoon.speedcap.render.CapModel;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.DyeableLeatherItem;
import net.minecraft.world.item.ItemStack;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.client.ICurioRenderer;

public class CurioRenderer implements ICurioRenderer {

    private static final ResourceLocation TEXTURE = new ResourceLocation(SpeedCap.MOD_ID, "textures/models/armor/speed_cap_layer_1.png");
    private static final ResourceLocation OVERLAY_TEXTURE = new ResourceLocation(SpeedCap.MOD_ID, "textures/models/armor/speed_cap_layer_1_overlay.png");


    @Override
    public <T extends LivingEntity, M extends EntityModel<T>> void render(ItemStack stack, SlotContext slotContext, PoseStack matrixStack, RenderLayerParent<T, M> renderLayerParent,
                    MultiBufferSource renderTypeBuffer, int light, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {

        M model = renderLayerParent.getModel();
        if (model instanceof HumanoidModel<?>) {
            HumanoidModel<T> humanoidModel = (HumanoidModel<T>)model;
            CapModel<LivingEntity> capModel = SpeedCapForgeClientEvents.capModel;
            LivingEntity livingEntity = slotContext.entity();

            humanoidModel.copyPropertiesTo((HumanoidModel<T>) capModel);

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
