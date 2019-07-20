package misc;

import java.awt.*;

public class ColorChooser {

    private ColorEnum colorEnum = ColorEnum.BLACK;
    private Color color;

    /**
     * Call this to change ColorChooser color automatically. Iterates through
     * automatically defined colors.
     */
    public void chooseColor() {
        int ordinalValue = colorEnum.ordinal();
        ordinalValue++;
        ColorEnum[] colorValues = ColorEnum.values();
        if (ordinalValue >= colorValues.length) {
            ordinalValue = 0;
        }
        colorEnum = colorValues[ordinalValue];
        this.color = colorEnum.getColor();
    }

    /**
     * Gets the current color of the ColorChooser
     *
     * @return The current color of the ColorChooser
     */
    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    private enum ColorEnum {
        BLACK(new Color(0, 0, 0)),
        WHITE(new Color(255, 255, 255)),
        BLUE(new Color(20, 20, 200)),
        LIGHT_BLUE(new Color(100, 200, 200)),
        SKY_BLUE(new Color(70, 130, 190)),
        LILAC(new Color(122, 100, 154)),
        LIGHT_PURPLE(new Color(200, 100, 200)),
        DARK_PURPLE(new Color(65, 45, 65)),
        PURPLE(new Color(128, 42, 128)),
        ORANGE(new Color(240, 100, 0)),
        BROWN(new Color(80, 40, 0)),
        BRIGHT_YELLOW(new Color(255, 255, 10)),
        GOLD(new Color(200, 200, 0)),
        DARK_GOLD(new Color(135, 135, 17)),
        GREEN(new Color(20, 200, 0)),
        GRASS_GREEN(new Color(17, 145, 28)),
        DARK_GREEN(new Color(0, 100, 0)),
        MILD_GREEN(new Color(40, 120, 40)),
        LIGHT_GREEN(new Color(130, 255, 130)),
        RED(new Color(200, 0, 0)),
        DARK_RED(new Color(120, 0, 0)),
        VIOLET(new Color(200, 100, 100)),
        LIGHT_GRAY(new Color(200, 200, 200)),
        ;

        private Color color;

        ColorEnum(Color color) {
            this.color = color;
        }

        public Color getColor() {
            return color;
        }
    }
}
