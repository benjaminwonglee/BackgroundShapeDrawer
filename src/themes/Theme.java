package themes;

public enum Theme {
    BLUE_LIGHTNING("blue lightning", new BlueLightning()),
    GOLD_PURPLE_STARS("gold purple stars", new GoldPurpleStars()),
    GRADIENT_BLUE_RED("gradient blue red", new GradientBlueRed()),
    GRADIENT_RED_BLUE("gradient red blue", new GradientRedBlue()),
    STEEL("steel", new Steel()),
    RANDOM_DOT("random dot", new RandomDot()),
    SEMI_RANDOM_DOT("semi random dot", new SemiRandomDot()),
    TRAFFIC_LIGHT("traffic light", new TrafficLight()),
    YELLOW_DIAMONDS("yellow diamonds", new YellowDiamonds()),
    ;

    private final ITheme theme;
    private final String themeName;

    Theme(String themeName, ITheme theme) {
        this.themeName = themeName;
        this.theme = theme;
    }

    public static Theme getNext(Theme theme) {
        Theme[] values = Theme.values();
        int ordinal = theme.ordinal();
        return ordinal + 1 < values.length ? values[ordinal + 1] : values[0];
    }

    /**
     * Returns a theme from a theme name.
     *
     * @param themeName The name of the theme
     * @return A Shape object representing the named shape
     */
    public static ITheme getThemeFromName(String themeName) {
        Theme themeEnum = getThemeEnumFromName(themeName);
        return themeEnum != null ? themeEnum.getTheme() : null;
    }

    public static Theme getThemeEnumFromName(String themeName) {
        Theme[] values = Theme.values();
        for (Theme theme : values) {
            if (theme.getThemeName().equalsIgnoreCase(themeName)) {
                return theme;
            }
        }
        return null;
    }

    public String getThemeName() {
        return themeName;
    }

    public ITheme getTheme() {
        return theme;
    }
}
