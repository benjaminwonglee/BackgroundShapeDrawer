package borders;

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
		int offset = 30;
		g.setColor(new Color(160, 160, 0));
		g.fillRect(x, y, width, height);
		
		int colorChange = 10;
		int red = 20;
		int green = 30;
		int blue = 90;
		
		g.setColor(new Color(red, green, blue));
		g.fillRect(width - offset, y + offset, offset, height - (offset * 2));
		g.setColor(new Color(red, green, g.getColor().getBlue() - colorChange));
		g.fillRect(x + offset, height - offset, width - (offset * 2), offset);
		g.setColor(new Color(red, green, g.getColor().getBlue() - colorChange));
		g.fillRect(x, y + offset, offset, height - (offset * 2));
		g.setColor(new Color(red, green, g.getColor().getBlue() - colorChange));
		g.fillRect(x + offset, y, width - (offset * 2), height);
		g.setColor(new Color(red, green, g.getColor().getBlue() - colorChange));
		g.fillRect(x + offset, y + offset, width - (offset * 2), height - (offset * 2));
		
		// Red or green
		if (!activated) {
			g.setColor(new Color(180, 0, 0));
		} else {
			g.setColor(new Color(0, 220, 0));
		}

		int layers = 10;
		for (int i = 0; i < layers; i++) {
			g.drawRect(x + i, y + i, width - (i *2), height - (i *2));
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
	
	public String getLabel() {
		return label;
	}

}
