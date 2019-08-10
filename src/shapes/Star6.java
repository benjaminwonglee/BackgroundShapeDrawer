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
        int[] xInts = new int[]{x, x + width / 3, x + width / 4, x + width / 2,
                x + width - width / 4, x + width - width / 3, x + width,
                x + width - width / 3, x + width - width / 4, x + width / 2,
                x + width / 4, x + width / 3, x};
        int[] yInts = new int[]{y + height / 2, y + height / 3, y, y + height / 4, y,
                y + height / 3, y + height / 2, y + height / 3 * 2, y + height,
                y + height / 4 * 3, y + height, y + height / 3 * 2, y + height / 2};
        if (fill == FillStatus.FULL) {
            g.fillPolygon(xInts, yInts, 13);
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
