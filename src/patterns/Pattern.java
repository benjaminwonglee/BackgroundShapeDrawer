package patterns;

import java.awt.Rectangle;

public interface Pattern {

    public int xInCanvas(int xCursor, int yCursor);

    public int yInCanvas(int xCursor, int yCursor);

    public int getWidth();

    public void setHeight(int height);

    public int getHeight();

    public void setWidth(int width);

    public Rectangle getCanvasSize();

    public void setCanvasSize(Rectangle canvasSize);

}
