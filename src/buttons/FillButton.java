package buttons;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextArea;

import borders.OptionBorder;
import borders.TextBorder;
import shapes.ShapeAbstract;

public class FillButton extends OptionButton {
	private static final long serialVersionUID = 3633526333963162280L;
	private JTextArea fillStatus;

	public FillButton(JTextArea fillStatus) {
		this.fillStatus = fillStatus;
		create();
	}

	public void create() {
		newButtonBounds();
		setBorder(new OptionBorder("Fill", getOptColor()));

		addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ShapeAbstract.setFill(!ShapeAbstract.getFill());
				TextBorder fillBorder = (TextBorder) fillStatus.getBorder();
				if (ShapeAbstract.getFill()) {
					fillBorder.setText("Yes");
				} else {
					fillBorder.setText("No");
				}
				fillStatus.repaint();
			}
		});
	}
}
