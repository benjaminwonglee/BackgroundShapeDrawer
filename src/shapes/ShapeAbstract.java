package shapes;

import misc.FillStatus;
import patterns.*;

import java.awt.*;

public abstract class ShapeAbstract implements Shape {

    private static final int gradientFactor = 3;
    private static java.awt.Rectangle canvasSize;
    // Shape size variables
    private static int width = 90;
    private static int height = 90;
    private static int prevWidth = 90;
    private static int prevHeight = 90;
    // Cursor variables
    private static int xCursor = 0;
    private static int yCursor = 0;
    // Pattern variables
    private static DrawPattern pattern = DrawPattern.RANDOM;
    private static int crossAlternatingInt = 0;
    // Canvas variables
    protected boolean canvasFilled = false;
    // Amount variables
    private int amount = 0;
    private int drawnAmount = 0;

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

    public static int getPrevWidth() {
        return prevWidth;
    }

    public static void setPrevWidth(int prevWidth) {
        ShapeAbstract.prevWidth = prevWidth;
    }

    public static int getPrevHeight() {
        return prevHeight;
    }

    public static void setPrevHeight(int prevHeight) {
        ShapeAbstract.prevHeight = prevHeight;
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

    public static int getGradientFactor() {
        return gradientFactor;
    }

    @Override
    public ShapeMetadata setDrawVariables(Color c, Pattern p, FillStatus fill) {
        ShapeMetadata metadata = new ShapeMetadata();

        int x = p.xInCanvas(xCursor, yCursor);
        int y = p.yInCanvas(xCursor, yCursor);
        xCursor = x + width;
        yCursor = y;

        metadata.setX(x);
        metadata.setY(y);
        metadata.setWidth(getWidth());
        metadata.setHeight(getHeight());
        metadata.setFillStatus(fill.ordinal());
        metadata.setRgb(c.getRGB());
        return metadata;
    }

    @Override
    public Pattern selectPattern() {
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

    @Override
    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public int getDrawnAmount() {
        return drawnAmount;
    }

    @Override
    public void setDrawnAmount(int amountDrawn) {
        this.drawnAmount = amountDrawn;
    }

    public java.awt.Rectangle getCanvasSize() {
        return canvasSize;
    }

    public static void setCanvasSize(java.awt.Rectangle canvasSize) {
        ShapeAbstract.canvasSize = canvasSize;
    }

    public boolean getCanvasFilled() {
        return canvasFilled;
    }

    @Override
    public void setCanvasFilled(boolean isCanvasFilled) {
        canvasFilled = isCanvasFilled;
    }

    public enum DrawPattern {
        RANDOM, ALIGNED, ALTERNATING, BORDERING, CROSSALTERNATING
    }
}
