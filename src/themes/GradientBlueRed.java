package themes;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class GradientBlueRed implements ColorTheme {

	@Override
	public void setTheme(Graphics g, JPanel sp) {
		Color cOne = new Color(30, 20, 40);
		int incr = sp.getBounds().width/80;
		g.setColor(cOne);
		for (int row = 0; row < sp.getBounds().width; row += incr) {
			for (int col = 0; col < sp.getBounds().height; col += incr) {
				g.fillRect(row, col, incr, incr);
			}
			g.setColor(new Color(cOne.getRed() + (row / incr) * 2, cOne.getGreen(), cOne.getBlue()));
		}
	}

	@Override
	public String name() {
		return "gradient blue red";
	}
}
