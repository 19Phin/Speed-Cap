package net.dialingspoon.speedcap.forge.mixin;

import net.dialingspoon.speedcap.PlatformSpecific;
import net.dialingspoon.speedcap.registry.ModItems;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.MultiPlayerGameMode;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.protocol.Packet;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(MultiPlayerGameMode.class)
public class MultiPlayerGameModeMixin {

    @Shadow private int destroyDelay;

    @Shadow @Final private Minecraft minecraft;

    @Inject(at = @At(value = "INVOKE", target = "Lnet/minecraft/client/multiplayer/MultiPlayerGameMode;destroyBlock(Lnet/minecraft/core/BlockPos;)Z"), method = {"lambda$startDestroyBlock$1"})
    public void antiInsta(BlockState blockstate1, PlayerInteractEvent.LeftClickBlock event, BlockPos arg, Direction arg2, int i, CallbackInfoReturnable<Packet> cir) {
        if (this.minecraft.player.getSlot(103).get().is(ModItems.SPEEDCAP.get()) || PlatformSpecific.IsInCurios(this.minecraft.player, ModItems.SPEEDCAP.get())) {
            destroyDelay = 5;
        }
    }
}
