package shapes;

import misc.FillStatus;

import java.awt.*;
import java.util.List;

public interface Shape {

    void drawShape(Graphics g, Graphics pngGraphics, Color c, FillStatus fill);

    String name();

    boolean getCanvasFilled();

    void drawFromXY(Graphics g, Color c, int x, int y, int width, int height, FillStatus fill);

    List<ShapeMetadata> getXY();

    static void clearAllShapes() {
        Circle.shapeMetadata.clear();
        Ellipse.shapeMetadata.clear();
        Hexagon.shapeMetadata.clear();
        Lightning.shapeMetadata.clear();
        Octagon.shapeMetadata.clear();
        Rectangle.shapeMetadata.clear();
        Square.shapeMetadata.clear();
        Star5.shapeMetadata.clear();
        Star6.shapeMetadata.clear();
        Triangle.shapeMetadata.clear();
    }
}
