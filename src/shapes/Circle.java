package shapes;

import java.awt.Color;
import java.awt.Graphics;

public class Circle extends ShapeAbstract implements Shape {

	@Override
	public void drawShape(Graphics g, Color c) {
		for (int i = 0; i < getAmount(); i++) {
			g.setColor(c);
			this.setCanvasSize(getCanvasSize());
			int x = xPointMap()[0];
			int y = yPointMap()[0];
			if (fill()) {
				g.fillOval(x, y, getWidth(), getWidth());
			} else {
				g.drawOval(x, y, getWidth(), getWidth());
			}
		}
	}

	@Override
	public int[] xPointMap() {
		int[] points = new int[1];
		points[0] = (int) (Math.random() * (getCanvasSize().getWidth() - getWidth()) + getCanvasSize().getX());
		return points;
	}

	@Override
	public int[] yPointMap() {
		int[] points = new int[1];
		points[0] = (int) (Math.random() * (getCanvasSize().getHeight() - getWidth()) + getCanvasSize().getY());
		return points;
	}

	@Override
	public String name() {
		return "circle";
	}
	
	

}
