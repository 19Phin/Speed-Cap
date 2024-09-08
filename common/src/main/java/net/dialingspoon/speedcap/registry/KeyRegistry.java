package net.dialingspoon.speedcap.registry;

import com.mojang.blaze3d.platform.InputConstants;
import dev.architectury.event.events.client.ClientTickEvent;
import dev.architectury.registry.client.keymappings.KeyMappingRegistry;
import net.minecraft.client.KeyMapping;
import org.lwjgl.glfw.GLFW;

public class KeyRegistry {
    public static boolean isActive = true;
    public static boolean dime = true;
    public static final String KEY_CATEGORY_SPEEDCAP = "key.category.speedcap";
    public static KeyMapping TOGGLE_KEY = new KeyMapping("key.speedcap.active", InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_N, KEY_CATEGORY_SPEEDCAP);
    public static KeyMapping STOPONADIME = new KeyMapping("key.speedcap.dime", InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_N, KEY_CATEGORY_SPEEDCAP);

    public static void register() {
        KeyMappingRegistry.register(TOGGLE_KEY);
        ClientTickEvent.CLIENT_POST.register(minecraft -> {
            while (KeyRegistry.TOGGLE_KEY.consumeClick()) {
                isActive = !isActive;
            }
        });
        KeyMappingRegistry.register(STOPONADIME);
        ClientTickEvent.CLIENT_POST.register(minecraft -> {
            while (KeyRegistry.STOPONADIME.consumeClick()) {
                dime = !dime;
            }
        });
    }
}
