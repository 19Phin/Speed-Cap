package net.dialingspoon.speedcap.fabric.client;

import net.dialingspoon.speedcap.SpeedCap;
import net.dialingspoon.speedcap.fabric.SpeedCapFabric;
import net.dialingspoon.speedcap.fabric.mixin.LayerDefinitionsAccessor;
import net.dialingspoon.speedcap.fabric.registry.ModItems;
import net.dialingspoon.speedcap.fabric.trinkets.TrinketRenderer;
import net.dialingspoon.speedcap.item.SpeedCapItem;
import net.dialingspoon.speedcap.models.CapModel;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.ArmorRenderer;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.minecraft.client.model.geom.LayerDefinitions;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.component.DyedItemColor;

public final class SpeedCapFabricClient implements ClientModInitializer {
    public static final ModelLayerLocation CAP_LAYER = new ModelLayerLocation(ResourceLocation.tryBuild(SpeedCap.MOD_ID, "speedcap"), "main");

    @Override
    public void onInitializeClient() {
        LayerDefinitionsAccessor deformationGetter = ((LayerDefinitionsAccessor)new LayerDefinitions());
        EntityModelLayerRegistry.registerModelLayer(CAP_LAYER, () -> CapModel.createLayer(deformationGetter.getOUTER_ARMOR_DEFORMATION()));

        ArmorRenderer.register(new Renderer(), ModItems.SPEEDCAP);
        if (SpeedCapFabric.trinketsLoaded) {
            TrinketRenderer.register();
        }

        ColorProviderRegistry.ITEM.register((itemStack, layer) -> layer > 0 ? -1 : DyedItemColor.getOrDefault(itemStack, SpeedCapItem.DEFAULT_COLOR));
    }
}
