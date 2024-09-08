// Made with Blockbench 4.10.4
// Exported for Minecraft version 1.17 or later with Mojang mappings
package net.dialingspoon.speedcap.render;

import net.dialingspoon.speedcap.interfaces.EntityInterface;
import net.dialingspoon.speedcap.registry.ModItems;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.world.entity.LivingEntity;
import org.joml.Vector3f;

import java.util.HashMap;
import java.util.Map;

public class SailModel<T extends LivingEntity> extends HumanoidModel<T> {

	public final ModelPart root;
	public final Map<String, ModelPart> modelParts;

	public SailModel(ModelPart root) {
		super(root, RenderType::entityTranslucent);
		this.setAllVisible(false);
		this.root = root;

		this.modelParts = new HashMap<>();
		this.modelParts.put("hat", root.getChild("hat"));
		this.modelParts.put("TR", modelParts.get("hat").getChild("TR"));
		this.modelParts.put("nub", modelParts.get("TR").getChild("nub"));
		this.modelParts.put("fabric2", modelParts.get("TR").getChild("fabric2"));
		this.modelParts.put("flap3", modelParts.get("TR").getChild("flap3"));
		this.modelParts.put("TL", modelParts.get("hat").getChild("TL"));
		this.modelParts.put("fabric", modelParts.get("TL").getChild("fabric"));
		this.modelParts.put("flap4", modelParts.get("TL").getChild("flap4"));
		this.modelParts.put("nub2", modelParts.get("TL").getChild("nub2"));
		this.modelParts.put("BL", modelParts.get("hat").getChild("BL"));
		this.modelParts.put("fabric3", modelParts.get("BL").getChild("fabric3"));
		this.modelParts.put("flap2", modelParts.get("fabric3").getChild("flap2"));
		this.modelParts.put("nub3", modelParts.get("BL").getChild("nub3"));
		this.modelParts.put("BR", modelParts.get("hat").getChild("BR"));
		this.modelParts.put("nub4", modelParts.get("BR").getChild("nub4"));
		this.modelParts.put("fabric4", modelParts.get("BR").getChild("fabric4"));
		this.modelParts.put("flap", modelParts.get("fabric4").getChild("flap"));

		modelParts.values().forEach(part -> part.visible = true);
		this.head.visible = true;
	}

	public static LayerDefinition createLayer(CubeDeformation deform) {
		MeshDefinition mesh = HumanoidModel.createMesh(deform, 0.0F);
		PartDefinition root = mesh.getRoot();
		PartDefinition hat = root.getChild("hat");

		PartDefinition TR = hat.addOrReplaceChild("TR", CubeListBuilder.create(), PartPose.offset(-5.0F, -9.0F, 0.0F));

		PartDefinition cube_r1 = TR.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(58, 23).mirror().addBox(0.0F, -5.0F, -1.0F, 1.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.7854F));

		PartDefinition nub = TR.addOrReplaceChild("nub", CubeListBuilder.create(), PartPose.offset(0.8485F, 0.8485F, 0.0F));

