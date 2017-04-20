package shapes;

import java.awt.Color;
import java.awt.Graphics;

public class Circle extends ShapeAbstract implements Shape {

	java.awt.Rectangle canvasSize;
	int width = 70;

	@Override
	public void drawShape(Graphics g, Color c, java.awt.Rectangle canvasSize) {
		for (int i = 0; i < getAmount(); i++) {
			g.setColor(c);
			this.canvasSize = canvasSize;
			int x = xPointMap()[0];
			int y = yPointMap()[0];
			if (fill()) {
				g.fillOval(x, y, width, width);
			} else {
				g.drawOval(x, y, width, width);
			}
		}
	}

	@Override
	public int[] xPointMap() {
		int[] points = new int[1];
		points[0] = (int) (Math.random() * (canvasSize.getWidth() - width) + canvasSize.getX());
		return points;
	}

	@Override
	public int[] yPointMap() {
		int[] points = new int[1];
		points[0] = (int) (Math.random() * (canvasSize.getHeight() - width) + canvasSize.getY());
		return points;
	}

	@Override
	public String name() {
		return "circle";
	}
	
	

}
