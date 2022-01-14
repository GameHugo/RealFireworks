package me.gamehugo.realfireworks.utils;

public class CakeEffect {
    private int delay;
    private FireworkEffects fireworkEffects;

    public void setDelay(int delay) {
        this.delay = delay;
    }
    public void setFireworkEffects( FireworkEffects fireworkEffects) {
        this.fireworkEffects = fireworkEffects;
    }

    public int getDelay() {
        return delay;
    }
    public FireworkEffects getFireworkEffects() {
        return fireworkEffects;
    }
}
