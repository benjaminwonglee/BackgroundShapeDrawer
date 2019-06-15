package shapes;

import misc.FillStatus;
import patterns.Pattern;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Star5 extends ShapeAbstract implements Shape {
    static List<ShapeMetadata> shapeMetadata = new ArrayList<>();

    @Override
    public void drawShape(Graphics g, Graphics gr, Color c, FillStatus fill) {
        g.setColor(c);
        gr.setColor(c);
        Pattern p = selectPattern();

        for (int shapeNumber = 0; shapeNumber < getAmount(); shapeNumber++) {
            ShapeMetadata metadata = setDrawVariables(c, p, fill);
            int x = metadata.getX();
            int y = metadata.getY();
            if (x == -1 || y == -1) {
                setDrawnAmount(shapeNumber);
                setCanvasFilled(true);
                return;
            }
            shapeMetadata.add(metadata);
            int width = getWidth();
            double span = width / 6.0;
            int iSpan = (int) span;
            int[] xInts = new int[]{x, x + width / 3, x + width / 2, x + width - (width / 3),
                    x + width, x + width / 4 * 3, x + width - iSpan, x + width / 2, x + iSpan - 2,
                    x + width / 4, x};
            int height = getHeight();
            int[] yInts = new int[]{y + (height / 3), y + (height / 3), y, y + (height / 3),
                    y + (height / 3), y + (height / 8) * 5, y + height, y + (height / 8 * 7),
                    y + height, y + (height / 8) * 5, y + (height / 3)};

            drawFromXY(g, c, x, y, width, height, fill);
            drawFromXY(gr, c, x, y, width, height, fill);
        }
    }

    @Override
    public String name() {
        return ShapeName.STAR5.getShapeName();
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


}
