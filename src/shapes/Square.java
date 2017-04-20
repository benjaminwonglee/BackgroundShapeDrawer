package shapes;

import java.awt.Color;
import java.awt.Graphics;

public class Square extends ShapeAbstract implements Shape{
	
	java.awt.Rectangle canvasSize;
	int width = 70;

	@Override
	public void drawShape(Graphics g, Color c) {
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int[] yPointMap() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String name() {
		return "square";
	}
}
