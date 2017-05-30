package patterns;

import java.awt.Rectangle;

public class BorderingPattern implements Pattern {

	private int width;
	private int height;
	private static int xCursor = 0;
	private static int yCursor = 0;
	private Rectangle canvasSize;
	private static int borderingInt = 0;

	@Override
	public int xInCanvas() {
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
		return xCursor - getWidth();
	}

	@Override
	public int yInCanvas() {
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

	public static void setBorderingInt(int i) {
		borderingInt = i;
	}

}
