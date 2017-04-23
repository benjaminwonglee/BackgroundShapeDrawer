package shapes;

public abstract class ShapeAbstract implements Shape {
	private int amount = 0;
	private DrawPattern pattern = DrawPattern.ALIGNED;
	private static java.awt.Rectangle canvasSize;
	private static int width = 80;
	private static int height = 80;
	private static boolean fill = false;
	private static int xCursor = 0;
	private static int yCursor = 0;

	public enum DrawPattern {
		RANDOM, ALIGNED
	};

	public int randomXIntegerInCanvas() {
		int x = (int) (Math.random() * (getCanvasSize().getWidth() - getWidth()) + getCanvasSize().getX());
		return x;
	}

	public int randomYIntegerInCanvas() {
		int y = (int) (Math.random() * (getCanvasSize().getHeight() - getHeight()) + getCanvasSize().getY());
		return y;
	}

	public int alignedXIntegerInCanvas() {
		xCursor += getWidth();
		if (xCursor >= canvasSize.getX() + canvasSize.getWidth()) {
			xCursor = (int) canvasSize.getX() + 1;
			if (yCursor >= canvasSize.getY() + canvasSize.getHeight()) {
				// Abort
				return -1;
			}
		}
		System.out.println(xCursor - getWidth() + "  Cream cheese width?  " + canvasSize.getWidth());
		return xCursor - getWidth();
	}

	public int alignedYIntegerInCanvas() {
		yCursor += getHeight();
		if (yCursor >= canvasSize.getY() + canvasSize.getHeight()) {
			yCursor = (int) canvasSize.getY() + 1;
			if (xCursor >= canvasSize.getX() + canvasSize.getWidth()) {
				// Abort
				return -1;
			}

		}
		System.out.println(yCursor - getHeight() + "  Cream cheese?  " + canvasSize.getHeight());
		return yCursor - getHeight();
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

	public int xSelection() {
		System.out.println(1);
		if (pattern == DrawPattern.RANDOM) {
			return randomXIntegerInCanvas();
		} else if (pattern == DrawPattern.ALIGNED) {
			return alignedXIntegerInCanvas();
		}
		System.out.println(-2);
		return 0;
	}

	public int ySelection() {
		if (pattern == DrawPattern.RANDOM) {
			return randomYIntegerInCanvas();
		} else if (pattern == DrawPattern.ALIGNED) {
			return alignedYIntegerInCanvas();
		}
		return 0;
	}

	public void setPattern(DrawPattern p) {
		this.pattern = p;
	}

	public static int getXCursor() {
		return xCursor;
	}

	public static void setXCursor(int xCursor) {
		ShapeAbstract.xCursor = xCursor;
	}

	public static int getYCursor() {
		return yCursor;
	}

	public static void setYCursor(int yCursor) {
		ShapeAbstract.yCursor = yCursor;
	}

}
