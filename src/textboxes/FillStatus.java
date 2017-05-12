package textboxes;

import java.awt.Font;

import borders.TextBorder;
import shapes.ShapeAbstract;

public class FillStatus extends TextBox {
	private static final long serialVersionUID = -6639018859031014296L;

	public FillStatus() {
		create();
	}

	public void create() {
		newTextAreaBounds();
		TextBorder fillBorder = new TextBorder("");
		if (ShapeAbstract.getFill()) {
			fillBorder.setText("Yes");
		} else {
			fillBorder.setText("No");
		}
		fillBorder.setFont(new Font("Arial", Font.BOLD, 32));
		setBorder(fillBorder);

	}
}
