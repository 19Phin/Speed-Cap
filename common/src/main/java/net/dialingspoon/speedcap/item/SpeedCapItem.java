package net.dialingspoon.speedcap.item;

import dev.architectury.injectables.annotations.PlatformOnly;
import net.dialingspoon.speedcap.PlatformSpecific;
import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.util.FastColor;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import java.util.function.Consumer;

public class SpeedCapItem extends ArmorItem {
    public static final Item.Properties DEFAULT_PROPERTIES = new Item.Properties().durability(11 * 15).component(PlatformSpecific.getDataComponent(), new CapSettingsComponent(4.8f, 4, true, false, true, false, true, true));
    public static final int DEFAULT_COLOR = -1;

    public SpeedCapItem(Holder<ArmorMaterial> material, Type type, Properties settings) {
        super(material, type, settings);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand interactionHand) {
        if(player.isShiftKeyDown()) {
            player.openMenu(new SimpleMenuProvider((i, playerInventory, playerEntity) ->
                    PlatformSpecific.getMenu().create(i, playerInventory), Component.literal("Speed Cap")));
            return InteractionResultHolder.pass(player.getItemInHand(interactionHand));
        }
        return super.use(level, player, interactionHand);
    }

    /**
     *This @Overrides Item.initializeClient(Consumer<IClientItemExtensions>) through type-erasure shenanigans.
     */
    @PlatformOnly({PlatformOnly.FORGE, "neoforge"})
    public void initializeClient(Consumer<Object> consumer) {
        consumer.accept(PlatformSpecific.itemExtension());
    }
}
