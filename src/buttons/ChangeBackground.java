package buttons;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import borders.OptionBorder;
import main.ShapePanel;

public class ChangeBackground extends OptionButton {

	private ShapePanel sp;
	private int xLoc;
	private int yLoc;
	private int optionButtonWidth;
	private int optionButtonHeight;

	public ChangeBackground(ShapePanel sp, int xLoc, int yLoc, int optionButtonWidth, int optionButtonHeight) {
		this.sp = sp;
		this.xLoc = xLoc;
		this.yLoc = yLoc;
		this.optionButtonWidth = optionButtonWidth;
		this.optionButtonHeight = optionButtonHeight;
		create();
	}

	public void create() {
		JButton changeBackgroundButton = newButtonBounds(xLoc, yLoc, optionButtonWidth, optionButtonHeight);
		changeBackgroundButton.setBorder(new OptionBorder("Change Background", getOptColor()));
		changeBackgroundButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				sp.changeBackgroundButtonResponse();
			}
		});
		sp.add(changeBackgroundButton);
	}
}
