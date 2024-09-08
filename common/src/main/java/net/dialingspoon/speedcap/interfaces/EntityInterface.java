package net.dialingspoon.speedcap.interfaces;

public interface EntityInterface {

    boolean speedcap$isSpeeding();

    boolean speedcap$getSailDirection();

    float speedcap$getSailTick();

    void speedcap$setSailDirection(boolean direction);

    void speedcap$setSailTick(float tick);

    void speedcap$setSpeeding(boolean b);
}
