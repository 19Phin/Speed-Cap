package net.dialingspoon.speedcap.fabric.mixin;

import net.dialingspoon.speedcap.Util;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Player.class)
public class PlayerMixin {

    @Inject(method = "getDestroySpeed", at = @At(value = "RETURN"), cancellable = true, remap = false)
    private void slowDestroy(BlockState blockState, CallbackInfoReturnable<Float> cir) {
        ItemStack cap = Util.getActiveCap((Player)(Object)this);
        if (!cap.isEmpty() && cap.getTag().getBoolean("mineActive") && !cap.getTag().getBoolean("creative")) {
            cir.setReturnValue(Math.min(cir.getReturnValue(), cap.getTag().getFloat("mineSpeed")));
        }
    }
}