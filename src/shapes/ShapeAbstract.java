package shapes;

public abstract class ShapeAbstract implements Shape {
	private int amount = 0;
	private static java.awt.Rectangle canvasSize;
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
		ShapeAbstract.canvasSize = canvasSize;
	}

	public int generateRandomIntegerX() {
		int x = (int) (Math.random() * (getCanvasSize().getWidth() - getWidth()) + getCanvasSize().getX());
		return x;
	}

	public int generateRandomIntegerY() {
		int y = (int) (Math.random() * (getCanvasSize().getHeight() - getHeight()) + getCanvasSize().getY());
		return y;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		ShapeAbstract.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		ShapeAbstract.height = height;
	}

}
