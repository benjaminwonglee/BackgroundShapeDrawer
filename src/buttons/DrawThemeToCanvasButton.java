package buttons;

import borders.OptionBorder;
import panels.ShapePanel;

public class DrawThemeToCanvasButton extends OptionButton {
	private static final long serialVersionUID = 8501655856512224984L;
	private ShapePanel sp;

	public DrawThemeToCanvasButton(ShapePanel sp) {
		this.sp = sp;
		create();
	}

	public void create() {
		newButtonBounds(this);
		setBorder(new OptionBorder("Draw Theme To Canvas", getOptColor()));
		addActionListener(event -> sp.drawThemeToCanvasButtonResponse());
	}
}
