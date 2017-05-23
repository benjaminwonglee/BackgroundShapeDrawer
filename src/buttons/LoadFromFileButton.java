package buttons;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import borders.OptionBorder;
import main.ShapePanel;

public class LoadFromFileButton extends OptionButton {
	private static final long serialVersionUID = -845385195405722236L;
	private ShapePanel sp;

	public LoadFromFileButton(ShapePanel sp) {
		this.sp = sp;
		create();
	}

	public void create() {
		newButtonBounds(this);
		this.setBounds(new java.awt.Rectangle(getBounds().x, getBounds().y, getBounds().width, getBounds().height * 2));
		setBorder(new OptionBorder("Load From Text File", getOptColor()));
		addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				sp.loadFileButtonResponse();
			}
		});
	}
}
