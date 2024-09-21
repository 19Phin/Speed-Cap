package net.dialingspoon.speedcap.models;

import net.dialingspoon.speedcap.models.KeyframeList.Type;
import org.joml.Vector3f;

import java.util.HashMap;
import java.util.Map;

/**
 * Made with Blockbench 4.10.4
 * @author Dialingspoon
 */
public class ModelAnimations {

	public static final Map<String, KeyframeList> OPEN = new HashMap<>();
	static {
		OPEN.put("TR", new KeyframeList(
				new KeyframeList.Keyframe(Type.ROTATION, 0, new Vector3f(0,0,135)),
				new KeyframeList.Keyframe(Type.ROTATION, 1, new Vector3f(0,0,0)),
				new KeyframeList.Keyframe(Type.POSITION, 0, new Vector3f(-0.1F,-0.1F,0)),
				new KeyframeList.Keyframe(Type.POSITION, 0.05F, new Vector3f(0,0,0))));
		OPEN.put("TL", new KeyframeList(
				new KeyframeList.Keyframe(Type.ROTATION, 0, new Vector3f(0,0,-135)),
				new KeyframeList.Keyframe(Type.ROTATION, 1, new Vector3f(0,0,0)),
				new KeyframeList.Keyframe(Type.POSITION, 0, new Vector3f(0.1F,-0.1F,0)),
				new KeyframeList.Keyframe(Type.POSITION, 0.05F, new Vector3f(0,0,0))));
		OPEN.put("BL", new KeyframeList(
				new KeyframeList.Keyframe(Type.ROTATION, 0, new Vector3f(0,0,-135)),
				new KeyframeList.Keyframe(Type.ROTATION, 1, new Vector3f(0,0,0)),
				new KeyframeList.Keyframe(Type.POSITION, 0, new Vector3f(0.1F,0,0)),
				new KeyframeList.Keyframe(Type.POSITION, 0.05F, new Vector3f(0,0,0))));
		OPEN.put("BR", new KeyframeList(
				new KeyframeList.Keyframe(Type.ROTATION, 0, new Vector3f(0,0,135)),
				new KeyframeList.Keyframe(Type.ROTATION, 1, new Vector3f(0,0,0)),
				new KeyframeList.Keyframe(Type.POSITION, 0, new Vector3f(-0.1F,0,0)),
				new KeyframeList.Keyframe(Type.POSITION, 0.05F, new Vector3f(0,0,0))));
		OPEN.put("fabric", new KeyframeList(
				new KeyframeList.Keyframe(Type.SCALE, 0, new Vector3f(0,0,0)),
				new KeyframeList.Keyframe(Type.SCALE, 0.05F, new Vector3f(1,1,1))));
		OPEN.put("fabric2", new KeyframeList(
				new KeyframeList.Keyframe(Type.SCALE, 0, new Vector3f(0,0,0)),
				new KeyframeList.Keyframe(Type.SCALE, 0.05F, new Vector3f(1,1,1))));
		OPEN.put("fabric3", new KeyframeList(
				new KeyframeList.Keyframe(Type.SCALE, 0, new Vector3f(0,0,0)),
				new KeyframeList.Keyframe(Type.SCALE, 0.05F, new Vector3f(1,1,1))));
		OPEN.put("fabric4", new KeyframeList(
				new KeyframeList.Keyframe(Type.SCALE, 0, new Vector3f(0,0,0)),
				new KeyframeList.Keyframe(Type.SCALE, 0.05F, new Vector3f(1,1,1))));
		OPEN.put("nub", new KeyframeList(
				new KeyframeList.Keyframe(Type.SCALE, 0, new Vector3f(0,0,0)),
				new KeyframeList.Keyframe(Type.SCALE, 0.65F, new Vector3f(0,0,0)),
				new KeyframeList.Keyframe(Type.SCALE, 0.7F, new Vector3f(1,1,1))));
		OPEN.put("nub2", new KeyframeList(
				new KeyframeList.Keyframe(Type.SCALE, 0, new Vector3f(0,0,0)),
				new KeyframeList.Keyframe(Type.SCALE, 0.65F, new Vector3f(0,0,0)),
				new KeyframeList.Keyframe(Type.SCALE, 0.7F, new Vector3f(1,1,1))));
		OPEN.put("nub3", new KeyframeList(
				new KeyframeList.Keyframe(Type.SCALE, 0, new Vector3f(0,0,0)),
				new KeyframeList.Keyframe(Type.SCALE, 0.65F, new Vector3f(0,0,0)),
				new KeyframeList.Keyframe(Type.SCALE, 0.7F, new Vector3f(1,1,1))));
		OPEN.put("nub4", new KeyframeList(
				new KeyframeList.Keyframe(Type.SCALE, 0, new Vector3f(0,0,0)),
				new KeyframeList.Keyframe(Type.SCALE, 0.65F, new Vector3f(0,0,0)),
				new KeyframeList.Keyframe(Type.SCALE, 0.7F, new Vector3f(1,1,1))));
		OPEN.put("flap", new KeyframeList(
				new KeyframeList.Keyframe(Type.SCALE, 0, new Vector3f(0,0,0)),
				new KeyframeList.Keyframe(Type.SCALE, 0.65F, new Vector3f(0,0,0)),
				new KeyframeList.Keyframe(Type.SCALE, 0.7F, new Vector3f(1,1,1)),
				new KeyframeList.Keyframe(Type.POSITION, 0, new Vector3f(0,3.0F,0)),
				new KeyframeList.Keyframe(Type.POSITION, 0.7F, new Vector3f(0,3.0F,0)),
				new KeyframeList.Keyframe(Type.POSITION, 1, new Vector3f(0,0,0))));
		OPEN.put("flap2", new KeyframeList(
				new KeyframeList.Keyframe(Type.SCALE, 0, new Vector3f(0,0,0)),
				new KeyframeList.Keyframe(Type.SCALE, 0.65F, new Vector3f(0,0,0)),
				new KeyframeList.Keyframe(Type.SCALE, 0.7F, new Vector3f(1,1,1)),
				new KeyframeList.Keyframe(Type.POSITION, 0, new Vector3f(0,3.0F,0)),
				new KeyframeList.Keyframe(Type.POSITION, 0.7F, new Vector3f(0,3.0F,0)),
				new KeyframeList.Keyframe(Type.POSITION, 1, new Vector3f(0,0,0))));
		OPEN.put("flap3", new KeyframeList(
				new KeyframeList.Keyframe(Type.SCALE, 0, new Vector3f(0,0,0)),
				new KeyframeList.Keyframe(Type.SCALE, 0.5F, new Vector3f(0,0,0)),
				new KeyframeList.Keyframe(Type.SCALE, 0.55F, new Vector3f(1,1,1))));
		OPEN.put("flap4", new KeyframeList(
				new KeyframeList.Keyframe(Type.SCALE, 0, new Vector3f(0,0,0)),
				new KeyframeList.Keyframe(Type.SCALE, 0.5F, new Vector3f(0,0,0)),
				new KeyframeList.Keyframe(Type.SCALE, 0.55F, new Vector3f(1,1,1))));
	}

