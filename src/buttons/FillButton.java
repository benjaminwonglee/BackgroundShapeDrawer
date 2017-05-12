package buttons;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextArea;

import borders.OptionBorder;
import borders.TextBorder;
import main.ShapePanel;
import shapes.ShapeAbstract;
import textboxes.TextBox;

public class FillButton extends OptionButton {
	private static final long serialVersionUID = 3633526333963162280L;
	private ShapePanel sp;

	public FillButton(ShapePanel sp) {
		this.sp = sp;
		create();
	}

	public void create() {
		newButtonBounds();
		setBorder(new OptionBorder("Fill", getOptColor()));

		ShapePanel.setYLoc(ShapePanel.getYLoc() + getOptionButtonHeight());
		JTextArea fillStatus = TextBox.newTextAreaBounds();
		TextBorder fillBorder = new TextBorder("");
		if (ShapeAbstract.getFill()) {
			fillBorder.setText("Yes");
		} else {
			fillBorder.setText("No");
		}
		fillBorder.setFont(new Font("Arial", Font.BOLD, 32));
		fillStatus.setBorder(fillBorder);

		addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ShapeAbstract.setFill(!ShapeAbstract.getFill());
				if (ShapeAbstract.getFill()) {
					fillBorder.setText("Yes");
				} else {
					fillBorder.setText("No");
				}
				fillStatus.repaint();
			}
		});
		sp.add(fillStatus);
	}
}
