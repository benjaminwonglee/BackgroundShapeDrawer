package patterns;

import java.awt.*;

public interface IPattern {

    int xInCanvas(int xCursor, int yCursor);

    int yInCanvas(int xCursor, int yCursor);

    int getWidth();

    void setHeight(int height);

    int getHeight();

    void setWidth(int width);

    Rectangle getCanvasSize();

    void setCanvasSize(Rectangle canvasSize);

}
