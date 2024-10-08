package net.dialingspoon.speedcap.neoforge.curios;

import com.mojang.blaze3d.vertex.PoseStack;
import net.dialingspoon.speedcap.neoforge.client.SpeedCapNeoForgeClientEvents;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.client.ICurioRenderer;

public class CurioRenderer implements ICurioRenderer {

    @Override
    public <T extends LivingEntity, M extends EntityModel<T>> void render(ItemStack stack, SlotContext slotContext, PoseStack matrixStack, RenderLayerParent<T, M> renderLayerParent,
                    MultiBufferSource renderTypeBuffer, int light, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {

        M model = renderLayerParent.getModel();
        if (model instanceof HumanoidModel<?>) {
            HumanoidModel<LivingEntity> humanoidModel = (HumanoidModel<LivingEntity>)model;
            SpeedCapNeoForgeClientEvents.ClientModBusEvents.capModel.render(matrixStack, renderTypeBuffer, stack, slotContext.entity(), light, humanoidModel);
        }
    }
}
