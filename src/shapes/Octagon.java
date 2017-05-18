package shapes;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

public class Octagon extends ShapeAbstract implements Shape {
	public static ArrayList<int[]> xy = new ArrayList<int[]>();

	@Override
	public void drawShape(Graphics g, Graphics gr, Color c) {
		g.setColor(c);
		gr.setColor(c);
		for (int i = 0; i < getAmount(); i++) {
			int[] xys = setDrawVariables(c);
			xy.add(xys);
			int x = xys[0];
			int y = xys[1];
			if (x == -1 || y == -1) {
				setDrawnAmount(i);
				setCanvasFilled(true);
				return;
			}
			int[] xInts = new int[] { x, x + getWidth() / 4, x + getWidth() / 4 * 3, x + getWidth(), x + getWidth(),
					x + getWidth() / 4 * 3, x + getWidth() / 4, x, x };
			int[] yInts = new int[] { y + (getHeight() / 4), y, y, y + (getHeight() / 4), y + getHeight() / 4 * 3,
					y + getHeight(), y + getHeight(), y + (getHeight() / 4) * 3, y + (getHeight() / 4) };
			if (getFill()) {
				g.fillPolygon(xInts, yInts, 9);
				gr.fillPolygon(xInts, yInts, 9);
			} else {
				g.drawPolygon(xInts, yInts, 9);
				gr.drawPolygon(xInts, yInts, 9);
			}
		}
	}

	@Override
	public String name() {
		return "octagon";
	}

	@Override
	public boolean getCanvasFilled() {
		return canvasFilled;
	}

	@Override
	public void drawFromXY(Graphics g, Color c, int x, int y, int width, int height) {
		// TODO Auto-generated method stub

	}

	@Override
	public ArrayList<int[]> getXY() {
		return xy;
	}

}
