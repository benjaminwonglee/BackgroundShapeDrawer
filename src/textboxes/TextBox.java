package textboxes;

import java.awt.Rectangle;

import javax.swing.JTextArea;

import panels.ShapePanel;

public abstract class TextBox extends JTextArea {
	private static final long serialVersionUID = 6594931150709990623L;
	private static int optionButtonWidth;
	private static int optionButtonHeight;

	public static void newTextAreaBounds(JTextArea t) {
		t.setBounds(
				new Rectangle(ShapePanel.getXLoc(), ShapePanel.getYLoc(), optionButtonWidth, optionButtonHeight));
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
