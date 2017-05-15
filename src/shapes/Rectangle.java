package shapes;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

public class Rectangle extends ShapeAbstract implements Shape {
	public static ArrayList<int[]> xy = new ArrayList<int[]>();

	@Override
	public void drawShape(Graphics g, Graphics gr, Color c) {
		g.setColor(c);
		gr.setColor(c);
		for (int i = 0; i < getAmount(); i++) {
			int[] xys = setDrawVariables();
			xy.add(xys);
			int x = xys[0];
			int y = xys[1];
			if (x == -1 || y == -1) {
				setDrawnAmount(i);
				setCanvasFilled(true);
				return;
			}
			if (getFill()) {
				g.fillRect(x, y, getWidth(), getHeight());
				gr.fillRect(x, y, getWidth(), getHeight());
			} else {
				g.drawRect(x, y, getWidth(), getHeight());
				gr.drawRect(x, y, getWidth(), getHeight());
			}
		}
	}

	@Override
	public String name() {
		return "rectangle";
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
