package themes;

import java.awt.Graphics;

import javax.swing.JPanel;

public interface Theme {
	public void setTheme(Graphics g, JPanel sp);

	public String name();
}
