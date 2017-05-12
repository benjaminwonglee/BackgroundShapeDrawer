package buttons;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import borders.OptionBorder;
import main.ShapePanel;

public class ChangeBackgroundButton extends OptionButton {
	private static final long serialVersionUID = -5980908112284503925L;
	private ShapePanel sp;

	public ChangeBackgroundButton(ShapePanel sp) {
		this.sp = sp;
		create();
	}

	public void create() {
		newButtonBounds();
		setBorder(new OptionBorder("Change Background", getOptColor()));
		addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				sp.changeBackgroundButtonResponse();
			}
		});
	}
}