		PartDefinition cube_r2 = nub.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(58, 23).mirror().addBox(0.0F, -1.2F, -1.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.7854F));

		PartDefinition fabric2 = TR.addOrReplaceChild("fabric2", CubeListBuilder.create().texOffs(6, 26).mirror().addBox(0.0F, 1.0F, -1.0F, 2.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false)
				.texOffs(2, 22).mirror().addBox(-2.0F, -1.0F, -1.0F, 6.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false)
				.texOffs(4, 24).mirror().addBox(-1.0F, 0.0F, -1.0F, 4.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-1.0F, -3.0F, 1.0F));

		PartDefinition flap3 = TR.addOrReplaceChild("flap3", CubeListBuilder.create().texOffs(10, 26).mirror().addBox(2.0F, 1.0F, -1.0F, 4.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false)
				.texOffs(8, 27).mirror().addBox(1.0F, 2.0F, -1.0F, 5.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false)
				.texOffs(14, 22).mirror().addBox(4.0F, -1.0F, -1.0F, 2.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false)
				.texOffs(12, 24).mirror().addBox(3.0F, 0.0F, -1.0F, 3.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-1.0F, -3.0F, 1.0F));

		PartDefinition TL = hat.addOrReplaceChild("TL", CubeListBuilder.create(), PartPose.offset(5.0F, -9.0F, 0.0F));

		PartDefinition cube_r3 = TL.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(58, 23).addBox(-1.0F, -5.0F, -1.0F, 1.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.7854F));

		PartDefinition fabric = TL.addOrReplaceChild("fabric", CubeListBuilder.create().texOffs(24, 24).addBox(-4.0F, 1.0F, -0.999F, 4.0F, 1.0F, 0.0F, new CubeDeformation(0.0F))
				.texOffs(26, 26).addBox(-3.0F, 2.0F, -0.999F, 2.0F, 1.0F, 0.0F, new CubeDeformation(0.0F))
				.texOffs(22, 22).addBox(-5.0F, 0.0F, -0.999F, 6.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(2.0F, -4.0F, 1.0F));

		PartDefinition flap4 = TL.addOrReplaceChild("flap4", CubeListBuilder.create().texOffs(18, 24).addBox(-7.0F, 1.0F, -0.999F, 3.0F, 1.0F, 0.0F, new CubeDeformation(0.0F))
				.texOffs(18, 26).addBox(-7.0F, 2.0F, -0.999F, 4.0F, 1.0F, 0.0F, new CubeDeformation(0.0F))
				.texOffs(18, 27).addBox(-7.0F, 3.0F, -0.999F, 5.0F, 1.0F, 0.0F, new CubeDeformation(0.0F))
				.texOffs(18, 22).addBox(-7.0F, 0.0F, -0.999F, 2.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(2.0F, -4.0F, 1.0F));

		PartDefinition nub2 = TL.addOrReplaceChild("nub2", CubeListBuilder.create(), PartPose.offset(-0.8485F, 0.8485F, 0.0F));

		PartDefinition cube_r4 = nub2.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(58, 23).addBox(-1.0F, -1.2F, -1.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.7854F));

		PartDefinition BL = hat.addOrReplaceChild("BL", CubeListBuilder.create(), PartPose.offset(5.0F, -4.0F, 0.0F));

		PartDefinition cube_r5 = BL.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(58, 23).addBox(-1.0F, -4.0F, -1.0F, 1.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 2.3562F));

		PartDefinition fabric3 = BL.addOrReplaceChild("fabric3", CubeListBuilder.create().texOffs(34, 27).addBox(-1.0F, -3.0F, 0.0F, 1.0F, 5.0F, 0.0F, new CubeDeformation(0.0F))
				.texOffs(32, 28).addBox(-2.0F, -3.0F, 0.0F, 1.0F, 4.0F, 0.0F, new CubeDeformation(0.0F))
				.texOffs(30, 29).addBox(-3.0F, -3.0F, 0.0F, 1.0F, 3.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(3.0F, 0.0F, 0.0F));

		PartDefinition flap2 = fabric3.addOrReplaceChild("flap2", CubeListBuilder.create().texOffs(34, 22).addBox(2.0F, -3.0F, 0.0F, 1.0F, 5.0F, 0.0F, new CubeDeformation(0.0F))
				.texOffs(32, 24).addBox(1.0F, -2.0F, 0.0F, 1.0F, 4.0F, 0.0F, new CubeDeformation(0.0F))
				.texOffs(30, 26).addBox(0.0F, -1.0F, 0.0F, 1.0F, 3.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(-3.0F, -5.0F, 0.0F));

		PartDefinition nub3 = BL.addOrReplaceChild("nub3", CubeListBuilder.create(), PartPose.offset(-0.7071F, -0.7071F, 0.0F));

		PartDefinition cube_r6 = nub3.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(58, 23).addBox(-1.0F, -1.0F, -1.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 2.3562F));

		PartDefinition BR = hat.addOrReplaceChild("BR", CubeListBuilder.create(), PartPose.offset(-5.0F, -4.0F, 0.0F));

		PartDefinition cube_r7 = BR.addOrReplaceChild("cube_r7", CubeListBuilder.create().texOffs(58, 23).mirror().addBox(0.0F, -4.0F, -1.0F, 1.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -2.3562F));

		PartDefinition nub4 = BR.addOrReplaceChild("nub4", CubeListBuilder.create(), PartPose.offset(0.7071F, -0.7071F, 0.0F));

		PartDefinition cube_r8 = nub4.addOrReplaceChild("cube_r8", CubeListBuilder.create().texOffs(58, 23).mirror().addBox(0.0F, -1.0F, -1.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -2.3562F));

		PartDefinition fabric4 = BR.addOrReplaceChild("fabric4", CubeListBuilder.create().texOffs(4, 29).mirror().addBox(2.0F, -3.0F, 0.0F, 1.0F, 3.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false)
				.texOffs(0, 27).mirror().addBox(0.0F, -3.0F, 0.0F, 1.0F, 5.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false)
				.texOffs(2, 28).mirror().addBox(1.0F, -3.0F, 0.0F, 1.0F, 4.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-3.0F, 0.0F, 0.0F));

		PartDefinition flap = fabric4.addOrReplaceChild("flap", CubeListBuilder.create().texOffs(4, 26).mirror().addBox(2.0F, -1.0F, 0.0F, 1.0F, 3.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false)
				.texOffs(2, 24).mirror().addBox(1.0F, -2.0F, 0.0F, 1.0F, 4.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false)
				.texOffs(0, 22).mirror().addBox(0.0F, -3.0F, 0.0F, 1.0F, 5.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.0F, -5.0F, 0.0F));

		return LayerDefinition.create(mesh, 64, 32);
	}



	public void setupAnim(LivingEntity livingEntity) {
		head.visible = livingEntity.getSlot(103).get().isEmpty() || livingEntity.getSlot(103).get().is(ModItems.SPEEDCAP.get());

		EntityInterface entityMixin = (EntityInterface) livingEntity;
		boolean direction = entityMixin.speedcap$getSailDirection();
		float localTick = entityMixin.speedcap$getSailTick();

		float tick = Minecraft.getInstance().level.getGameTime() + Minecraft.getInstance().getFrameTime();
		boolean isSpeeding = ((EntityInterface)livingEntity).speedcap$isSpeeding();

		if (isSpeeding != direction) {
			entityMixin.speedcap$setSailTick(localTick = tick - ((1 - Math.min((tick - localTick) / 10, 1)) * 10));
			entityMixin.speedcap$setSailDirection(isSpeeding);
		}

		SailModel.runAnimation(isSpeeding ? ModelAnimations.OPEN : ModelAnimations.CLOSE, modelParts, Math.min((tick - localTick) / 10, 1));
	}

	public static void runAnimation(Map<String, KeyframeList> animation, Map<String, ModelPart> parts, float progress) {
		for (Map.Entry<String, KeyframeList> entry : animation.entrySet()) {
			ModelPart part = parts.get(entry.getKey());
			if (part == null) {
				throw new IllegalArgumentException("ModelPart \"" + entry.getKey() + "\" not found");
			}

			KeyframeList.Keyframe pos1 = null, pos2 = null;
			KeyframeList.Keyframe rot1 = null, rot2 = null;
			KeyframeList.Keyframe sca1 = null, sca2 = null;

			for (KeyframeList.Keyframe frame : entry.getValue().keyframes) {
				switch (frame.getType()) {
					case POSITION:
						if (pos2 != null) break;
						pos1 = pos1 == null || progress >= frame.getStartTime() ? frame : pos1;
						pos2 = progress < frame.getStartTime() ? frame : null;
						break;
					case ROTATION:
						if (rot2 != null) break;
						rot1 = rot1 == null || progress >= frame.getStartTime() ? frame : rot1;
						rot2 = progress < frame.getStartTime() ? frame : null;
						break;
					case SCALE:
						if (sca2 != null) break;
						sca1 = sca1 == null || progress >= frame.getStartTime() ? frame : sca1;
						sca2 = progress < frame.getStartTime() ? frame : null;
						break;
				}
			}

			applyInterpolation(part, progress, pos1, pos2, KeyframeList.Type.POSITION);
			applyInterpolation(part, progress, rot1, rot2, KeyframeList.Type.ROTATION);
			applyInterpolation(part, progress, sca1, sca2, KeyframeList.Type.SCALE);
		}
	}

	private static void applyInterpolation(ModelPart part, float progress, KeyframeList.Keyframe keyframe1, KeyframeList.Keyframe keyframe2, KeyframeList.Type type) {
		if (keyframe1 != null) {
			if (keyframe2 == null) {
				setModelPartValue(part, keyframe1.getTransform(), type);
			} else {
				float duration = keyframe2.getStartTime() - keyframe1.getStartTime();
				float progressRatio = (progress - keyframe1.getStartTime()) / duration;
				Vector3f interpolatedValue = interpolate(progressRatio, keyframe1.getTransform(), keyframe2.getTransform());
				setModelPartValue(part, interpolatedValue, type);
			}
		}
	}

	private static void setModelPartValue(ModelPart part, Vector3f transform, KeyframeList.Type type) {
		switch (type) {
			case POSITION:
				part.setPos(part.getInitialPose().x + transform.x, part.getInitialPose().y + transform.y, part.getInitialPose().z + transform.z);
				break;
			case ROTATION:
				part.setRotation(part.getInitialPose().xRot + (float)Math.toRadians(transform.x), part.getInitialPose().yRot + (float)Math.toRadians(transform.y), part.getInitialPose().yRot + (float)Math.toRadians(transform.z));
				break;
			case SCALE:
				part.xScale = transform.x;
				part.yScale = transform.y;
				part.zScale = transform.z;
				break;
		}
	}

	private static Vector3f interpolate(float progress, Vector3f start, Vector3f end) {
		float x = start.x + (end.x - start.x) * progress;
		float y = start.y + (end.y - start.y) * progress;
		float z = start.z + (end.z - start.z) * progress;
		return new Vector3f(x, y, z);
	}
}