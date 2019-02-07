package patterns;

import java.awt.Rectangle;

public interface Pattern {

    int xInCanvas(int xCursor, int yCursor);

    int yInCanvas(int xCursor, int yCursor);

    int getWidth();

    void setHeight(int height);

    int getHeight();

    void setWidth(int width);

    Rectangle getCanvasSize();

    void setCanvasSize(Rectangle canvasSize);

}
