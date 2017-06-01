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

		for (int i = 0; i < 140; i++) {
			Color curr = g.getColor();
			g.drawRect(x + i, y + i, wd - (i * 2), ht - (i * 2));
			g.setColor(new Color(curr.getRed() - 1, curr.getGreen(), curr.getBlue()));
		}
		Color curr = g.getColor();
		int maxCol = 255;
		g.setColor(new Color(maxCol - curr.getRed(), maxCol - curr.getGreen(), maxCol - curr.getBlue()));
		g.drawRect(x, y, wd - 1, ht - 1);
		g.drawRect(x + 1, y + 1, wd - 3, ht - 3);
		g.drawString(label, wd / 2 - (g.getFontMetrics().stringWidth(label) / 2), ht / 2 + 14);
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