package shapes;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

public class Circle extends ShapeAbstract implements Shape{

	@Override
	public void drawShape(Graphics g, Color c) {
		if(fill()){
			//g.fillOval(x, y, width, height);
		}
	}

	@Override
	public int[] xPointMap() {
		int[] points = new int[1];
		//points[0] = new
		return null;
	}

	@Override
	public int[] yPointMap() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String name() {
		return "circle";
	}
}
