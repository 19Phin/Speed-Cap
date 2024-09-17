package net.dialingspoon.speedcap.mixin;

import net.dialingspoon.speedcap.gui.SpeedCapScreen;
import net.dialingspoon.speedcap.registry.ModMenuTypes;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.inventory.MenuAccess;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MenuScreens.class)
public abstract class MenuScreensMixin {

    @Shadow
    private static <M extends AbstractContainerMenu, U extends Screen & MenuAccess<M>> void register(MenuType<? extends M> arg, MenuScreens.ScreenConstructor<M, U> arg2) {}

    @Inject(method = "<clinit>", at = @At(value = "TAIL"))
    private static void register(CallbackInfo ci) {
        register(ModMenuTypes.SPEEDCAP.get(), SpeedCapScreen::new);
    }
}
