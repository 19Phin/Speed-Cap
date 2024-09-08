package net.dialingspoon.speedcap.forge.mixin;

import net.dialingspoon.speedcap.forge.client.ItemExtension;
import net.dialingspoon.speedcap.item.SpeedCapItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.function.Consumer;

@Mixin(Item.class)
public class ItemMixin {

    @Inject(at = @At("RETURN"), method = "initializeClient", remap = false)
    public void initializeClient(Consumer<IClientItemExtensions> consumer, CallbackInfo ci) {
        if ((Object) this instanceof SpeedCapItem) {
            consumer.accept(ItemExtension.INSTANCE);
        }
    }

}
