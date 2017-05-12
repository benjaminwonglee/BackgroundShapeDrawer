package buttons;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import borders.OptionBorder;
import main.ShapePanel;

public class ShapeColorButton extends OptionButton {
	private static final long serialVersionUID = -5980908112284503925L;
	private ShapePanel sp;

	public ShapeColorButton(ShapePanel sp) {
		this.sp = sp;
		create();
	}

	public void create() {
		OptionButton.newButtonBounds(this);
		setBorder(new OptionBorder("Shape Colour", getOptColor()));
		addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				sp.shapeColourButtonResponse();
			}
		});
	}
}
