package shapes;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

public interface Shape {

	public void drawShape(Graphics g, Graphics pngGraphics, Color c, boolean fill);

	public String name();

	public boolean getCanvasFilled();

	public int getDrawnAmount();

	public void drawFromXY(Graphics g, Color c, int x, int y, int width, int height, boolean fill);

	public ArrayList<int[]> getXY();

}
