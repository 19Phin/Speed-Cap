package net.dialingspoon.speedcap.forge.registry;

import net.dialingspoon.speedcap.SpeedCap;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModCreativeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, SpeedCap.MOD_ID);
    public static final RegistryObject<CreativeModeTab> SPEEDCAP_TAB = CREATIVE_MODE_TABS.register("speedcap_tab", () -> CreativeModeTab.builder().icon(() -> createColoredCap(DyeColor.BLUE))
            .title(Component.translatable("creativetab.speedcap_tab"))
            .displayItems((pParameters, pOutput) -> {
                pOutput.accept(new ItemStack(ModItems.SPEEDCAP.get()));
                pOutput.accept(createColoredCap(DyeColor.LIGHT_GRAY));
                pOutput.accept(createColoredCap(DyeColor.GRAY));
                pOutput.accept(createColoredCap(DyeColor.BLACK));
                pOutput.accept(createColoredCap(DyeColor.BROWN));
                pOutput.accept(createColoredCap(DyeColor.RED));
                pOutput.accept(createColoredCap(DyeColor.ORANGE));
                pOutput.accept(createColoredCap(DyeColor.YELLOW));
                pOutput.accept(createColoredCap(DyeColor.LIME));
                pOutput.accept(createColoredCap(DyeColor.GREEN));
                pOutput.accept(createColoredCap(DyeColor.CYAN));
                pOutput.accept(createColoredCap(DyeColor.LIGHT_BLUE));
                pOutput.accept(createColoredCap(DyeColor.BLUE));
                pOutput.accept(createColoredCap(DyeColor.PURPLE));
                pOutput.accept(createColoredCap(DyeColor.MAGENTA));
                pOutput.accept(createColoredCap(DyeColor.PINK));
            })
            .build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }

    private static ItemStack createColoredCap(DyeColor color) {
        ItemStack cap = new ItemStack(ModItems.SPEEDCAP.get());
        float[] textureDiffuseColors = color.getTextureDiffuseColors();

        int r = (int) (textureDiffuseColors[0] * 255.0F);
        int g = (int) (textureDiffuseColors[1] * 255.0F);
        int b = (int) (textureDiffuseColors[2] * 255.0F);

        int colorhash = (r << 16) | (g << 8) | b;
        ModItems.SPEEDCAP.get().setColor(cap, colorhash);
        return cap;
    }
}
