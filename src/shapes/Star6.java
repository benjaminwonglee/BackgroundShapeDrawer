package shapes;

import misc.FillStatus;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Star6 extends ShapeAbstract implements IShape {

    private static List<ShapeMetadata> shapeMetadata = new ArrayList<>();

    @Override
    public String name() {
        return Shape.STAR6.getShapeName();
    }

    @Override
    public boolean getCanvasFilled() {
        return canvasFilled;
    }

    @Override
    public void drawFromXY(Graphics g, Color c, int x, int y, int width, int height, FillStatus fill) {
        g.setColor(c);
        int[] xInts = new int[]{
                x,
                (int) (x + width / 3.0),
                (int) (x + width / 4.0),
                (int) (x + width / 2.0),
                (int) (x + width - width / 4.0),
                (int) (x + width - width / 3.0),
                x + width,
                (int) (x + width - width / 3.0),
                (int) (x + width - width / 4.0),
                (int) (x + width / 2.0),
                (int) (x + width / 4.0),
                (int) (x + width / 3.0),
                x
        };
        int[] yInts = new int[]{
                (int) (y + height / 2.0),
                (int) (y + height / 3.0),
                y,
                (int) (y + height / 4.0),
                y,
                (int) (y + height / 3.0),
                (int) (y + height / 2.0),
                (int) (y + height / 3.0 * 2),
                y + height,
                (int) (y + height / 4.0 * 3),
                y + height,
                (int) (y + height / 3.0 * 2),
                (int) (y + height / 2.0)
        };
        if (fill == FillStatus.FULL) {
            g.fillPolygon(xInts, yInts, 13);
        // TODO gradient fill
        } else if (fill == FillStatus.NONE) {
            g.drawPolygon(xInts, yInts, 13);
        }
    }

    @Override
    public List<ShapeMetadata> getXY() {
        return shapeMetadata;
    }

    @Override
    public void clearShape() {
        shapeMetadata.clear();
    }
}
