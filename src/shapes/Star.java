package shapes;

import java.awt.Color;
import java.awt.Graphics;

public class Star extends ShapeAbstract implements Shape{
	
	java.awt.Rectangle canvasSize;
	int width = 70;

	@Override
	public void drawShape(Graphics g, Color c) {
		// TODO Auto-generated method stub
		
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
		return "star";
	}
}
