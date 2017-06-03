package borders;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Insets;

import javax.swing.border.Border;

public class ColorBorder implements Border {

	private Color color;

	public ColorBorder(Color c) {
		this.color = c;
	}

	@Override
	public void paintBorder(Component c, Graphics g, int x, int y, int wd, int ht) {
		g.setColor(color);
		g.fillRect(x, y, wd - 1, ht - 1);
	}

	@Override
	public boolean isBorderOpaque() {
		return false;
	}

	@Override
	public Insets getBorderInsets(Component arg0) {
		return new Insets(0, 0, 0, 0);
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}
}
