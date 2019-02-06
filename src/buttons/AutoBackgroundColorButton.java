package buttons;

import borders.OptionBorder;
import panels.ShapePanel;

public class AutoBackgroundColorButton extends OptionButton {
	private static final long serialVersionUID = 516160300676491899L;
	private ShapePanel sp;

	public AutoBackgroundColorButton(ShapePanel sp) {
		this.sp = sp;
		create();
	}

	public void create() {
		newButtonBounds(this);
		setBorder(new OptionBorder("Auto Background Color", getOptColor()));
		addActionListener(event -> sp.autoBackgroundColorButtonResponse());
	}

}
