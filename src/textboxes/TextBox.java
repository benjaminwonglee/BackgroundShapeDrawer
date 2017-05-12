package textboxes;

import java.awt.Rectangle;

import javax.swing.JTextArea;

public abstract class TextBox extends JTextArea{

	private static int optionButtonWidth;
	private static int optionButtonHeight;

	public static JTextArea newTextAreaBounds(int xLoc, int yLoc, int optionButtonWidth, int optionButtonHeight) {
		JTextArea textA = new JTextArea();
		textA.setBounds(new Rectangle(xLoc, yLoc, optionButtonWidth, optionButtonHeight));
		return textA;
	}

	public static int getOptionButtonWidth() {
		return optionButtonWidth;
	}

	public static void setOptionButtonWidth(int optionButtonWidth) {
		TextBox.optionButtonWidth = optionButtonWidth;
	}

	public static int getOptionButtonHeight() {
		return optionButtonHeight;
	}

	public static void setOptionButtonHeight(int optionButtonHeight) {
		TextBox.optionButtonHeight = optionButtonHeight;
	}

}
