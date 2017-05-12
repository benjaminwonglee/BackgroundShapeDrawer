package textboxes;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JTextArea;

import borders.ColorBorder;
import borders.TextBorder;
import main.ShapePanel;
import shapes.ShapeAbstract;

public class FillStatus extends TextBox{
	public FillStatus() {
		create();
	}

	public void create() {
		newTextAreaBounds(ShapePanel.xLoc, ShapePanel.yLoc, getOptionButtonWidth(), getOptionButtonHeight());
		TextBorder fillBorder = new TextBorder("");
		if (ShapeAbstract.getFill()) {
			fillBorder.setText("Yes");
		} else {
			fillBorder.setText("No");
		}
		fillBorder.setFont(new Font("Arial", Font.BOLD, 32));
		setBorder(fillBorder);

	}
}
