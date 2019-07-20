package util;

import shapes.Shape;
import shapes.*;
import themes.*;

import java.awt.*;
import java.util.NoSuchElementException;

import static shapes.ShapeName.*;

public class Utils {

    /**
     * Returns a ShapeName type from a shape name in string format.
     *
     * @param shapeName The name of the shape
     * @return A Shape Enum representing the named shape
     */
    public static ShapeName determineShapeNameFromString(String shapeName) {
        switch (shapeName.toLowerCase()) {
            case ("circle"):
                return CIRCLE;
            case ("ellipse"):
                return ELLIPSE;
            case ("hexagon"):
                return HEXAGON;
            case ("lightning"):
                return LIGHTNING;
            case ("octagon"):
                return OCTAGON;
            case ("rectangle"):
                return RECTANGLE;
            case ("square"):
                return SQUARE;
            case ("5-pointed star"):
            case ("star5"):
                return STAR5;
            case ("6-pointed star"):
            case ("star6"):
                return STAR6;
            case ("triangle"):
                return TRIANGLE;
            default:
                throw new NoSuchElementException();
        }
    }


    /**
     * Returns a shape from a shape name.
     *
     * @param shapeName The name of the shape
     * @return A Shape object representing the named shape
     */
    public static Shape determineShapeFromName(String shapeName) {
        switch (shapeName.toLowerCase()) {
            case ("circle"):
                return new Circle();
            case ("ellipse"):
                return new Ellipse();
            case ("hexagon"):
                return new Hexagon();
            case ("lightning"):
                return new Lightning();
            case ("octagon"):
                return new Octagon();
            case ("rectangle"):
                return new shapes.Rectangle();
            case ("square"):
                return new Square();
            case ("5-pointed star"):
            case ("star5"):
                return new Star5();
            case ("6-pointed star"):
            case ("star6"):
                return new Star6();
            case ("triangle"):
                return new Triangle();
            default:
                throw new NoSuchElementException(String.format("A shape was entered which was invalid for this method %s", shapeName));
        }
    }

    /**
     * Returns a theme from a theme name.
     *
     * @param themeName The name of the theme
     * @return A Shape object representing the named shape
     */
    public static Theme retrieveThemeFromName(String themeName) {
        Theme theme;
        switch (themeName) {
            case ("blue lightning"):
                theme = new BlueLightning();
                break;
            case ("gold purple stars"):
                theme = new GoldPurpleStars();
                break;
            case ("gradient red blue"):
                theme = new GradientRedBlue();
                break;
            case ("gradient blue red"):
                theme = new GradientBlueRed();
                break;
            case ("metal theme"):
                theme = new MetalTheme();
                break;
            case ("random dot"):
                theme = new RandomDot();
                break;
            case ("semi random dot"):
                theme = new SemiRandomDot();
                break;
            case ("traffic light theme"):
                theme = new TrafficLight();
                break;
            case ("yellow diamonds"):
                theme = new YellowDiamonds();
                break;
            default:
                theme = new RandomDot();
                break;
        }
        return theme;
    }

    public static Dimension getScreenSize() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int space = 40;
        return new Dimension(screenSize.width - space, screenSize.height - (space * 2));
    }

}
