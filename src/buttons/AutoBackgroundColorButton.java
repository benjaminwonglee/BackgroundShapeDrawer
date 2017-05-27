package buttons;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import borders.OptionBorder;
import main.ShapePanel;

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
		addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				sp.autoBackgroundColorButtonResponse();
			}
		});
	}

}
