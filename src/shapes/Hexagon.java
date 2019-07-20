package shapes;

import misc.FillStatus;
import util.ColouringUtils;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Hexagon extends ShapeAbstract implements Shape {
    static List<ShapeMetadata> shapeMetadata = new ArrayList<>();

    @Override
    public String name() {
        return ShapeName.HEXAGON.getShapeName();
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
            int maxColorShade = ColouringUtils.findMaxColorShade(c);
            int[] colorArray = new int[]{c.getRed(), c.getGreen(), c.getBlue()};
            // Loop an arbitrary number of times expecting break from loop before it reaches this number
            for (int j = 0; j < 3; j++) {
                tempXs = gradientXIncrement(tempXs);
                tempYs = gradientYIncrement(tempYs);
                if (ColouringUtils.isDarkColor(c)) {
                    for (int i = 0; i < width / 2; i++) {
                        g.fillPolygon(tempXs, tempYs, 7);
                        c = ColouringUtils.lightenColor(c, getGradientFactor());
                        g.setColor(c);
                    }
                } else {
                    for (int i = 0; i < width / 2; i++) {
                        g.fillPolygon(tempXs, tempYs, 7);
                        c = ColouringUtils.darkenColor(c, getGradientFactor());
                        g.setColor(c);
                    }
                }
                Color nextColor = new Color(colorArray[0], colorArray[1], colorArray[2]);
                g.setColor(nextColor);
            }
        } else if (fill == FillStatus.NONE) {
            g.drawPolygon(xInts, yInts, 7);
        }
    }

    @Override
    public List<ShapeMetadata> getXY() {
        return shapeMetadata;
    }

    public int[] gradientXIncrement(int[] xs) {
        assert xs.length == 7;
        xs[0] = xs[0] + 1;
        xs[1] = xs[1] - 1;
        xs[2] = xs[2] - 1;
        xs[3] = xs[3] - 1;
        xs[4] = xs[4] + 1;
        xs[5] = xs[5] + 1;
        xs[6] = xs[6] + 1;
        return xs;
    }

    public int[] gradientYIncrement(int[] ys) {
        assert ys.length == 7;
        ys[0] = ys[0] + 1;
        ys[1] = ys[1] + 1;
        // ys[2] = ys[2]; Remains the same
        ys[3] = ys[3] - 1;
        ys[4] = ys[4] - 1;
        // ys[5] = ys[5]; Remains the same
        ys[6] = ys[6] + 1;
        return ys;
    }
}
