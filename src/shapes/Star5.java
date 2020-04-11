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
        int[] xInts = new int[]{
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
        int[] yInts = new int[]{
                (int) (y + height / 3.0),
                (int) (y + height / 3.0),
                y,
                (int) (y + height / 3.0),
                (int) (y + height / 3.0),
                (int) (y + height / 8.0) * 5,
                y + height,
                (int) (y + height / 8.0 * 7),
                y + height,
                (int) (y + height / 8.0 * 5),
                (int) (y + height / 3.0)};
        if (fill == FillStatus.FULL) {
            g.fillPolygon(xInts, yInts, 11);
        } else if (fill == FillStatus.GRADIENT) {
            // TODO: gradient fill
//            g.fillPolygon(xInts, yInts, 11);
//            int maxColorShade = Utils.findMaxColorShade(c);
//            int[] colorArray = new int[]{c.getRed(), c.getGreen(), c.getBlue()};
//            for (int iGradient = 0; iGradient < width / 2; iGradient++) {
//                g.fillPolygon(xInts, yInts, 11);
//                if (colorArray[maxColorShade] > 2) {
//                    colorArray[maxColorShade] -= getGradientFactor();
//                }
//                g.setColor(new Color(colorArray[0], colorArray[1], colorArray[2]));
//            }
        } else if (fill == FillStatus.NONE) {
            g.drawPolygon(xInts, yInts, 11);
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
