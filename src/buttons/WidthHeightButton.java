package buttons;

import borders.OptionBorder;
import panels.ShapePanel;

public class WidthHeightButton extends OptionButton {
	private static final long serialVersionUID = 4554481865473454523L;
	private ShapePanel sp;

	public WidthHeightButton(ShapePanel sp) {
		this.sp = sp;
		create();
	}

	public void create() {
		newButtonBounds(this);
		setBorder(new OptionBorder("Set Width & Height", getOptColor()));
		addActionListener(event -> sp.widthHeightButtonResponse());
	}
}