	public static final Map<String, KeyframeList> CLOSE = new HashMap<>();
	static {
		CLOSE.put("TR", new KeyframeList(
				new KeyframeList.Keyframe(Type.ROTATION, 1, new Vector3f(0,0,135)),
				new KeyframeList.Keyframe(Type.ROTATION, 0, new Vector3f(0,0,0)),
				new KeyframeList.Keyframe(Type.POSITION, 1, new Vector3f(-0.1F,-0.1F,0)),
				new KeyframeList.Keyframe(Type.POSITION, 0.95F, new Vector3f(0,0,0)),
				new KeyframeList.Keyframe(Type.POSITION, 0, new Vector3f(0,0,0))));
		CLOSE.put("TL", new KeyframeList(
				new KeyframeList.Keyframe(Type.ROTATION, 1, new Vector3f(0,0,-135)),
				new KeyframeList.Keyframe(Type.ROTATION, 0, new Vector3f(0,0,0)),
				new KeyframeList.Keyframe(Type.POSITION, 1, new Vector3f(0.1F,-0.1F,0)),
				new KeyframeList.Keyframe(Type.POSITION, 0.95F, new Vector3f(0,0,0)),
				new KeyframeList.Keyframe(Type.POSITION, 0, new Vector3f(0,0,0))));
		CLOSE.put("BL", new KeyframeList(
				new KeyframeList.Keyframe(Type.ROTATION, 1, new Vector3f(0,0,-135)),
				new KeyframeList.Keyframe(Type.ROTATION, 0, new Vector3f(0,0,0)),
				new KeyframeList.Keyframe(Type.POSITION, 1, new Vector3f(0.1F,0,0)),
				new KeyframeList.Keyframe(Type.POSITION, 0.95F, new Vector3f(0,0,0)),
				new KeyframeList.Keyframe(Type.POSITION, 0, new Vector3f(0,0,0))));
		CLOSE.put("BR", new KeyframeList(
				new KeyframeList.Keyframe(Type.ROTATION, 1, new Vector3f(0,0,135)),
				new KeyframeList.Keyframe(Type.ROTATION, 0, new Vector3f(0,0,0)),
				new KeyframeList.Keyframe(Type.POSITION, 1, new Vector3f(-0.1F,0,0)),
				new KeyframeList.Keyframe(Type.POSITION, 0.95F, new Vector3f(0,0,0)),
				new KeyframeList.Keyframe(Type.POSITION, 0, new Vector3f(0,0,0))));
		CLOSE.put("fabric", new KeyframeList(
				new KeyframeList.Keyframe(Type.SCALE, 1, new Vector3f(0,0,0)),
				new KeyframeList.Keyframe(Type.SCALE, 0.95F, new Vector3f(1,1,1)),
				new KeyframeList.Keyframe(Type.SCALE, 0, new Vector3f(1,1,1))));
		CLOSE.put("fabric2", new KeyframeList(
				new KeyframeList.Keyframe(Type.SCALE, 1, new Vector3f(0,0,0)),
				new KeyframeList.Keyframe(Type.SCALE, 0.95F, new Vector3f(1,1,1)),
				new KeyframeList.Keyframe(Type.SCALE, 0, new Vector3f(1,1,1))));
		CLOSE.put("fabric3", new KeyframeList(
				new KeyframeList.Keyframe(Type.SCALE, 1, new Vector3f(0,0,0)),
				new KeyframeList.Keyframe(Type.SCALE, 0.95F, new Vector3f(1,1,1)),
				new KeyframeList.Keyframe(Type.SCALE, 0, new Vector3f(1,1,1))));
		CLOSE.put("fabric4", new KeyframeList(
				new KeyframeList.Keyframe(Type.SCALE, 1, new Vector3f(0,0,0)),
				new KeyframeList.Keyframe(Type.SCALE, 0.95F, new Vector3f(1,1,1)),
				new KeyframeList.Keyframe(Type.SCALE, 0, new Vector3f(1,1,1))));
		CLOSE.put("nub", new KeyframeList(
				new KeyframeList.Keyframe(Type.SCALE, 0.35F, new Vector3f(0,0,0)),
				new KeyframeList.Keyframe(Type.SCALE, 0.3F, new Vector3f(1,1,1)),
				new KeyframeList.Keyframe(Type.SCALE, 0, new Vector3f(1,1,1))));
		CLOSE.put("nub2", new KeyframeList(
				new KeyframeList.Keyframe(Type.SCALE, 0.35F, new Vector3f(0,0,0)),
				new KeyframeList.Keyframe(Type.SCALE, 0.3F, new Vector3f(1,1,1)),
				new KeyframeList.Keyframe(Type.SCALE, 0, new Vector3f(1,1,1))));
		CLOSE.put("nub3", new KeyframeList(
				new KeyframeList.Keyframe(Type.SCALE, 0.35F, new Vector3f(0,0,0)),
				new KeyframeList.Keyframe(Type.SCALE, 0.3F, new Vector3f(1,1,1)),
				new KeyframeList.Keyframe(Type.SCALE, 0, new Vector3f(1,1,1))));
		CLOSE.put("nub4", new KeyframeList(
				new KeyframeList.Keyframe(Type.SCALE, 0.35F, new Vector3f(0,0,0)),
				new KeyframeList.Keyframe(Type.SCALE, 0.3F, new Vector3f(1,1,1)),
				new KeyframeList.Keyframe(Type.SCALE, 0, new Vector3f(1,1,1))));
		CLOSE.put("flap", new KeyframeList(
				new KeyframeList.Keyframe(Type.SCALE, 0.35F, new Vector3f(0,0,0)),
				new KeyframeList.Keyframe(Type.SCALE, 0.3F, new Vector3f(1,1,1)),
				new KeyframeList.Keyframe(Type.SCALE, 0, new Vector3f(1,1,1)),
				new KeyframeList.Keyframe(Type.POSITION, 0.3F, new Vector3f(0,3.0F,0)),
				new KeyframeList.Keyframe(Type.POSITION, 0, new Vector3f(0,0,0)),
				new KeyframeList.Keyframe(Type.POSITION, 0, new Vector3f(0,0,0))));
		CLOSE.put("flap2", new KeyframeList(
				new KeyframeList.Keyframe(Type.SCALE, 0.35F, new Vector3f(0,0,0)),
				new KeyframeList.Keyframe(Type.SCALE, 0.3F, new Vector3f(1,1,1)),
				new KeyframeList.Keyframe(Type.SCALE, 0, new Vector3f(1,1,1)),
				new KeyframeList.Keyframe(Type.POSITION, 0.3F, new Vector3f(0,3.0F,0)),
				new KeyframeList.Keyframe(Type.POSITION, 0, new Vector3f(0,0,0)),
				new KeyframeList.Keyframe(Type.POSITION, 0, new Vector3f(0,0,0))));
		CLOSE.put("flap3", new KeyframeList(
				new KeyframeList.Keyframe(Type.SCALE, 0.5F, new Vector3f(0,0,0)),
				new KeyframeList.Keyframe(Type.SCALE, 0.45F, new Vector3f(1,1,1)),
				new KeyframeList.Keyframe(Type.SCALE, 0, new Vector3f(1,1,1))));
		CLOSE.put("flap4", new KeyframeList(
				new KeyframeList.Keyframe(Type.SCALE, 0.5F, new Vector3f(0,0,0)),
				new KeyframeList.Keyframe(Type.SCALE, 0.45F, new Vector3f(1,1,1)),
				new KeyframeList.Keyframe(Type.SCALE, 0, new Vector3f(1,1,1))));
	}
}