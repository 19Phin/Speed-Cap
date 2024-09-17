package net.dialingspoon.speedcap.forge.client;

import net.dialingspoon.speedcap.SpeedCap;
import net.dialingspoon.speedcap.item.SpeedCapItem;
import net.dialingspoon.speedcap.registry.ModItems;
import net.dialingspoon.speedcap.render.CapModel;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.geom.LayerDefinitions;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.RegisterColorHandlersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = SpeedCap.MOD_ID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class SpeedCapForgeClientEvents {

    private static final ModelLayerLocation LAYER = new ModelLayerLocation(new ResourceLocation(SpeedCap.MOD_ID, "speedcap"), "main");

    public static CapModel<LivingEntity> capModel = null;

    @SubscribeEvent
    public static void initLayers(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(LAYER, () -> CapModel.createLayer(LayerDefinitions.OUTER_ARMOR_DEFORMATION));
    }

    @SubscribeEvent
    public static void initModels(EntityRenderersEvent.AddLayers event) {
        capModel = new CapModel<>(Minecraft.getInstance().getEntityModels().bakeLayer(LAYER));
    }

    @SubscribeEvent
    public static void registerColors(RegisterColorHandlersEvent.Item event) {
        event.register((itemStack, layer) -> layer > 0 ? -1 : ((SpeedCapItem)itemStack.getItem()).getColor(itemStack), ModItems.SPEEDCAP.get());
    }
}
