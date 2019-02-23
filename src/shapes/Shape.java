package shapes;

import misc.FillStatus;

import java.awt.*;
import java.util.List;

public interface Shape {

    void drawShape(Graphics g, Graphics pngGraphics, Color c, FillStatus fill);

    String name();

    boolean getCanvasFilled();

    void drawFromXY(Graphics g, Color c, int x, int y, int width, int height, FillStatus fill);

    List<int[]> getXY();

    static void clearAllShapes() {
        Circle.xy.clear();
        Ellipse.xy.clear();
        Hexagon.xy.clear();
        Lightning.xy.clear();
        Octagon.xy.clear();
        Rectangle.xy.clear();
        Square.xy.clear();
        Star5.xy.clear();
        Star6.xy.clear();
        Triangle.xy.clear();
    }
}
