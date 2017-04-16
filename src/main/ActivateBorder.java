package main;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Insets;

import javax.swing.border.Border;

public class ActivateBorder implements Border {

	private boolean activated = false;
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
		g.setColor(new Color(244, 244, 200));
		g.fillRect(x, y, width, height);

		// Red or green
		if (!activated) {
			g.setColor(new Color(140, 0, 0));
		} else {
			g.setColor(new Color(0, 180, 0));
		}

		int layers = 3;
		for (int i = 0; i < layers; i++) {
			g.drawRect(x + i, y + i, width - (i + 1), height - (i + 1));
			g.setFont(new Font("Arial", 1, 25));
			g.drawString(label, width / 2 - (g.getFontMetrics().stringWidth(label) / 2), height / 2 + 8);
		}
	}

	public void setActivated(boolean activated) {
		this.activated = activated;
	}

	public boolean getActivated() {
		return activated;
	}
}
