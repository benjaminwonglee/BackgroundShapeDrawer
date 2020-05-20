package shapes;

import misc.FillStatus;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Hexagon extends ShapeAbstract implements IShape {
    private static List<ShapeMetadata> shapeMetadata = new ArrayList<>();

    @Override
    public String name() {
        return Shape.HEXAGON.getShapeName();
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
                x,
                (int) (x + width / 3.0),
                (int) (x + width / 3.0 * 2),
                x + width,
                (int) (x + width / 3.0 * 2),
                (int) (x + width / 3.0),
                x};
    }

    @Override
    public int[] getYCoords(int y, int height) {
        return new int[]{
                (int) (y + height / 2.0),
                y,
                y,
                (int) (y + height / 2.0),
                y + height,
                y + height,
                (int) (y + height / 2.0)};
    }

    @Override
    public List<ShapeMetadata> getXY() {
        return shapeMetadata;
    }

    @Override
    public int[] gradientXIncrement(int[] xs) {
        assert xs.length == 7;
        xs[0] = xs[0] + 1;
        xs[1] = xs[1] + 1;
        xs[2] = xs[2] - 1;
        xs[3] = xs[3] - 1;
        xs[4] = xs[4] - 1;
        xs[5] = xs[5] + 1;
        xs[6] = xs[6] + 1;
        return xs;
    }

    @Override
    public int[] gradientYIncrement(int[] ys) {
        assert ys.length == 7;
//        ys[0] = ys[0];
        ys[1] = ys[1] + 1;
        ys[2] = ys[2] + 1;
//        ys[3] = ys[3];
        ys[4] = ys[4] - 1;
        ys[5] = ys[5] - 1;
//        ys[6] = ys[6];
        return ys;
    }

    @Override
    public void clearShape() {
        shapeMetadata.clear();
    }
}
