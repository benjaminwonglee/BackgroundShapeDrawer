package buttons;

import java.awt.Color;
import java.awt.Rectangle;

import javax.swing.JButton;

import main.ShapePanel;

public abstract class OptionButton extends JButton {
	private static final long serialVersionUID = -616184886607585138L;
	private static Color optColor;
	private static int optionButtonWidth;
	private static int optionButtonHeight;

	public static JButton newButtonBounds() {
		JButton button = new JButton();
		button.setBounds(
				new Rectangle(ShapePanel.getXLoc(), ShapePanel.getYLoc(), optionButtonWidth, optionButtonHeight));
		return button;
	}

	public static void setOptColor(Color optColor) {
		OptionButton.optColor = optColor;
	}

	public static Color getOptColor() {
		return optColor;
	}

	public static int getOptionButtonWidth() {
		return optionButtonWidth;
	}

	public static void setOptionButtonWidth(int optionButtonWidth) {
		OptionButton.optionButtonWidth = optionButtonWidth;
	}

	public static int getOptionButtonHeight() {
		return optionButtonHeight;
	}

	public static void setOptionButtonHeight(int optionButtonHeight) {
		OptionButton.optionButtonHeight = optionButtonHeight;
	}

}
