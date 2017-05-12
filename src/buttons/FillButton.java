package buttons;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import borders.OptionBorder;
import main.ShapePanel;

public class FillButton extends OptionButton {
	public FillButton() {
		create();
	}

	public void create() {
		JButton changeBackgroundButton = newButtonBounds(ShapePanel.getXLoc(), ShapePanel.getYLoc(), getOptionButtonWidth, optionButtonHeight);
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
