package me.gamehugo.realfireworks.utils;

import org.bukkit.FireworkEffect;

@SuppressWarnings("unused")
public class FireworkEffects {
    private int power;
    private FireworkEffect.Type type;
    private int red;
    private int green;
    private int blue;
    private int fadeRed;
    private int fadeGreen;
    private int fadeBlue;
    private boolean flicker;
    private boolean trail;
    private boolean fade;

    public void setPower(int power) {
        this.power = power;
    }
    public void setType(FireworkEffect.Type type) {
        this.type = type;
    }
    public void setColor(int red, int green, int blue) {
        this.red = red;
        this.green = green;
        this.blue = blue;
    }
    public void setRed(int red) {
        this.red = red;
    }
    public void setGreen(int green) {
        this.green = green;
    }
    public void setBlue(int blue) {
        this.blue = blue;
    }
    public void setFadeColor(int fadeRed, int fadeGreen, int fadeBlue) {
        this.fadeRed = fadeRed;
        this.fadeGreen = fadeGreen;
        this.fadeBlue = fadeBlue;
    }
    public void setFadeRed(int fadeRed) {
        this.fadeRed = fadeRed;
    }
    public void setFadeGreen(int fadeGreen) {
        this.fadeGreen = fadeGreen;
    }
    public void setFadeBlue(int fadeBlue) {
        this.fadeBlue = fadeBlue;
    }
    public void setFlicker(boolean flicker) {
        this.flicker = flicker;
    }
    public void setTrail(boolean trail) {
        this.trail = trail;
    }
    public void setFade(boolean fade) {
        this.fade = fade;
    }

    public int getPower() {
        return power;
    }
    public FireworkEffect.Type getType() {
        return type;
    }
    public int getRed() {
        return red;
    }
    public int getGreen() {
        return green;
    }
    public int getBlue() {
        return blue;
    }
    public int getFadeRed() {
        return fadeRed;
    }
    public int getFadeGreen() {
        return fadeGreen;
    }
    public int getFadeBlue() {
        return fadeBlue;
    }
    public boolean getFlicker() {
        return flicker;
    }
    public boolean getTrail() {
        return trail;
    }
    public boolean getFade() {
        return fade;
    }
}
