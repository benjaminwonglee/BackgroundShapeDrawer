package patterns;

import java.awt.*;

public class AlternatingPattern implements Pattern {

    private int width;
    private int height;
    private int offset;
    private Rectangle canvasSize;

    public AlternatingPattern(int offset) {
        this.offset = offset;
    }

    @Override
    public int xInCanvas(int xCursor, int yCursor) {
        int xShift = getWidth() * 2;
        xCursor += xShift;
        // If the next starting x point is beyond the width of the canvas shift the y point
        if (xCursor >= canvasSize.getWidth()) {
            // Shift y point down. Only draw if the there is still room to move down on the y axis on the canvas
            if (yInCanvas(xCursor, yCursor) != -1) {
                // Switch to tell whether this starts on the far left or offset by 1
                if (offset == 0) {
                    offset = 1;
                    xCursor = getWidth();
                } else {
                    offset = 0;
                    xCursor = xShift;
                }
            } else {
                // Stop
                return -1;
            }
        }
        return xCursor - getWidth();
    }

    @Override
    public int yInCanvas(int xCursor, int yCursor) {
        if (xCursor + (getWidth() * 2) >= canvasSize.getWidth()) {
            // new line
            yCursor += getHeight();
            if (yCursor + getHeight() >= canvasSize.getHeight()) {
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
