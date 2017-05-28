package main;

import java.awt.Color;

public class ColorChooser {
	private Color color;
	private int colorInt = 0;

	/**
	 * Call this to change ColorChooser color automatically. Iterates through
	 * automatically defined colors.
	 */
	public void chooseColor() {
		colorInt++;
		color = null;
		switch (colorInt) {
		case (1):
			// White
			color = new Color(255, 255, 255);
			break;
		case (2):
			// Black
			color = new Color(0, 0, 0);
			break;
		case (3):
			// General blue
			color = new Color(20, 20, 200);
			break;
		case (4):
			// Light Blue
			color = new Color(100, 200, 200);
			break;
		case (5):
			// Sky Blue
			color = new Color(70, 130, 190);
			break;
		case (6):
			// Lilac
			color = new Color(122, 100, 154);
			break;
		case (7):
			// Light Purple
			color = new Color(200, 100, 200);
			break;
		case (8):
			// Dark Purple
			color = new Color(65, 45, 65);
			break;
		case (9):
			// Purple
			color = new Color(128, 42, 128);
			break;
		case (10):
			// Orange
			color = new Color(240, 100, 0);
			break;
		case (11):
			// Brown
			color = new Color(80, 40, 0);
			break;
		case (12):
			// Bright Yellow
			color = new Color(255, 255, 10);
			break;
		case (13):
			// General Yellow
			color = new Color(200, 200, 0);
			break;
		case (14):
			// Gold
			color = new Color(145, 145, 17);
			break;
		case (15):
			// General Green
			color = new Color(20, 200, 0);
			break;
		case (16):
			// Grass Green
			color = new Color(17, 145, 28);
			break;
		case (17):
			// Dark Green
			color = new Color(0, 100, 0);
			break;
		case (18):
			// Mild Green
			color = new Color(40, 120, 40);
			break;
		case (19):
			// Light Green
			color = new Color(130, 255, 130);
			break;
		case (20):
			// Dark red
			color = new Color(120, 0, 0);
			break;
		case (21):
			// Violet
			color = new Color(200, 100, 100);
			break;
		case (22):
			// Light Gray
			color = new Color(200, 200, 200);
			break;
		default:
			// General Red
			color = new Color(200, 0, 0);
			colorInt = 0;
			break;
		}
	}

	/**
	 * Gets the current color of the ColorChooser
	 * 
	 * @return The current color of the ColorChooser
	 */
	public Color getColor() {
		return color;
	}

}
