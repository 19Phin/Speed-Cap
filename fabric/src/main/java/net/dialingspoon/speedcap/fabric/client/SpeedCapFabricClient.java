package net.dialingspoon.speedcap.fabric.client;

import dev.emi.trinkets.api.client.TrinketRendererRegistry;
import net.dialingspoon.speedcap.SpeedCap;
import net.dialingspoon.speedcap.fabric.mixin.LayerDefinitionsAccessor;
import net.dialingspoon.speedcap.fabric.trinkets.TrinketRenderer;
import net.dialingspoon.speedcap.item.SpeedCapItem;
import net.dialingspoon.speedcap.registry.ModItems;
import net.dialingspoon.speedcap.render.CapModel;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.ArmorRenderer;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.minecraft.client.model.geom.LayerDefinitions;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;

public final class SpeedCapFabricClient implements ClientModInitializer {
    private static final ModelLayerLocation LAYER = new ModelLayerLocation(new ResourceLocation(SpeedCap.MOD_ID, "speedcap"), "main");
    private static CapModel<LivingEntity> capModel = null;

    @Override
    public void onInitializeClient() {
        LayerDefinitionsAccessor deformationGetter = ((LayerDefinitionsAccessor)new LayerDefinitions());
        EntityModelLayerRegistry.registerModelLayer(LAYER, () -> CapModel.createLayer(deformationGetter.getOUTER_ARMOR_DEFORMATION()));

        ArmorRenderer.register(new Renderer(), ModItems.SPEEDCAP.get());
        TrinketRendererRegistry.registerRenderer(ModItems.SPEEDCAP.get(), new TrinketRenderer());

        ColorProviderRegistry.ITEM.register((itemStack, layer) -> layer > 0 ? -1 : ((SpeedCapItem)itemStack.getItem()).getColor(itemStack), ModItems.SPEEDCAP.get());
    }

    public static CapModel<LivingEntity> getCapModel() {
        if (capModel == null) {
            capModel = new CapModel<>(net.minecraft.client.Minecraft.getInstance().getEntityModels().bakeLayer(LAYER));
        }
        return capModel;
    }
}
