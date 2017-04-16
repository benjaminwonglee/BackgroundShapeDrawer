package main;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Insets;

import javax.swing.border.Border;

public class ActivateBorder implements Border {

	boolean activated = false;
	private String label;

	public ActivateBorder(String label) {
		this.label = label;
	}

	@Override
	public Insets getBorderInsets(Component arg0) {
		return new Insets(0, 0, 0, 0);
	}

	@Override
	public boolean isBorderOpaque() {
		return false;
	}

	@Override
	public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
		if (!activated) {
			g.setColor(new Color(140, 0, 0));
		} else {
			g.setColor(new Color(0, 180, 0));
		}

		// Three layers of border
		g.drawRect(x, y, width - 1, height - 1);
		g.drawRect(x + 1, y + 1, width - 2, height - 2);
		g.drawRect(x + 2, y + 2, width, height);

		g.setFont(new Font("Arial", 1, 25));
		g.drawString(label, width / 2 - (g.getFontMetrics().stringWidth(label) / 2), height / 2 + 8);
	}

	public boolean getActivated() {
		return activated;
	}

}
