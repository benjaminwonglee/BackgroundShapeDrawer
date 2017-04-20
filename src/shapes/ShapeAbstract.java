package shapes;

public abstract class ShapeAbstract implements Shape {
	private int amount = 0;
	private static java.awt.Rectangle canvasSize;
	// TODO: Ask for width and height
	private static int width = 70;
	private static int height = 70;

	public boolean fill() {
		double choice = Math.random();
		if (choice >= 0.5) {
			return true;
		}
		return false;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public java.awt.Rectangle getCanvasSize() {
		return canvasSize;
	}

	public void setCanvasSize(java.awt.Rectangle canvasSize) {
		this.canvasSize = canvasSize;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

}
