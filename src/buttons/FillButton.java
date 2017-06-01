package buttons;

import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextArea;

import borders.OptionBorder;
import borders.TextBorder;
import main.ShapePanel;

public class FillButton extends OptionButton {
	private static final long serialVersionUID = 3633526333963162280L;
	private ShapePanel sp;

	public FillButton(ShapePanel sp) {
		this.sp = sp;
		create();
	}

	public void create() {
		int textBoxSize = 100;
		newButtonBounds(this);
		this.setBounds(new Rectangle(this.getBounds().x, this.getBounds().y, this.getBounds().width - textBoxSize,
				this.getBounds().height));

		setBorder(new OptionBorder("Fill", getOptColor()));

		JTextArea fillStatus = new JTextArea();
		fillStatus.setBounds(new Rectangle(this.getBounds().x + this.getBounds().width, this.getBounds().y, textBoxSize,
				this.getBounds().height));
		TextBorder fillBorder = new TextBorder("");
		if (sp.getFill()) {
			fillBorder.setText("Yes");
		} else {
			fillBorder.setText("No");
		}
		fillBorder.setFont(new Font("Arial", Font.BOLD, 32));
		fillStatus.setBorder(fillBorder);
		addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				sp.setFill(!sp.getFill());
				if (sp.getFill()) {
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
