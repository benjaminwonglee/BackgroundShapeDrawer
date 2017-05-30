package patterns;

import java.awt.Rectangle;

public class AlignedPattern implements Pattern {

	private int width;
	private int height;
	private static int xCursor = 0;
	private static int yCursor = 0;
	private Rectangle canvasSize;

	@Override
	public int xInCanvas() {
		xCursor += width;
		if (xCursor >= canvasSize.getWidth()) {
			if (yInCanvas() != -1) {
				xCursor = getWidth();
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
			yCursor += height;
			if (yCursor + height >= canvasSize.height) {
				// Stop
				return -1;
			}
			return yCursor - height;
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
