package buttons;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import borders.OptionBorder;
import main.ShapePanel;

public class SaveToFileButton extends OptionButton {
	private static final long serialVersionUID = -845385195405722236L;
	private ShapePanel sp;

	public SaveToFileButton(ShapePanel sp) {
		this.sp = sp;
		create();
	}

	public void create() {
		newButtonBounds(this);
		this.setBounds(new java.awt.Rectangle(getBounds().x, getBounds().y, getBounds().width, getBounds().height));
		setBorder(new OptionBorder("Save To PNG File", getOptColor()));
		addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				sp.saveFileButtonResponse();
			}
		});
	}
}
