package shapes;

import misc.FillStatus;
import patterns.Pattern;

import java.awt.*;
import java.util.List;

public interface Shape {

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

    default void drawShape(Graphics g, Graphics pngGraphics, Color c, FillStatus fill) {
        g.setColor(c);
        pngGraphics.setColor(c);
        Pattern p = selectPattern();

        for (int i = 0; i < getAmount(); i++) {
            ShapeMetadata metadata = setDrawVariables(c, p, fill);
            if (metadata.getX() == -1 || metadata.getY() == -1) {
                setDrawnAmount(i);
                setCanvasFilled(true);
                return;
            }
            getXY().add(metadata);
            drawFromXY(g, c, metadata.getX(), metadata.getY(), ShapeAbstract.getWidth(), ShapeAbstract.getHeight(), fill);
            drawFromXY(pngGraphics, c, metadata.getX(), metadata.getY(), ShapeAbstract.getWidth(), ShapeAbstract.getHeight(), fill);
        }
    }

    String name();

    ShapeMetadata setDrawVariables(Color c, Pattern p, FillStatus fill);

    Pattern selectPattern();

    int getAmount();

    int getDrawnAmount();

    void setDrawnAmount(int amountDrawn);

    boolean getCanvasFilled();

    void setCanvasFilled(boolean isCanvasFilled);

    void drawFromXY(Graphics graphics, Color color, int x, int y, int width, int height, FillStatus fill);

    List<ShapeMetadata> getXY();
}
