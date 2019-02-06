package buttons;

import borders.OptionBorder;
import panels.ShapePanel;

public class AutoShapeColorButton extends OptionButton {
	private static final long serialVersionUID = -5866465567392229448L;
	private ShapePanel sp;

	public AutoShapeColorButton(ShapePanel sp) {
		this.sp = sp;
		create();
	}

	public void create() {
		newButtonBounds(this);
		setBorder(new OptionBorder("Auto Shape Color", getOptColor()));
		addActionListener(event -> sp.autoShapeColorButtonResponse());
	}

}
