package themes;

import java.awt.Graphics;

import javax.swing.JPanel;

public interface ColorTheme {
	public void setTheme(Graphics g, JPanel sp);

	public String name();
}
