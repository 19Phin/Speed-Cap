package net.dialingspoon.speedcap.forge.client;

import net.dialingspoon.speedcap.models.CapModel;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;

public class ItemExtension implements IClientItemExtensions {

    @Override
    public HumanoidModel<?> getHumanoidArmorModel(LivingEntity livingEntity, ItemStack itemStack, EquipmentSlot equipmentSlot, HumanoidModel<?> original) {
        CapModel<LivingEntity> model = SpeedCapForgeClientEvents.ClientModBusEvents.capModel;
        model.setupAnim(livingEntity);
        return model;
    }
}
