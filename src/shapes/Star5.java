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
        int[] xInts = new int[]{x, x + width / 3, x + width / 2, x + width - (width / 3),
                x + width, x + width / 4 * 3, x + width - sp, x + width / 2, x + sp - 2,
                x + width / 4, x};
        int[] yInts = new int[]{y + (height / 3), y + (height / 3), y, y + (height / 3),
                y + (height / 3), y + (height / 8) * 5, y + height, y + (height / 8 * 7),
                y + height, y + (height / 8) * 5, y + (height / 3)};
        if (fill == FillStatus.FULL) {
            g.fillPolygon(xInts, yInts, 11);
        } else if (fill == FillStatus.GRADIENT) {
            // TODO: Somehow handle this complicated process
            g.fillPolygon(xInts, yInts, 11);
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
