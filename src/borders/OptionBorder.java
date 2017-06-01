package borders;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Insets;

import javax.swing.border.Border;

public class OptionBorder implements Border {
	private String label;
	private Color color;

	public OptionBorder(String label, Color c) {
		this.label = label;
		this.color = c;
	}

	@Override
	public void paintBorder(Component c, Graphics g, int x, int y, int wd, int ht) {
		g.setColor(color);
		if (label.length() > 18) {
			g.setFont(new Font("Georgia", 1, 19));
		} else {
			g.setFont(new Font("Georgia", 1, 22));
		}
		g.drawString(label, wd / 2 - (g.getFontMetrics().stringWidth(label) / 2), ht / 2 + 14);

		for (int i = 0; i < 5; i++) {
			g.drawRect(x + i, y + i, wd - (i * 2), ht - (i * 2));
		}
	}

	@Override
	public boolean isBorderOpaque() {
		return false;
	}

	@Override
	public Insets getBorderInsets(Component arg0) {
		return new Insets(0, 0, 0, 0);
	}
}