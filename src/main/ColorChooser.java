package main;

import java.awt.Color;

public class ColorChooser {
	private Color color;
	private int colorInt = 0;

	public ColorChooser(){
		chooseColor();
	}
	
	private void chooseColor() {
		colorInt++;
		color = null;
		switch (colorInt) {
		case (1):
			color = new Color(20, 20, 200);
			break;
		case (2):
			color = new Color(200, 200, 0);
			break;
		case (3):
			color = new Color(20, 200, 0);
			break;
		case (4):
			color = new Color(100, 200, 200);
			break;
		case (5):
			color = new Color(200, 100, 100);
			break;
		case (6):
			color = new Color(200, 100, 200);
			break;
		case (7):
			color = new Color(145, 100, 155);
			break;
		case (8):
			color = new Color(145, 155, 100);
			break;
		case (9):
			color = new Color(120, 0, 0);
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
