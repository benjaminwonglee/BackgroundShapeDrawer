package shapes;

import misc.FillStatus;
import util.ColouringUtils;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Triangle extends ShapeAbstract implements IShape {

    private static List<ShapeMetadata> shapeMetadata = new ArrayList<>();

    @Override
    public String name() {
        return Shape.TRIANGLE.getShapeName();
    }

    @Override
    public boolean getCanvasFilled() {
        return canvasFilled;
    }

    @Override
    public void drawFromXY(Graphics g, Color c, int x, int y, int width, int height, FillStatus fill) {
        g.setColor(c);
        int[] xCoords = getXCoords(x, width);
        int[] yCoords = getYCoords(y, height);
        int nPoints = xCoords.length;
        if (fill == FillStatus.FULL) {
            g.fillPolygon(xCoords, yCoords, 4);
        } else if (fill == FillStatus.GRADIENT) {
            drawShapeWithColorGradient(g, c, x, y, width, height, nPoints);
        } else if (fill == FillStatus.NONE) {
            g.drawPolygon(xCoords, yCoords, 4);
        }
    }

    @Override
    public int[] getXCoords(int x, int width) {
        return new int[]{x, (int) (x + width / 2.0), x + width, x};
    }

    @Override
    public int[] getYCoords(int y, int height) {
        return new int[]{y + height, y, y + height, y + height};
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
