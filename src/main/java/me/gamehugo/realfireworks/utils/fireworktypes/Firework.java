package me.gamehugo.realfireworks.utils.fireworktypes;

import me.gamehugo.realfireworks.RealFireworks;
import me.gamehugo.realfireworks.utils.files.Config;

public class Firework {

    private static Ground ground;
    private static Rocket rocket;
    private static Fountain fountain;
    private static Cake cake;

    public void setup() {
        ground = new Ground();
        rocket = new Rocket();
        fountain = new Fountain();
        cake = new Cake();
        if(Config.getConfig().getBoolean("Debug")) RealFireworks.getInstance().getLogger().info("Loaded firework types");
    }

    public static Ground getGround() {
        return ground;
    }
    public static Rocket getRocket() {
        return rocket;
    }
    public static Fountain getFountain() {
        return fountain;
    }
    public static Cake getCake() {
        return cake;
    }
}
