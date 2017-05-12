package textboxes;

import java.awt.Color;

import borders.ColorBorder;

public class ChangeOutlineColor extends TextBox {
	private static final long serialVersionUID = 8791957611120418155L;
	private Color outlineColor;

	public ChangeOutlineColor(Color outlineColor) {
		this.outlineColor = outlineColor;
		create();
	}

	public void create() {
		newTextAreaBounds(this);
		setBorder(new ColorBorder(outlineColor));
	}
}
