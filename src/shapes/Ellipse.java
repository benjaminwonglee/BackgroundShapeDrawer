package shapes;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import patterns.Pattern;

public class Ellipse extends ShapeAbstract implements Shape {
	public static ArrayList<int[]> xy = new ArrayList<int[]>();

	@Override
	public void drawShape(Graphics g, Graphics gr, Color c) {
		g.setColor(c);
		gr.setColor(c);
		Pattern p = selectPattern();
		for (int i = 0; i < getAmount(); i++) {
			int[] xys = setDrawVariables(c, p);
			if (xys[0] == -1 || xys[1] == -1) {
				setDrawnAmount(i);
				setCanvasFilled(true);
				return;
			}
			xy.add(xys);
			if (getFill()) {
				g.fillOval(xys[0], xys[1], getWidth(), getHeight());
				gr.fillOval(xys[0], xys[1], getWidth(), getHeight());
			} else {
				g.drawOval(xys[0], xys[1], getWidth(), getHeight());
				gr.drawOval(xys[0], xys[1], getWidth(), getHeight());
			}
		}
	}

	@Override
	public String name() {
		return "ellipse";
	}

	@Override
	public boolean getCanvasFilled() {
		return canvasFilled;
	}

	@Override
	public void drawFromXY(Graphics g, Color c, int x, int y, int width, int height) {
		g.setColor(c);
		if (getFill()) {
			g.fillOval(x, y, width, height);
		} else {
			g.drawOval(x, y, width, height);
		}
	}

	@Override
	public ArrayList<int[]> getXY() {
		return xy;
	}

}
