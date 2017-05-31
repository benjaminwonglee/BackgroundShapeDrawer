package patterns;

import java.awt.Rectangle;

public class BorderingPattern implements Pattern {

	private int width;
	private int height;
	private Rectangle canvasSize;
	private static int borderingInt = 0;

	@Override
	public int xInCanvas(int xCursor, int yCursor) {
		if (yCursor == 0 || yCursor >= (canvasSize.getHeight() - getHeight() * 2)) {
			borderingInt = 0;
			if (xCursor + getWidth() < canvasSize.getWidth()) {
				xCursor += getWidth();
			} else {
				borderingInt = 1;
			}
		} else if (borderingInt == 1) {
			// Left border
			xCursor = getWidth();
			borderingInt = 2;
		} else if (borderingInt == 2) {
			// Right border
			while (xCursor < canvasSize.getWidth() - getWidth()) {
				xCursor += getWidth();
			}
			borderingInt = 1;
		}
		return xCursor - getWidth() + 1;
	}

	@Override
	public int yInCanvas(int xCursor, int yCursor) {
		if (borderingInt == 1) {
			yCursor += getHeight();
			xCursor = 0;
		}
		if (yCursor + getHeight() >= canvasSize.getHeight()) {
			// Stop
			return -1;
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

	public static void setBorderingInt(int i) {
		borderingInt = i;
	}

}
