package net.dialingspoon.speedcap.neoforge.mixin;

import net.dialingspoon.speedcap.Util;
import net.dialingspoon.speedcap.interfaces.EntityInterface;
import net.dialingspoon.speedcap.item.CapSettingsComponent;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.MultiPlayerGameMode;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.protocol.Packet;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(MultiPlayerGameMode.class)
public class MultiPlayerGameModeMixin {
    @Shadow
    private int destroyDelay;

    @Shadow
    @Final private Minecraft minecraft;

    @Inject(at = @At(value = "INVOKE", target = "Lnet/minecraft/client/multiplayer/MultiPlayerGameMode;destroyBlock(Lnet/minecraft/core/BlockPos;)Z"), method = {"lambda$startDestroyBlock$1"})
    public void delayDestroy(BlockState blockstate1, PlayerInteractEvent.LeftClickBlock event, BlockPos arg, Direction arg2, int i, CallbackInfoReturnable<Packet> cir) {
        ItemStack cap = Util.getActiveCap(minecraft.player);
        CapSettingsComponent data = ((EntityInterface) minecraft.player).speedcap$getData();
        if (!cap.isEmpty() && data.mineActive() && data.creative()) {
            destroyDelay = (int)((1 / data.mineSpeed()) * 20);
        }
    }
}
