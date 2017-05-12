package buttons;

import java.awt.Color;
import java.awt.Rectangle;

import javax.swing.JButton;

public abstract class OptionButton extends JButton {

	private static Color optColor;

	public static JButton newButtonBounds(int xLoc, int yLoc, int optionButtonWidth, int optionButtonHeight) {
		JButton button = new JButton();
		button.setBounds(new Rectangle(xLoc, yLoc, optionButtonWidth, optionButtonHeight));
		return button;
	}

	public static void setOptColor(Color optColor) {
		OptionButton.optColor = optColor;
	}

	public static Color getOptColor() {
		return optColor;
	}

}
