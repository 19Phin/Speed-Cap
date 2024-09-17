package net.dialingspoon.speedcap.render;

import org.joml.Vector3f;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class KeyframeList {
    public final List<Keyframe> keyframes;
    public KeyframeList(Keyframe... keyframes) {
        Arrays.sort(keyframes, Comparator.comparing(Keyframe::getStartTime));
        this.keyframes = List.of(keyframes);
    }

    public enum Type {
        POSITION,
        ROTATION,
        SCALE
    }

    public static class Keyframe {
        private final Type type;
        private final float startTime;
        private final Vector3f transform;

        public Keyframe(Type type, float startTime, Vector3f position) {
            this.type = type;
            this.startTime = startTime;
            this.transform = position;
        }

        public Type getType() {
            return type;
        }

        public float getStartTime() {
            return startTime;
        }

        public Vector3f getTransform() {
            return transform;
        }
    }
}
