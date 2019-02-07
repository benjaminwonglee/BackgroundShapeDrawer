package patterns;

import java.awt.Rectangle;

public class RandomPattern implements Pattern {

    private int width;
    private int height;
    private Rectangle canvasSize;

    @Override
    public int xInCanvas(int xCursor, int yCursor) {
        return (int) (Math.random() * (getCanvasSize().getWidth() - getWidth()));

    }

    @Override
    public int yInCanvas(int xCursor, int yCursor) {
        return (int) (Math.random() * (getCanvasSize().getHeight() - getHeight()));
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
