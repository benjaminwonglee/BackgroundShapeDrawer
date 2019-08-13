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
        int[] xInts = new int[]{x, x + width / 3, x + width / 3 * 2, x + width, x + width / 3 * 2, x + width / 3, x};
        int[] yInts = new int[]{y + height / 2, y, y, y + height / 2, y + height, y + height, y + height / 2};

        if (fill == FillStatus.FULL) {
            g.fillPolygon(xInts, yInts, 7);
        } else if (fill == FillStatus.GRADIENT) {
            int[] tempXs = xInts;
            int[] tempYs = yInts;

            // Loop an arbitrary number of times expecting break from loop before it reaches this number
            int rightTopX = xInts[1];
            int loopAmount = rightTopX - x;
            for (int i = 0; i < loopAmount; i++) {
                tempXs = gradientXIncrement(tempXs, false);
                tempYs = gradientYIncrement(tempYs);
                g.fillPolygon(tempXs, tempYs, 7);
                int[] colorArray = incrementGradient(c);
                c = new Color(colorArray[0], colorArray[1], colorArray[2]);
                g.setColor(c);
            }
        } else if (fill == FillStatus.NONE) {
            g.drawPolygon(xInts, yInts, 7);
        }
    }

    @Override
    public List<ShapeMetadata> getXY() {
        return shapeMetadata;
    }

    public int[] gradientXIncrement(int[] xs, boolean decrement) {
        assert xs.length == 7;
        int incrementVal = 1;
        if (decrement) {
            incrementVal = -1;
        }
        xs[0] = xs[0] + incrementVal;
        xs[1] = xs[1] + incrementVal;
        xs[2] = xs[2] - incrementVal;
        xs[3] = xs[3] - incrementVal;
        xs[4] = xs[4] - incrementVal;
        xs[5] = xs[5] + incrementVal;
        xs[6] = xs[6] + incrementVal;
        return xs;
    }

    public int[] gradientYIncrement(int[] ys) {
        assert ys.length == 7;
        ys[0] = ys[0] + 1;
        ys[1] = ys[1] + 1;
        ys[2] = ys[2] + 1;
        ys[3] = ys[3] - 1;
        ys[4] = ys[4] - 1;
        ys[5] = ys[5] - 1;
        ys[6] = ys[6] + 1;
        return ys;
    }

    @Override
    public void clearShape() {
        shapeMetadata.clear();
    }
}
