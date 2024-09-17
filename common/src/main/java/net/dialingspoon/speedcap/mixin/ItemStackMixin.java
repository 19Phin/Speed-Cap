package net.dialingspoon.speedcap.mixin;

import net.dialingspoon.speedcap.item.SpeedCapItem;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ItemStack.class)
public abstract class ItemStackMixin {

    @Shadow
    public abstract CompoundTag getOrCreateTag();

    @Shadow
    public abstract CompoundTag getTag();

    @Inject(at = @At("RETURN"), method = "<init>(Lnet/minecraft/world/level/ItemLike;I)V", remap = false)
    public void initializeData(ItemLike itemLike, int i, CallbackInfo ci) {
        if (itemLike instanceof SpeedCapItem) {
            this.getOrCreateTag().putFloat("moveSpeed", 4.3f);
            this.getTag().putFloat("mineSpeed", 4);
            this.getTag().putBoolean("moveActive", true);
            this.getTag().putBoolean("modifiable", false);
            this.getTag().putBoolean("jump", true);
            this.getTag().putBoolean("stoponadime", false);
            this.getTag().putBoolean("mineActive", true);
            this.getTag().putBoolean("creative", true);
        }
    }

}
