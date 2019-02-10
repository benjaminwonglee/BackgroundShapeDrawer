package shapes;

import patterns.*;

import java.awt.*;

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
    // Cursor variables
    private static int xCursor = 0;
    private static int yCursor = 0;
    // Pattern variables
    private static DrawPattern pattern = DrawPattern.RANDOM;
    private static int crossAlternatingInt = 0;

    public enum DrawPattern {
        RANDOM, ALIGNED, ALTERNATING, BORDERING, CROSSALTERNATING
    }

    public int[] setDrawVariables(Color c, Pattern p, boolean fill) {
        // xys = [x, y, width, height, fill, rgbColor]
        int[] xys = new int[6];
        int x = p.xInCanvas(xCursor, yCursor);
        int y = p.yInCanvas(xCursor, yCursor);
        xCursor = x + width;
        yCursor = y;
        xys[0] = x;
        xys[1] = y;
        xys[2] = ShapeAbstract.getWidth();
        xys[3] = ShapeAbstract.getHeight();
        if (fill) {
            xys[4] = 1;
        } else {
            xys[4] = 0;
        }
        xys[5] = c.getRGB();
        return xys;
    }

    protected Pattern selectPattern() {
        // Default to random pattern
        Pattern p = new RandomPattern();
        if (pattern == DrawPattern.ALIGNED) {
            p = new AlignedPattern();
        } else if (pattern == DrawPattern.ALTERNATING) {
            xCursor -= width;
            p = new AlternatingPattern(1);
        } else if (pattern == DrawPattern.BORDERING) {
            p = new BorderingPattern();
        } else if (pattern == DrawPattern.CROSSALTERNATING) {
            // The 2nd shape for cross alternating
            if (crossAlternatingInt == 0) {
                xCursor -= width;
                crossAlternatingInt = 1;
            } else {
                crossAlternatingInt = 0;
            }
            p = new AlternatingPattern(crossAlternatingInt);
        }
        p.setWidth(width);
        p.setHeight(height);
        p.setCanvasSize(canvasSize);
        return p;
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
