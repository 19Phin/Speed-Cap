package net.dialingspoon.speedcap.neoforge.client;

import net.dialingspoon.speedcap.SpeedCap;
import net.dialingspoon.speedcap.Util;
import net.dialingspoon.speedcap.item.SpeedCapItem;
import net.dialingspoon.speedcap.models.CapModel;
import net.dialingspoon.speedcap.neoforge.networking.CapKeybindPacket;
import net.dialingspoon.speedcap.neoforge.networking.Packets;
import net.dialingspoon.speedcap.neoforge.registry.ModItems;
import net.dialingspoon.speedcap.neoforge.registry.ModKeys;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.geom.LayerDefinitions;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.event.InputEvent;
import net.neoforged.neoforge.client.event.RegisterColorHandlersEvent;
import net.neoforged.neoforge.client.event.RegisterKeyMappingsEvent;

@SuppressWarnings("unused")
public class SpeedCapNeoForgeClientEvents {
    @Mod.EventBusSubscriber(modid = SpeedCap.MOD_ID, value = Dist.CLIENT)
    public static class ClientNeoForgeEvents {
        @SubscribeEvent
        public static void onKey(InputEvent.Key event) {
            if (ModKeys.TOGGLE_SPEED.consumeClick()) {
                ItemStack cap = Util.getActiveCap(Minecraft.getInstance().player);
                if (!cap.isEmpty()) {
                    Packets.sendToServer(new CapKeybindPacket(true));
                }
            }
            if (ModKeys.TOGGLE_MINE.consumeClick()) {
                ItemStack cap = Util.getActiveCap(Minecraft.getInstance().player);
                if (!cap.isEmpty()) {
                    Packets.sendToServer(new CapKeybindPacket(false));
                }
            }
        }
    }


    @Mod.EventBusSubscriber(modid = SpeedCap.MOD_ID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class ClientModBusEvents {
        private static final ModelLayerLocation LAYER = new ModelLayerLocation(ResourceLocation.tryBuild(SpeedCap.MOD_ID, "speedcap"), "main");

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
        public static void initColors(RegisterColorHandlersEvent.Item event) {
            event.register((itemStack, layer) -> layer > 0 ? -1 : ((SpeedCapItem) itemStack.getItem()).getColor(itemStack), ModItems.SPEEDCAP.get());
        }

        @SubscribeEvent
        public static void initKeys(RegisterKeyMappingsEvent event) {
            event.register(ModKeys.TOGGLE_SPEED);
            event.register(ModKeys.TOGGLE_MINE);
        }
    }
}
