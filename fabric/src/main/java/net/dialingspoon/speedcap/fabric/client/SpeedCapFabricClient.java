package net.dialingspoon.speedcap.fabric.client;

import net.dialingspoon.speedcap.SpeedCap;
import net.dialingspoon.speedcap.registry.ModItems;
import net.dialingspoon.speedcap.render.SailModel;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.ArmorRenderer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.minecraft.client.model.geom.LayerDefinitions;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;

public final class SpeedCapFabricClient implements ClientModInitializer {
    public static final ModelLayerLocation LAYER = new ModelLayerLocation(new ResourceLocation(SpeedCap.MOD_ID, "speedcap"), "main");

    private static SailModel<LivingEntity> sailModel = null;

    @Override
    public void onInitializeClient() {
        //register entity model layer
        EntityModelLayerRegistry.registerModelLayer(LAYER, () -> SailModel.createLayer(LayerDefinitions.OUTER_ARMOR_DEFORMATION));
        ArmorRenderer renderer = new Renderer();
        //register armor renderer
        ArmorRenderer.register(renderer, ModItems.SPEEDCAP.get());
    }

    public static SailModel<LivingEntity> getSailModel() {
        if (sailModel == null) {
            sailModel = new SailModel<>(net.minecraft.client.Minecraft.getInstance().getEntityModels().bakeLayer(LAYER));;
        }
        return sailModel;
    }
}
