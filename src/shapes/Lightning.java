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
        int[] xCoords = getXCoords(x, width);
        int[] yCoords = getYCoords(y, height);
        int nPoints = xCoords.length;
        if (fill == FillStatus.FULL) {
            g.fillPolygon(xCoords, yCoords, nPoints);
        } else if (fill == FillStatus.GRADIENT) {
            drawShapeWithColorGradient(g, c, x, y, width, height, nPoints);
        } else if (fill == FillStatus.NONE) {
            g.drawPolygon(xCoords, yCoords, nPoints);
        }
    }

    @Override
    public int[] getXCoords(int x, int width) {
        return new int[]{
                (int) (x + width / 6.0 * 2),
                x + width,
                (int) (x + width / 6.0 * 4),
                x + width,
                (int) (x + width / 6.0 * 4),
                x + width,
                (int) (x + width / 6.0),
                (int) (x + width / 6.0 * 2),
                x,
                (int) (x + width / 6.0 * 2),
                x,
                (int) (x + width / 6.0 * 2)
        };
    }

    @Override
    public int[] getYCoords(int y, int height) {
        return new int[]{
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
                y
        };
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
