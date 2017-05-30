package patterns;

import java.awt.Rectangle;

public class BorderingPattern implements Pattern {

	private int width;
	private int height;
	private int xCursor;
	private int yCursor;
	private Rectangle canvasSize;

	@Override
	public int xInCanvas() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int yInCanvas() {
		// TODO Auto-generated method stub
		return 0;
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
