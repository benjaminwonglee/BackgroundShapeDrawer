package buttons;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import borders.OptionBorder;
import main.ShapePanel;

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
		addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				sp.autoShapeColorButtonResponse();
			}
		});
	}

}
