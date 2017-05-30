package patterns;

import java.awt.Rectangle;

import shapes.ShapeAbstract;

public class CrossAlternatingPattern implements Pattern {

	private int width;
	private int height;
	private static int xCursor = 0;
	private static int yCursor = 0;
	private Rectangle canvasSize;
	private static int crossAlternatingInt;
	
	@Override
	public int xInCanvas() {
		crossAlternatingInt = ShapeAbstract.getCrossAlternatingInt();
		xCursor += getWidth() * 2;
		if (xCursor >= canvasSize.getWidth()) {
			if (yInCanvas() != -1) {
				if (crossAlternatingInt % 2 == 0) {
					xCursor = getWidth();
					crossAlternatingInt++;
				} else {
					xCursor = getWidth() * 2;
					crossAlternatingInt++;
				}
			} else {
				// Stop
				return -1;
			}
		}
		return xCursor - getWidth();

	}

	@Override
	public int yInCanvas() {
		if (xCursor >= canvasSize.getWidth()) {
			// new line
			yCursor += getHeight();
			if (yCursor + getHeight() >= canvasSize.getHeight()) {
				// Stop
				return -1;
			}
			return yCursor - getHeight();
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
	public int getXCursor() {
		return xCursor;
	}

	@Override
	public void setXCursor(int xCursor) {
		this.xCursor = xCursor;
	}

	@Override
	public int getYCursor() {
		return yCursor;
	}

	@Override
	public void setYCursor(int yCursor) {
		this.yCursor = yCursor;
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
