package textboxes;

import java.awt.Color;

import borders.ColorBorder;
import main.ShapePanel;

public class ChangeBackgroundColor extends TextBox {
	private static final long serialVersionUID = 1L;
	private ShapePanel sp;
	private Color backgroundColor;
	private int xLoc;
	private int yLoc;

	public ChangeBackgroundColor(ShapePanel sp, Color backgroundColor, int xLoc, int yLoc) {
		this.sp = sp;
		this.backgroundColor = backgroundColor;
		this.xLoc = xLoc;
		this.yLoc = yLoc;

		create();
	}

	public void create() {
		newTextAreaBounds(xLoc, yLoc, getOptionButtonWidth(), getOptionButtonHeight());
		setBorder(new ColorBorder(backgroundColor));
		sp.add(this);

	}
}
