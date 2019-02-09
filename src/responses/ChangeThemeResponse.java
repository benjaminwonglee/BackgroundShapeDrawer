package responses;

import panels.ShapePanel;
import themes.*;

import java.util.NoSuchElementException;

public class ChangeThemeResponse implements ButtonResponse {

    @Override
    public void respond(ShapePanel sp) {
        Theme theme = sp.getTheme();
        // Set theme to the NEXT one in the list
        switch (theme.getThemeName()) {
            case BLUE_LIGHTNING:
                sp.setTheme(new GoldPurpleStars());
                break;
            case GOLD_PURPLE_STARS:
                sp.setTheme(new GradientBlueRed());
                break;
            case GRADIENT_BLUE_RED:
                sp.setTheme(new GradientRedBlue());
                break;
            case GRADIENT_RED_BLUE:
                sp.setTheme(new MetalTheme());
                break;
            case METAL_THEME:
                sp.setTheme(new RandomDot());
                break;
            case RANDOM_DOT:
                sp.setTheme(new SemiRandomDot());
                break;
            case SEMI_RANDOM_DOT:
                sp.setTheme(new TrafficLight());
                break;
            case TRAFFIC_LIGHT:
                sp.setTheme(new YellowDiamonds());
                break;
            case YELLOW_DIAMONDS:
                sp.setTheme(new BlueLightning());
                break;
            default:
                throw new NoSuchElementException(String.format(
                        "The theme was not found in the change theme response %s", theme.getThemeName()));
        }
        sp.repaint();

        String themeName = sp.getTheme().getName();
        sp.writeToTextBoxAndRepaint(sp.getThemeText(), themeName.substring(0, 1).toUpperCase() + themeName.substring(1));
        sp.setThemeDrawn(false);
    }
}
