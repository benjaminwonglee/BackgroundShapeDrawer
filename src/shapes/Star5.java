package shapes;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

public class Star5 extends ShapeAbstract implements Shape {
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
			double span = getWidth() / 6;
			int sp = (int) span;
			int[] xInts = new int[] { x, x + getWidth() / 3, x + getWidth() / 2, x + getWidth() - (getWidth() / 3),
					x + getWidth(), x + getWidth() / 4 * 3, x + getWidth() - sp, x + getWidth() / 2, x + sp - 2,
					x + getWidth() / 4, x };
			int[] yInts = new int[] { y + (getHeight() / 3), y + (getHeight() / 3), y, y + (getHeight() / 3),
					y + (getHeight() / 3), y + (getHeight() / 8) * 5, y + getHeight(), y + (getHeight() / 8 * 7),
					y + getHeight(), y + (getHeight() / 8) * 5, y + (getHeight() / 3) };
			if (getFill()) {
				g.fillPolygon(xInts, yInts, 11);
				gr.fillPolygon(xInts, yInts, 11);
			} else {
				g.drawPolygon(xInts, yInts, 11);
				gr.drawPolygon(xInts, yInts, 11);
			}
		}
	}

	@Override
	public String name() {
		return "5-pointed star";
	}

	@Override
	public boolean getCanvasFilled() {
		return canvasFilled;
	}

	@Override
	public void drawFromXY(Graphics g, Color c, int x, int y, int width, int height) {
		// TODO Auto-generated method stub
		
	}

	
}
