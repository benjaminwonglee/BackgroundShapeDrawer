package shapes;

import java.awt.Color;
import java.awt.Graphics;

public interface Shape {
	public void drawShape(Graphics g, Color c);

	/**
	 * Makes a random point array. Some shapes should only have one point; the
	 * beginning point. Some shapes have determined points based on one point.
	 *
	 * @return An array of all the x points on the shape
	 */
	public int[] xPointMap();

	/**
	 * Makes a random point map. Some shapes should only have one point; the
	 * beginning point. Some shapes have determined points based on one point.
	 *
	 * @return An array of all the y points on the shape
	 */
	public int[] yPointMap();

	public String name();

}
