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
        int[] xInts = new int[]{x, (int) (x + width / 2.0), x + width, x};
        int[] yInts = new int[]{y + height, y, y + height, y + height};
        if (fill == FillStatus.FULL) {
            g.fillPolygon(xInts, yInts, 4);
        } else if (fill == FillStatus.GRADIENT) {
            int maxColorShade = ColouringUtils.findMaxColorShade(c);
            int[] colorArray = new int[]{c.getRed(), c.getGreen(), c.getBlue()};
            // TODO: Handle the iterations so it is not width dependent only
            for (int i = 0; i < width / 2; i++) {
                xInts = new int[]{x + i, x + width / 2, x + width - i, x + i};
                yInts = new int[]{y + height - i, y + i, y + height - i, y + height - i};
                g.fillPolygon(xInts, yInts, 4);
                if (colorArray[maxColorShade] > getGradientFactor() - 1) {
                    colorArray[maxColorShade] -= getGradientFactor();
                }
                g.setColor(new Color(colorArray[0], colorArray[1], colorArray[2]));
            }
        } else if (fill == FillStatus.NONE) {
            g.drawPolygon(xInts, yInts, 4);
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
