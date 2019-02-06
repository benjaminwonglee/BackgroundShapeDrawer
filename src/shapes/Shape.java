package shapes;

import java.awt.Color;
import java.awt.Graphics;
import java.util.HashSet;

public interface Shape {

    public void drawShape(Graphics g, Graphics pngGraphics, Color c, boolean fill);

    public String name();

    public boolean getCanvasFilled();

    public int getDrawnAmount();

    public void drawFromXY(Graphics g, Color c, int x, int y, int width, int height, boolean fill);

    public HashSet<int[]> getXY();

    public static void clearAllShapes() {
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
