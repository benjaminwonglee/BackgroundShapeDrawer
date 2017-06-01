package buttons;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import borders.OptionBorder;
import main.ShapePanel;

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
		addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				sp.drawThemeToCanvasButtonResponse();
			}
		});
	}
}
