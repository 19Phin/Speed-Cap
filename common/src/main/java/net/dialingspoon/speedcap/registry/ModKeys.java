package net.dialingspoon.speedcap.registry;

import com.mojang.blaze3d.platform.InputConstants;
import dev.architectury.event.events.client.ClientTickEvent;
import dev.architectury.registry.client.keymappings.KeyMappingRegistry;
import net.dialingspoon.speedcap.Util;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.world.item.ItemStack;
import org.lwjgl.glfw.GLFW;

public class ModKeys {
    public static final String KEY_CATEGORY_SPEEDCAP = "key.category.speedcap";
    public static KeyMapping TOGGLE_SPEED = new KeyMapping("key.speedcap.speed", InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_N, KEY_CATEGORY_SPEEDCAP);
    public static KeyMapping TOGGLE_MINE = new KeyMapping("key.speedcap.mine", InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_N, KEY_CATEGORY_SPEEDCAP);

    public static void register() {
        KeyMappingRegistry.register(TOGGLE_SPEED);
        ClientTickEvent.CLIENT_POST.register(minecraft -> {
            while (ModKeys.TOGGLE_SPEED.consumeClick()) {
                ItemStack cap = Util.getActiveCap(Minecraft.getInstance().player);
                if (!cap.isEmpty()) {
                    cap.getTag().putBoolean("moveActive", !cap.getTag().getBoolean("moveActive"));
                }
            }
        });
        KeyMappingRegistry.register(TOGGLE_MINE);
        ClientTickEvent.CLIENT_POST.register(minecraft -> {
            while (ModKeys.TOGGLE_MINE.consumeClick()) {
                ItemStack cap = Util.getActiveCap(Minecraft.getInstance().player);
                if (!cap.isEmpty()) {
                    cap.getTag().putBoolean("mineActive", cap.getTag().getBoolean("mineActive"));
                }
            }
        });
    }
}
