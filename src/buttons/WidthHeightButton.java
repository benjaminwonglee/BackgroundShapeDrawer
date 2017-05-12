package buttons;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import borders.OptionBorder;
import main.ShapePanel;

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
		addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				sp.widthHeightButtonResponse();
			}
		});
	}
}
