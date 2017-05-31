package patterns;

import java.awt.Rectangle;

import shapes.ShapeAbstract;

public class CrossAlternatingPattern implements Pattern {

	private int width;
	private int height;
	private Rectangle canvasSize;
	private static int crossAlternatingInt;
	
	@Override
	public int xInCanvas(int xCursor, int yCursor) {
		crossAlternatingInt = ShapeAbstract.getCrossAlternatingInt();
		xCursor += getWidth() * 2;
		if (xCursor >= canvasSize.getWidth()) {
			if (yInCanvas(xCursor, yCursor) != -1) {
				if (crossAlternatingInt % 2 == 0) {
					xCursor = getWidth();
					crossAlternatingInt++;
				} else {
					xCursor = getWidth() * 2;
					crossAlternatingInt--;
				}
			} else {
				// Stop
				return -1;
			}
		}
		return xCursor - getWidth() + 1;
	}

	@Override
	public int yInCanvas(int xCursor, int yCursor) {
		if (xCursor + getWidth() >= canvasSize.getWidth()) {
			// new line
			yCursor += getHeight();
			if (yCursor + getHeight() >= canvasSize.getHeight()) {
				// Stop
				return -1;
			}
		}
		return yCursor;
	}

	@Override
	public int getWidth() {
		return width;
	}

	@Override
	public void setWidth(int width) {
		this.width = width;
	}

	@Override
	public void setHeight(int height) {
		this.height = height;
	}

	@Override
	public int getHeight() {
		return height;
	}

	@Override
	public Rectangle getCanvasSize() {
		return canvasSize;
	}

	@Override
	public void setCanvasSize(Rectangle canvasSize) {
		this.canvasSize = canvasSize;
	}

}
