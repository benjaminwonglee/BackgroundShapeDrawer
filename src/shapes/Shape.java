package shapes;

import java.awt.Color;
import java.awt.Graphics;

public interface Shape {
	public void drawShape(Graphics g, Color c);
	
	public String name();
}
