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

        int[] xCoords = new int[]{
                x,
                x + width / 4,
                x + width / 4 * 3,
                x + width,
                x + width,
                x + width / 4 * 3,
                x + width / 4,
                x,
                x};
        int[] yCoords = new int[]{
                y + (height / 4),
                y,
                y,
                y + (height / 4),
                y + height / 4 * 3,
                y + height,
                y + height,
                y + (height / 4) * 3,
                y + (height / 4)};
        int nPoints = xCoords.length;

        if (fill == FillStatus.FULL) {
            g.fillPolygon(xCoords, yCoords, nPoints);
        } else if (fill == FillStatus.GRADIENT) {
            int[] tempXs = xCoords;
            int[] tempYs = yCoords;

            int loopAmount = Math.min(width / 4, height / 4);
            for (int i = 0; i < loopAmount; i++) {
                tempXs = gradientXIncrement(tempXs);
                tempYs = gradientYIncrement(tempYs);
                g.fillPolygon(tempXs, tempYs, nPoints);
                int[] colorArray = incrementGradient(c);
                c = new Color(colorArray[0], colorArray[1], colorArray[2]);
                g.setColor(c);
            }
        } else if (fill == FillStatus.NONE) {
            g.drawPolygon(xCoords, yCoords, nPoints);
        }
    }

    public int[] gradientXIncrement(int[] xs) {
        assert xs.length == 9;
        xs[0] = xs[0] + 1;
        xs[1] = xs[1] + 1;
        xs[2] = xs[2] - 1;
        xs[3] = xs[3] - 1;
        xs[4] = xs[4] - 1;
        xs[5] = xs[5] - 1;
        xs[6] = xs[6] + 1;
        xs[7] = xs[7] + 1;
        xs[8] = xs[8] + 1;
        return xs;
    }

    public int[] gradientYIncrement(int[] ys) {
        assert ys.length == 9;
        ys[0] = ys[0] + 1;
        ys[1] = ys[1] + 1;
        ys[2] = ys[2] + 1;
        ys[3] = ys[3] + 1;
        ys[4] = ys[4] - 1;
        ys[5] = ys[5] - 1;
        ys[6] = ys[6] - 1;
        ys[7] = ys[7] - 1;
        ys[8] = ys[8] + 1;
        return ys;
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
