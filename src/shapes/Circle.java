package shapes;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Arrays;

import patterns.Pattern;

public class Circle extends ShapeAbstract implements Shape {
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
				g.fillOval(xys[0], xys[1], getWidth(), getWidth());
				gr.fillOval(xys[0], xys[1], getWidth(), getWidth());
			} else {
				g.drawOval(xys[0], xys[1], getWidth(), getWidth());
				gr.drawOval(xys[0], xys[1], getWidth(), getWidth());
			}
		}
	}

	@Override
	public String name() {
		return "circle";
	}

	@Override
	public boolean getCanvasFilled() {
		return canvasFilled;
	}

	@Override
	public void drawFromXY(Graphics g, Color c, int x, int y, int width, int height) {
		g.setColor(c);
		if (getFill()) {
			g.fillOval(x, y, width, width);
		} else {
			g.drawOval(x, y, width, width);
		}
	}

	@Override
	public ArrayList<int[]> getXY() {
		return xy;
	}
}
