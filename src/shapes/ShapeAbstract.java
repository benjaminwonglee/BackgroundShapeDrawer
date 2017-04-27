package shapes;

import javax.swing.JTextArea;

public abstract class ShapeAbstract implements Shape {
	private int amount = 0;
	private int drawnAmount = 0;
	private static DrawPattern pattern = DrawPattern.RANDOM;
	private static java.awt.Rectangle canvasSize;
	private static int width = 80;
	private static int height = 80;
	private static boolean fill = false;
	private static int xCursor = 0;
	private static int yCursor = 0;
	private static int alternatingInt = 0;
	private static int crossAlternatingInt = 0;

	public enum DrawPattern {
		RANDOM, ALIGNED, BORDERING, CROSSALTERNATING
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
			if (alignedYIntegerInCanvas() != -1) {
				xCursor = (int) canvasSize.getX() + getWidth();
			} else {
				// Abort
				return -1;
			}
		}
		return xCursor - getWidth();
	}

	public int alignedYIntegerInCanvas() {
		if (xCursor >= canvasSize.getX() + canvasSize.getWidth()) {
			yCursor += getHeight();
			if (yCursor + getHeight() >= canvasSize.getY() + canvasSize.getHeight()) {
				// Abort
				return -1;
			}
			return yCursor - getHeight();
		}
		return yCursor;
	}

	public int borderingXIntegerInCanvas() {
		xCursor += getWidth() * 2;
		if (xCursor >= canvasSize.getX() + canvasSize.getWidth()) {
			if (alignedYIntegerInCanvas() != -1) {
				if (alternatingInt % 2 == 0) {
					xCursor = (int) canvasSize.getX() + getWidth();
					alternatingInt++;
				} else {
					xCursor = (int) canvasSize.getX() + getWidth() * 2;
					alternatingInt++;
				}
			} else {
				// Abort
				return -1;
			}
		}
		return xCursor - getWidth();
	}

	public int borderingYIntegerInCanvas() {
		if (xCursor >= canvasSize.getX() + canvasSize.getWidth()) {
			// new line
			yCursor += getHeight();
			if (yCursor + getHeight() >= canvasSize.getY() + canvasSize.getHeight()) {
				// Abort
				return -1;
			}
			return yCursor - getHeight();
		}
		return yCursor;
	}

	public int crossAlternatingXIntegerInCanvas() {
		xCursor += getWidth() * 2;
		if (xCursor >= canvasSize.getX() + canvasSize.getWidth()) {
			if (alignedYIntegerInCanvas() != -1) {
				if (crossAlternatingInt % 2 == 0) {
					xCursor = (int) canvasSize.getX() + getWidth();
					crossAlternatingInt++;
				} else {
					xCursor = (int) canvasSize.getX() + getWidth() * 2;
					crossAlternatingInt++;
				}
			} else {
				// Abort
				return -1;
			}
		}
		return xCursor - getWidth();
	}

	public int crossAlternatingYIntegerInCanvas() {
		return borderingYIntegerInCanvas();
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
		if (pattern == DrawPattern.RANDOM) {
			return randomXIntegerInCanvas();
		} else if (pattern == DrawPattern.ALIGNED) {
			return alignedXIntegerInCanvas();
		} else if (pattern == DrawPattern.BORDERING) {
			return borderingXIntegerInCanvas();
		} else if (pattern == DrawPattern.CROSSALTERNATING) {
			return crossAlternatingXIntegerInCanvas();
		}
		return 0;
	}

	public int ySelection() {
		if (pattern == DrawPattern.RANDOM) {
			return randomYIntegerInCanvas();
		} else if (pattern == DrawPattern.ALIGNED) {
			return alignedYIntegerInCanvas();
		} else if (pattern == DrawPattern.BORDERING) {
			return borderingYIntegerInCanvas();
		} else if (pattern == DrawPattern.CROSSALTERNATING) {
			return crossAlternatingYIntegerInCanvas();
		}
		return 0;
	}

	public static void setPattern(DrawPattern p) {
		ShapeAbstract.pattern = p;
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

	public static int getAlternatingInt() {
		return alternatingInt;
	}

	public static void setAlternatingInt(int alternatingInt) {
		ShapeAbstract.alternatingInt = alternatingInt;
	}

	public static int getCrossAlternatingInt() {
		return crossAlternatingInt;
	}

	public static void setCrossAlternatingInt(int crossAlternatingInt) {
		ShapeAbstract.crossAlternatingInt = crossAlternatingInt;
	}

	public int getDrawnAmount(){
		return drawnAmount;
	}

	public void setDrawnAmount(int i){
		this.drawnAmount  = i;
	}

}
