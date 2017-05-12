package textboxes;

import java.awt.Color;

import borders.ColorBorder;

public class ChangeBackgroundColor extends TextBox {
	private static final long serialVersionUID = -5849945233568946779L;
	private Color backgroundColor;

	public ChangeBackgroundColor(Color backgroundColor) {
		this.backgroundColor = backgroundColor;
		create();
	}

	public void create() {
		newTextAreaBounds();
		setBorder(new ColorBorder(backgroundColor));
	}
}
