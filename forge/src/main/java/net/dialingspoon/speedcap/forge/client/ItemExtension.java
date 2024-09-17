package net.dialingspoon.speedcap.forge.client;

import net.dialingspoon.speedcap.render.CapModel;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;
import org.jetbrains.annotations.NotNull;

public class ItemExtension implements IClientItemExtensions {

    public static final ItemExtension INSTANCE = new ItemExtension();

    private ItemExtension() {}

    @Override
    @NotNull
    public HumanoidModel<?> getHumanoidArmorModel(LivingEntity livingEntity, ItemStack itemStack, EquipmentSlot equipmentSlot, HumanoidModel<?> original) {
        CapModel<LivingEntity> model = SpeedCapForgeClientEvents.capModel;
        model.setupAnim(livingEntity);
        return model;
    }


}
