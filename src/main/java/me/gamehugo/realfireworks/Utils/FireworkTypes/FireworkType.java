package me.gamehugo.realfireworks.Utils.FireworkTypes;

public enum FireworkType {
    Ground("ground"),
    Rocket("rocket"),
    Fountain("fountain"),
    Cake("cake");

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
