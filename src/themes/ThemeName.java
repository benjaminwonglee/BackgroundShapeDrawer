package themes;

import java.util.NoSuchElementException;

public enum ThemeName {
    BLUE_LIGHTNING,
    GOLD_PURPLE_STARS,
    GRADIENT_BLUE_RED,
    GRADIENT_RED_BLUE,
    METAL_THEME,
    RANDOM_DOT,
    SEMI_RANDOM_DOT,
    TRAFFIC_LIGHT,
    YELLOW_DIAMONDS;

    public String getThemeName() {
        switch (this) {
            case BLUE_LIGHTNING:
                return "blue lightning";
            case GOLD_PURPLE_STARS:
                return "gold purple stars";
            case GRADIENT_BLUE_RED:
                return "gradient blue red";
            case GRADIENT_RED_BLUE:
                return "gradient red blue";
            case METAL_THEME:
                return "metal theme";
            case RANDOM_DOT:
                return "random dot";
            case SEMI_RANDOM_DOT:
                return "semi random dot";
            case TRAFFIC_LIGHT:
                return "traffic light";
            case YELLOW_DIAMONDS:
                return "yellow diamonds";
            default:
                throw new NoSuchElementException(String.format(
                        "The chosen theme Enum was not recorded in theme names %s", this.name()));
        }
    }
}
