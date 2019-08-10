package util;

import javax.swing.*;
import java.awt.*;

public class ColouringUtils {

    public static Color openJColorChooser(Color color) {
        return JColorChooser.showDialog(null, "Choose a colour", color);
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
     * Returns the max value out of a color array of the form [red, green, blue] so it can be adjusted
     *
     * @param c a colour
     */
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

    public static boolean isDarkColor(Color color) {
        int totalColorValue = color.getRed() + color.getGreen() + color.getBlue();
        return totalColorValue < ((255 * 3) / 2);
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
}
