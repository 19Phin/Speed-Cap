package net.dialingspoon.speedcap.forge.client;

import net.dialingspoon.speedcap.SpeedCap;
import net.dialingspoon.speedcap.render.SailModel;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.geom.LayerDefinitions;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = SpeedCap.MOD_ID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class SpeedCapForgeClientEvents {

    public static final ModelLayerLocation LAYER = new ModelLayerLocation(new ResourceLocation(SpeedCap.MOD_ID, "speedcap"), "main");

    public static SailModel<LivingEntity> sailModel = null;

    @SubscribeEvent
    public static void initLayers(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(LAYER, () -> SailModel.createLayer(LayerDefinitions.OUTER_ARMOR_DEFORMATION));
    }

    @SubscribeEvent
    public static void initModels(EntityRenderersEvent.AddLayers event) {
        sailModel = new SailModel<>(Minecraft.getInstance().getEntityModels().bakeLayer(LAYER));
    }

}
