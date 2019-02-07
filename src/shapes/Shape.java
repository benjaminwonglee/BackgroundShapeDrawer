package shapes;

import java.awt.Color;
import java.awt.Graphics;
import java.util.HashSet;

public interface Shape {

    void drawShape(Graphics g, Graphics pngGraphics, Color c, boolean fill);

    String name();

    boolean getCanvasFilled();

    int getDrawnAmount();

    void drawFromXY(Graphics g, Color c, int x, int y, int width, int height, boolean fill);

    HashSet<int[]> getXY();

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
