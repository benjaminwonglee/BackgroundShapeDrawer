package themes;

public enum Theme {
    BLUE_LIGHTNING("blue lightning"),
    GOLD_PURPLE_STARS("gold purple stars"),
    GRADIENT_BLUE_RED("gradient blue red"),
    GRADIENT_RED_BLUE("gradient red blue"),
    METAL_THEME("metal theme"),
    RANDOM_DOT("random dot"),
    SEMI_RANDOM_DOT("semi random dot"),
    TRAFFIC_LIGHT("traffic light"),
    YELLOW_DIAMONDS("yellow diamonds"),
    ;

    private String themeName;

    Theme(String themeName) {
        this.themeName = themeName;
    }

    public static Theme getNext(Theme theme) {
        Theme[] values = Theme.values();
        int ordinal = theme.ordinal();
        return ordinal + 1 < values.length ? values[ordinal + 1] : values[0];
    }

    public static Theme getThemeFromName(String themeName) {
        Theme[] values = Theme.values();
        for (Theme theme : values) {
            if (theme.getThemeName().equalsIgnoreCase(themeName)) {
                return theme;
            };
        }
        return null;
    }

    public String getThemeName() {
        return themeName;
    }
}
