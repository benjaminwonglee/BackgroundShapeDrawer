package shapes;

import java.awt.Color;

import patterns.AlignedPattern;
import patterns.AlternatingPattern;
import patterns.BorderingPattern;
import patterns.CrossAlternatingPattern;
import patterns.Pattern;
import patterns.RandomPattern;

public abstract class ShapeAbstract implements Shape {
	// Amount variables
	private int amount = 0;
	private int drawnAmount = 0;
	// Canvas variables
	protected boolean canvasFilled = false;
	private static java.awt.Rectangle canvasSize;
	// Shape size variables
	private static int width = 90;
	private static int height = 90;
	private static boolean fill = false;
	// Cursor variables
	private static int xCursor = 0;
	private static int yCursor = 0;
	// Pattern variables
	private static DrawPattern pattern = DrawPattern.RANDOM;
	private static int crossAlternatingInt = 0;

	public enum DrawPattern {
		RANDOM, ALIGNED, ALTERNATING, BORDERING, CROSSALTERNATING
	};

	public int[] setDrawVariables(Color c) {
		// xys = [x, y, width, height, fill, rgbColor]
		int[] xys = new int[6];
		Pattern p = selectPattern();
		int x = p.xInCanvas();
		int y = p.yInCanvas();
		xys[0] = x;
		xys[1] = y;
		xys[2] = ShapeAbstract.getWidth();
		xys[3] = ShapeAbstract.getHeight();
		if (getFill()) {
			xys[4] = 1;
		} else {
			xys[4] = 0;
		}
		xys[5] = c.getRGB();
		return xys;
	}

	private Pattern selectPattern() {
		Pattern p = null;
		if (pattern == DrawPattern.RANDOM) {
			p = new RandomPattern();
		} else if (pattern == DrawPattern.ALIGNED) {
			p = new AlignedPattern();
		} else if (pattern == DrawPattern.ALTERNATING) {
			p = new AlternatingPattern();
		} else if (pattern == DrawPattern.BORDERING) {
			p = new BorderingPattern();
		} else if (pattern == DrawPattern.CROSSALTERNATING) {
			p = new CrossAlternatingPattern();
			// The 2nd shape for cross alternating
			// if (crossAlternatingInt == -1) {
			// xCursor = getWidth();
			// crossAlternatingInt = 1;
			// // return 0;
			// }
		}
		p.setWidth(width);
		p.setHeight(height);
		p.setXCursor(xCursor);
		p.setYCursor(yCursor);
		
		return p;

	}
	
	public int crossAlternatingXIntegerInCanvas() {
		xCursor += getWidth() * 2;
		if (xCursor >= canvasSize.getWidth()) {
			if (crossAlternatingYIntegerInCanvas() != -1) {
				if (crossAlternatingInt % 2 == 0) {
					xCursor = getWidth();
					crossAlternatingInt++;
				} else {
					xCursor = getWidth() * 2;
					crossAlternatingInt++;
				}
			} else {
				// Stop
				return -1;
			}
		}
		return xCursor - getWidth();
	}

	public int crossAlternatingYIntegerInCanvas() {
		return alternatingYIntegerInCanvas();
	}

	public int xSelection() {
		if (pattern == DrawPattern.RANDOM) {
			return randomXIntegerInCanvas();
		} else if (pattern == DrawPattern.ALIGNED) {
			
			return alignedXIntegerInCanvas();
		} else if (pattern == DrawPattern.ALTERNATING) {
			return alternatingXIntegerInCanvas();
		} else if (pattern == DrawPattern.BORDERING) {
			return borderingXIntegerInCanvas();
		} else if (pattern == DrawPattern.CROSSALTERNATING) {
			// The 2nd shape for cross alternating
			if (crossAlternatingInt == -1) {
				xCursor = getWidth();
				crossAlternatingInt = 1;
				return 0;
			}
			return crossAlternatingXIntegerInCanvas();
		}
		return 0;
	}

	public int ySelection() {
		if (pattern == DrawPattern.RANDOM) {
			return randomYIntegerInCanvas();
		} else if (pattern == DrawPattern.ALIGNED) {
			return alignedYIntegerInCanvas();
		} else if (pattern == DrawPattern.ALTERNATING) {
			return alternatingYIntegerInCanvas();
		} else if (pattern == DrawPattern.BORDERING) {
			return borderingYIntegerInCanvas();
		} else if (pattern == DrawPattern.CROSSALTERNATING) {
			return crossAlternatingYIntegerInCanvas();
		}
		return 0;
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

	public static int getBorderingInt() {
		return borderingInt;
	}

	public static void setBorderingInt(int borderingInt) {
		ShapeAbstract.borderingInt = borderingInt;
	}

	public static int getCrossAlternatingInt() {
		return crossAlternatingInt;
	}

	public static void setCrossAlternatingInt(int crossAlternatingInt) {
		ShapeAbstract.crossAlternatingInt = crossAlternatingInt;
	}

	public int getDrawnAmount() {
		return drawnAmount;
	}

	public void setDrawnAmount(int i) {
		this.drawnAmount = i;
	}

	public boolean getCanvasFilled() {
		return canvasFilled;
	}

	public void setCanvasFilled(boolean b) {
		canvasFilled = b;
	}
}
