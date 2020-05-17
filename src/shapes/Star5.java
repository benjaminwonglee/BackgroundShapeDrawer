package shapes;

import misc.FillStatus;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Star5 extends ShapeAbstract implements IShape {

    private static List<ShapeMetadata> shapeMetadata = new ArrayList<>();

    @Override
    public String name() {
        return Shape.STAR5.getShapeName();
    }

    @Override
    public boolean getCanvasFilled() {
        return canvasFilled;
    }

    @Override
    public void drawFromXY(Graphics g, Color c, int x, int y, int width, int height, FillStatus fill) {
        g.setColor(c);
        double span = width / 6.0;
        int sp = (int) span;
        int[] xCoords = getXCoords(x, width, sp);
        int[] yCoords = getYCoords(y, height);
        int nPoints = xCoords.length;

        if (fill == FillStatus.FULL) {
            g.fillPolygon(xCoords, yCoords, nPoints);
        } else if (fill == FillStatus.GRADIENT) {
            int loopAmount = Math.min(width / 2, height / 2);
            int[] tempXCoords = xCoords;
            int[] tempYCoords = yCoords;
            int tempX = x;
            int tempY = y;
            int tempWidth = width;
            int tempHeight = height;
            for (int i = 1; i < loopAmount; i++) {
                g.fillPolygon(tempXCoords, tempYCoords, nPoints);
                tempX += 1;
                tempY += 1;
                tempWidth -= 2;
                tempHeight -= 2;
                tempXCoords = getXCoords(tempX, tempWidth, sp);
                tempYCoords = getYCoords(tempY, tempHeight);
                int[] colorArray = incrementGradient(c);
                c = new Color(colorArray[0], colorArray[1], colorArray[2]);
                g.setColor(c);
            }

        } else if (fill == FillStatus.NONE) {
            g.drawPolygon(xCoords, yCoords, 11);
        }
    }

    private int[] getYCoords(int y, int height) {
        return new int[]{
                    (int) (y + height / 3.0),
                    (int) (y + height / 3.0),
                    y,
                    (int) (y + height / 3.0),
                    (int) (y + height / 3.0),
                    (int) (y + height / 8.0 * 5),
                    y + height,
                    (int) (y + height / 8.0 * 7),
                    y + height,
                    (int) (y + height / 8.0 * 5),
                    (int) (y + height / 3.0)};
    }

    private int[] getXCoords(int x, int width, int sp) {
        return new int[]{
                    x,
                    (int) (x + width / 3.0),
                    (int) (x + width / 2.0),
                    (int) (x + width - width / 3.0),
                    x + width,
                    (int) (x + width / 4.0 * 3),
                    x + width - sp,
                    (int) (x + width / 2.0),
                    x + sp,
                    (int) (x + width / 4.0),
                    x
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

    public int[] gradientXIncrement(int[] xs) {
        assert xs.length == 11;
        xs[0] = xs[0] + 1;
        xs[1] = xs[1] + 1;
        xs[2] = xs[2] - 1;
        xs[3] = xs[3] - 1;
        xs[4] = xs[4] - 1;
        xs[5] = xs[5] - 1;
        xs[6] = xs[6] - 1;
        xs[7] = xs[7] + 1;
        xs[8] = xs[8] + 1;
        xs[9] = xs[9] + 1;
        xs[10] = xs[10] + 1;
        return xs;
    }

    public int[] gradientYIncrement(int[] ys) {
        assert ys.length == 11;
//        ys[0] = ys[0];
        ys[1] = ys[1] + 1;
        ys[2] = ys[2] + 1;
//        ys[3] = ys[3] + 1;
        ys[4] = ys[4] + 1;
        ys[5] = ys[5] + 1;
        ys[6] = ys[6] - 1;
        ys[7] = ys[7] - 1;
        ys[8] = ys[8] - 1;
        ys[9] = ys[9] + 1;
        return ys;
    }
}
