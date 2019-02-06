package patterns;

import java.awt.Rectangle;

public class AlignedPattern implements Pattern {

    private int width;
    private int height;
    private Rectangle canvasSize;

    @Override
    public int xInCanvas(int xCursor, int yCursor) {
        xCursor += width;
        if (xCursor >= canvasSize.getWidth()) {
            if (yInCanvas(0, 0) != -1) {
                xCursor = getWidth();
            } else {
                // Stop
                return -1;
            }
        }
        return xCursor - getWidth() + 1;
    }

    @Override
    public int yInCanvas(int xCursor, int yCursor) {
        if (xCursor + getWidth() >= canvasSize.getWidth()) {
            yCursor += getHeight();
            if (yCursor + getHeight() >= canvasSize.height) {
                // Stop
                return -1;
            }
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
