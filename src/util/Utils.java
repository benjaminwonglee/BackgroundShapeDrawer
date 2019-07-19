package util;

import shapes.Shape;
import shapes.*;
import themes.*;

import javax.swing.*;
import java.awt.*;
import java.util.NoSuchElementException;

import static shapes.ShapeName.*;

public class Utils {

    public static Color openJColorChooser(Color color) {
        return JColorChooser.showDialog(null, "Choose a colour", color);
    }

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

    public static Color darkenColor(Color currentColor, int darkenAmount) {
        int[] colorArray = new int[]{currentColor.getRed(), currentColor.getGreen(), currentColor.getBlue()};

        int maxIndex;
        maxIndex = findMaxInThreeIndexArray(colorArray);

        colorArray[maxIndex] -= darkenAmount;
        if (colorArray[maxIndex] < 0) {
            // Either the colour is black or the darken amount has made this < 0
            colorArray[maxIndex] = 0;
        }
        return new Color(colorArray[0], colorArray[1], colorArray[2]);
    }

    public static Color lightenColor(Color currentColor, int lightenAmount) {
        int[] colorArray = new int[]{currentColor.getRed(), currentColor.getGreen(), currentColor.getBlue()};

        int minIndex = findMinInThreeIndexArray(colorArray);

        colorArray[minIndex] += lightenAmount;
        if (colorArray[minIndex] > 255) {
            // Either the colour is white or the lighten amount has made this > 255
            colorArray[minIndex] = 255;
        }
        return new Color(colorArray[0], colorArray[1], colorArray[2]);
    }

    /**
     * Specific find max for 3 elements in an array
     *
     * @param array An array with 3 elements
     * @return The index containing the max integer
     */
    private static int findMaxInThreeIndexArray(int[] array) {
        int maxIndex;
        if (array[0] > array[1]) {
            if (array[0] > array[2]) {
                // Max is index 0
                maxIndex = 0;
            } else {
                // Max is index 2
                maxIndex = 2;
            }
        } else {
            if (array[1] > array[2]) {
                // Max is index 1
                maxIndex = 1;
            } else {
                // Max is index 2
                maxIndex = 2;
            }
        }
        return maxIndex;
    }

    /**
     * Specific find minimum for 3 elements in an array
     *
     * @param array An array with 3 elements
     * @return The index containing the minimum integer
     */
    private static int findMinInThreeIndexArray(int[] array) {
        int minIndex;
        if (array[0] < array[1]) {
            if (array[0] < array[2]) {
                // Min is index 0
                minIndex = 0;
            } else {
                // Min is index 2
                minIndex = 2;
            }
        } else {
            if (array[1] < array[2]) {
                // Min is index 1
                minIndex = 1;
            } else {
                // Min is index 2
                minIndex = 2;
            }
        }
        return minIndex;
    }

    public static int findMaxColorShade(Color c) {
        int maxColorShade = -1;
        int[] colorArray = new int[]{c.getRed(), c.getGreen(), c.getBlue()};
        for (int i = 0; i < colorArray.length; i++) {
            if (colorArray[i] > maxColorShade) {
                maxColorShade = i;
            }
        }
        return maxColorShade;
    }

    public static Dimension getScreenSize() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int space = 40;
        return new Dimension(screenSize.width - space, screenSize.height - (space * 2));
    }

    public static boolean isDarkColor(Color color) {
        int[] colorArray = new int[]{color.getRed(), color.getGreen(), color.getBlue()};
        int max = findMaxInThreeIndexArray(colorArray);
        return colorArray[max] < 140;
    }
}
