package shapes;

public abstract class ShapeAbstract implements Shape {
	private int amount = 0;
	private static java.awt.Rectangle canvasSize;
	private static int width = 70;
	private static int height = 70;
	private static boolean fill = false;


	public int randomXIntegerInCanvas() {
		int x = (int) (Math.random() * (getCanvasSize().getWidth() - getWidth()) + getCanvasSize().getX());
		return x;
	}

	public int randomYIntegerInCanvas() {
		int y = (int) (Math.random() * (getCanvasSize().getHeight() - getHeight()) + getCanvasSize().getY());
		return y;
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

	public static void setCanvasSize(java.awt.Rectangle canvasSize) {
		ShapeAbstract.canvasSize = canvasSize;
	}

	public static int getWidth() {
		return width;
	}

	public static void setWidth(int width) {
		ShapeAbstract.width = width;
	}

	public static int getHeight() {
		return height;
	}

	public static void setHeight(int height) {
		ShapeAbstract.height = height;
	}

	public static boolean getFill() {
		return fill;
	}

	public static void setFill(boolean fill) {
		ShapeAbstract.fill = fill;
	}

}
