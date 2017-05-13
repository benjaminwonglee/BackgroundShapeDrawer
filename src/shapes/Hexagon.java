package shapes;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

public class Hexagon extends ShapeAbstract implements Shape {
	public static ArrayList<int[]> xy = new ArrayList<int[]>();

	@Override
	public void drawShape(Graphics g, Graphics gr, Color c) {
		int[] xys = new int[4];
		for (int i = 0; i < getAmount(); i++) {
			g.setColor(c);
			gr.setColor(c);
			int x = xSelection();
			int y = ySelection();
			xys[0] = x;
			xys[1] = y;
			xys[2] = ShapeAbstract.getWidth();
			xys[3] = ShapeAbstract.getHeight();
			xy.add(xys);
			if (x == -1 || y == -1) {
				setDrawnAmount(i);
				setCanvasFilled(true);
				return;
			}
			int[] xInts = new int[] { x, x + getWidth() / 3, x + getWidth() / 3 * 2, x + getWidth(),
					x + getWidth() / 3 * 2, x + getWidth() / 3, x };
			int[] yInts = new int[] { y + getHeight() / 2, y, y, y + getHeight() / 2, y + getHeight(), y + getHeight(),
					y + getHeight() / 2 };
			if (getFill()) {
				g.fillPolygon(xInts, yInts, 7);
				gr.fillPolygon(xInts, yInts, 7);
			} else {
				g.drawPolygon(xInts, yInts, 7);
				gr.drawPolygon(xInts, yInts, 7);
			}
		}
	}

	@Override
	public String name() {
		return "hexagon";
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
