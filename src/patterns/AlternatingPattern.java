package patterns;

import java.awt.Rectangle;

public class AlternatingPattern implements Pattern {

	private int width;
	private int height;
	private Rectangle canvasSize;
	private static int alternatingInt = 0;
	
	@Override
	public int xInCanvas(int xCursor, int yCursor) {
		xCursor += getWidth() * 2;
		if (xCursor >= canvasSize.getWidth()) {
			if (yInCanvas(0, 0) != -1) {
				if (alternatingInt % 2 == 0) {
					xCursor = getWidth();
					alternatingInt++;
				} else {
					xCursor = getWidth() * 2;
					alternatingInt--;
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
	public Rectangle getCanvasSize() {
		return canvasSize;
	}

	@Override
	public void setCanvasSize(Rectangle canvasSize) {
		this.canvasSize = canvasSize;
	}

	public static void setAlternatingInt(int i) {
		alternatingInt = i;
	}

}
