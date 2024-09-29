package net.dialingspoon.speedcap.fabric.registry;

import net.dialingspoon.speedcap.SpeedCap;
import net.dialingspoon.speedcap.item.CapSettingsComponent;
import net.minecraft.core.Registry;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;

import java.util.function.UnaryOperator;

public class ModDataComponents {
    public static final DataComponentType<CapSettingsComponent> SPEEDCAP_DATA = register("speed_cap", builder -> builder.persistent(CapSettingsComponent.CODEC).networkSynchronized(CapSettingsComponent.STREAM_CODEC));

    private static <T>DataComponentType<T> register(String name, UnaryOperator<DataComponentType.Builder<T>> builderOperator) {
        return Registry.register(BuiltInRegistries.DATA_COMPONENT_TYPE, ResourceLocation.tryBuild(SpeedCap.MOD_ID, name), builderOperator.apply(DataComponentType.builder()).build());
    }

    public static void register() {
    }
}
