package shapes;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import patterns.Pattern;

public class Triangle extends ShapeAbstract implements Shape {
	public static ArrayList<int[]> xy = new ArrayList<int[]>();

	@Override
	public void drawShape(Graphics g, Graphics gr, Color c) {
		g.setColor(c);
		gr.setColor(c);
		Pattern p = selectPattern();
		for (int i = 0; i < getAmount(); i++) {
			int[] xys = setDrawVariables(c, p);
			xy.add(xys);
			int x = xys[0];
			int y = xys[1];
			if (x == -1 || y == -1) {
				setDrawnAmount(i);
				setCanvasFilled(true);
				return;
			}
			int[] xInts = new int[] { x, x + getWidth() / 2, x + getWidth(), x };
			int[] yInts = new int[] { y + getHeight(), y, y + getHeight(), y + getHeight() };
			if (getFill()) {
				g.fillPolygon(xInts, yInts, 4);
				gr.fillPolygon(xInts, yInts, 4);
			} else {
				g.drawPolygon(xInts, yInts, 4);
				gr.drawPolygon(xInts, yInts, 4);
			}
		}
	}

	@Override
	public String name() {
		return "triangle";
	}

	@Override
	public boolean getCanvasFilled() {
		return canvasFilled;
	}

	@Override
	public void drawFromXY(Graphics g, Color c, int x, int y, int width, int height) {
		g.setColor(c);
		int[] xInts = new int[] { x, x + width / 2, x + width, x };
		int[] yInts = new int[] { y + height, y, y + height, y + height };
		if (getFill()) {
			g.fillPolygon(xInts, yInts, 4);
		} else {
			g.drawPolygon(xInts, yInts, 4);
		}
	}

	@Override
	public ArrayList<int[]> getXY() {
		return xy;
	}

}
