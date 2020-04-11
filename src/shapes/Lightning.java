package shapes;

import misc.FillStatus;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Lightning extends ShapeAbstract implements IShape {
    private static List<ShapeMetadata> shapeMetadata = new ArrayList<>();

    @Override
    public String name() {
        return Shape.LIGHTNING.getShapeName();
    }

    @Override
    public boolean getCanvasFilled() {
        return canvasFilled;
    }

    @Override
    public void drawFromXY(Graphics g, Color c, int x, int y, int width, int height, FillStatus fill) {
        g.setColor(c);
        int[] xInts = new int[]{
                (int) (x + width / 5.0 * 2),
                x + width,
                (int) (x + width / 5.0 * 3),
                x + width,
                (int) (x + width / 5.0 * 3),
                x + width,
                (int) (x + width / 5.0),
                (int) (x + width / 5.0 * 2),
                x,
                (int) (x + width / 5.0 * 2),
                x,
                (int) (x + width / 5.0 * 2)
        };
        int[] yInts = new int[]{
                y,
                y,
                (int) (y + height / 7.0 * 2),
                (int) (y + height / 7.0 * 2),
                (int) (y + height / 7.0 * 4),
                (int) (y + height / 7.0 * 4),
                y + height,
                (int) (y + height / 7.0 * 5),
                (int) (y + height / 7.0 * 5),
                (int) (y + height / 7.0 * 3),
                (int) (y + height / 7.0 * 3),
                (int) (y + height / 7.0 * 3)};
        if (fill == FillStatus.FULL) {
            g.fillPolygon(xInts, yInts, 11);
        } // TODO: Gradient Fill
        else if (fill == FillStatus.NONE) {
            g.drawPolygon(xInts, yInts, 11);
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
