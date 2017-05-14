package shapes;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

public class Square extends ShapeAbstract implements Shape {
	public static ArrayList<int[]> xy = new ArrayList<int[]>();

	@Override
	public void drawShape(Graphics g, Graphics gr, Color c) {
		int[] xys = new int[5];
		for (int i = 0; i < getAmount(); i++) {
			g.setColor(c);
			gr.setColor(c);
			int x = xSelection();
			int y = ySelection();
			xys[0] = x;
			xys[1] = y;
			xys[2] = ShapeAbstract.getWidth();
			xys[3] = ShapeAbstract.getHeight();
			if (getFill()) {
				xys[4] = 1;
			} else {
				xys[4] = 0;
			}
			xy.add(xys);
			if (x == -1 || y == -1) {
				setDrawnAmount(i);
				setCanvasFilled(true);
				return;
			}
			if (getFill()) {
				g.fillRect(x, y, getWidth(), getWidth());
				gr.fillRect(x, y, getWidth(), getWidth());
			} else {
				g.drawRect(x, y, getWidth(), getWidth());
				gr.drawRect(x, y, getWidth(), getWidth());
			}
		}
	}

	@Override
	public String name() {
		return "square";
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
