package net.dialingspoon.speedcap.forge.client;

import net.dialingspoon.speedcap.SpeedCap;
import net.dialingspoon.speedcap.Util;
import net.dialingspoon.speedcap.forge.registry.ModItems;
import net.dialingspoon.speedcap.forge.registry.ModKeys;
import net.dialingspoon.speedcap.interfaces.EntityInterface;
import net.dialingspoon.speedcap.item.SpeedCapItem;
import net.dialingspoon.speedcap.models.CapModel;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.geom.LayerDefinitions;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.client.event.RegisterColorHandlersEvent;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

public class SpeedCapForgeClientEvents {
    @Mod.EventBusSubscriber(modid = SpeedCap.MOD_ID, value = Dist.CLIENT)
    public static class ClientForgeEvents {
        @SubscribeEvent
        public static void onKeyInput(InputEvent.Key event) {
            if (ModKeys.TOGGLE_SPEED.consumeClick()) {
                ItemStack cap = Util.getActiveCap(Minecraft.getInstance().player);
                if (!cap.isEmpty()) {
                    ((EntityInterface) Minecraft.getInstance().player).getSpeedcap$data().putBoolean("moveActive", !((EntityInterface) Minecraft.getInstance().player).getSpeedcap$data().getBoolean("moveActive"));
                }
            }
            if (ModKeys.TOGGLE_MINE.consumeClick()) {
                ItemStack cap = Util.getActiveCap(Minecraft.getInstance().player);
                if (!cap.isEmpty()) {
                    ((EntityInterface) Minecraft.getInstance().player).getSpeedcap$data().putBoolean("mineActive", ((EntityInterface) Minecraft.getInstance().player).getSpeedcap$data().getBoolean("mineActive"));
                }
            }
        }
    }

    @Mod.EventBusSubscriber(modid = SpeedCap.MOD_ID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class ClientModBusEvents {
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
            event.register((itemStack, layer) -> layer > 0 ? -1 : ((SpeedCapItem) itemStack.getItem()).getColor(itemStack), ModItems.SPEEDCAP.get());
        }

        @SubscribeEvent
        public static void onKeyRegister(RegisterKeyMappingsEvent event) {
            event.register(ModKeys.TOGGLE_SPEED);
            event.register(ModKeys.TOGGLE_MINE);
        }
    }
}
