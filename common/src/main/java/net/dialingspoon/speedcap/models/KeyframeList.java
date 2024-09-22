package net.dialingspoon.speedcap.models;

import org.joml.Vector3f;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class KeyframeList {
    public final List<Keyframe> positionKeyframes = new ArrayList<>();
    public final List<Keyframe> rotationKeyframes = new ArrayList<>();
    public final List<Keyframe> scaleKeyframes = new ArrayList<>();
    public KeyframeList(Keyframe... keyframes) {
        Arrays.stream(keyframes)
                .sorted(Comparator.comparing(Keyframe::getStartTime))
                .forEach(keyframe -> {
                    switch (keyframe.getType()) {
                        case POSITION -> positionKeyframes.add(keyframe);
                        case ROTATION -> rotationKeyframes.add(keyframe);
                        case SCALE -> scaleKeyframes.add(keyframe);
                    }
                });
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
