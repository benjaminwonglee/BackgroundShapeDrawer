package patterns;

import java.awt.Rectangle;

public class BorderingPattern implements Pattern {

    private int width;
    private int height;
    private Rectangle canvasSize;
    private static int endWidth;

    @Override
    public int xInCanvas(int xCursor, int yCursor) {
        if (yCursor == 0 || yCursor >= (canvasSize.getHeight() - getHeight() * 2)) {
            if (xCursor + getWidth() < canvasSize.getWidth()) {
                xCursor += getWidth();

                return xCursor - getWidth() + 1;
            }
            endWidth = xCursor;
            yCursor = yInCanvas(xCursor, yCursor);
            // Left border
            xCursor = getWidth();
        } else {
            if (xCursor <= getWidth() + 5) {
                // Right border
                xCursor = endWidth;
            } else {
                // Left border
                xCursor = getWidth();
            }
        }

        return xCursor - getWidth() + 1;

    }

    @Override
    public int yInCanvas(int xCursor, int yCursor) {
        if (xCursor + getWidth() > canvasSize.getWidth()) {
            yCursor += getHeight();
            xCursor = 0;
        }
        if (yCursor + getHeight() >= canvasSize.getHeight()) {
            // Stop
            return -1;
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
    public Rectangle getCanvasSize() {
        return canvasSize;
    }

    @Override
    public void setCanvasSize(Rectangle canvasSize) {
        this.canvasSize = canvasSize;
    }
}
