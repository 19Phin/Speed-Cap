package net.dialingspoon.speedcap.neoforge.registry;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import org.lwjgl.glfw.GLFW;

public class ModKeys {
    public static final String KEY_CATEGORY_SPEEDCAP = "key.category.speedcap";
    public static KeyMapping TOGGLE_SPEED = new KeyMapping("key.speedcap.speed", InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_N, KEY_CATEGORY_SPEEDCAP);
    public static KeyMapping TOGGLE_MINE = new KeyMapping("key.speedcap.mine", InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_N, KEY_CATEGORY_SPEEDCAP);
}
