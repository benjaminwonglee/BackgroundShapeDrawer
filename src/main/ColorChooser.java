package main;

import java.awt.Color;

public class ColorChooser {
	private Color color;
	private int colorInt = 0;

	public void chooseColor() {
		colorInt++;
		color = null;
		switch (colorInt) {
		case (1):
			// General blue
			color = new Color(20, 20, 200);
			break;
		case (2):
			// Sea blue
			color = new Color(122, 100, 154);
			break;
		case (3):
			// Light Blue
			color = new Color(100, 200, 200);
			break;
		case (4):
			// Light Purple (Lilac)
			color = new Color(200, 100, 200);
			break;
		case (5):
			// General Yellow
			color = new Color(200, 200, 0);
			break;
		case (6):
			// Gold
			color = new Color(178, 204, 51);
			break;
		case (7):
			// General Green
			color = new Color(20, 200, 0);
			break;
		case (8):
			// Dark red
			color = new Color(120, 0, 0);
			break;
		case (9):
			// Violet (Red)
			color = new Color(200, 100, 100);
			break;
		default:
			color = new Color(200, 0, 0);
			colorInt = 0;
			break;
		}
	}

	public Color getColor() {
		return color;
	}

}
