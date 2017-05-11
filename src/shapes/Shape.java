package shapes;

import java.awt.Color;
import java.awt.Graphics;

public interface Shape {
	public void drawShape(Graphics g, Graphics pngGraphics, Color c);

	public String name();

	public boolean getCanvasFilled();

	public int getDrawnAmount();
}
