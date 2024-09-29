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
                .sorted(Comparator.comparing(Keyframe::startTime))
                .forEach(keyframe -> {
                    switch (keyframe.type()) {
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

    public record Keyframe(Type type, float startTime, Vector3f transform) {
    }
}
