package buttons;

import borders.OptionBorder;
import panels.ShapePanel;

public class ChangeBackgroundButton extends OptionButton {
	private static final long serialVersionUID = -5980908112284503925L;
	private ShapePanel sp;

	public ChangeBackgroundButton(ShapePanel sp) {
		this.sp = sp;
		create();
	}

	public void create() {
		newButtonBounds(this);
		setBorder(new OptionBorder("Change Background", getOptColor()));
		addActionListener(event -> sp.changeBackgroundButtonResponse());
	}
}
