package shapes;

import java.awt.Point;

public interface Shape {
	public void drawShape();

	public Point[] xPointMap();

	public Point[] yPointMap();
	
	public String name();
}
