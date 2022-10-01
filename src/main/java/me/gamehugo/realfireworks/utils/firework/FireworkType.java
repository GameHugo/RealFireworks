package me.gamehugo.realfireworks.utils.firework;

@SuppressWarnings("unused")
public enum FireworkType {
    GROUND("ground"),
    ROCKET("rocket"),
    FOUNTAIN("fountain"),
    CAKE("cake");

    private final String type;

    FireworkType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public static FireworkType getType(String name) {
        for (FireworkType fireworkType : FireworkType.values()) {
            if(fireworkType.type.equalsIgnoreCase(name)) {
                return fireworkType;
            }
        }
        return null;
    }
}
