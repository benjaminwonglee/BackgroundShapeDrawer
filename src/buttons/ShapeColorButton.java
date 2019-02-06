package buttons;

import borders.OptionBorder;
import panels.ShapePanel;

public class ShapeColorButton extends OptionButton {
	private static final long serialVersionUID = -5980908112284503925L;
	private ShapePanel sp;

	public ShapeColorButton(ShapePanel sp) {
		this.sp = sp;
		create();
	}

	public void create() {
		newButtonBounds(this);
		setBorder(new OptionBorder("Shape Colour", getOptColor()));
		addActionListener(event -> sp.shapeColourButtonResponse());
	}
}
