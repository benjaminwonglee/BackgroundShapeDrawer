package shapes;

import misc.FillStatus;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Octagon extends ShapeAbstract implements IShape {
    private static List<ShapeMetadata> shapeMetadata = new ArrayList<>();

    @Override
    public String name() {
        return Shape.OCTAGON.getShapeName();
    }

    @Override
    public boolean getCanvasFilled() {
        return canvasFilled;
    }

    @Override
    public void drawFromXY(Graphics g, Color c, int x, int y, int width, int height, FillStatus fill) {
        g.setColor(c);
        int[] xInts = new int[]{x, x + width / 4, x + width / 4 * 3, x + width, x + width, x + width / 4 * 3,
                x + width / 4, x, x};
        int[] yInts = new int[]{y + (height / 4), y, y, y + (height / 4), y + height / 4 * 3, y + height, y + height,
                y + (height / 4) * 3, y + (height / 4)};
        if (fill == FillStatus.FULL) {
            g.fillPolygon(xInts, yInts, 9);
        } else if (fill == FillStatus.NONE) {
            g.drawPolygon(xInts, yInts, 9);
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
