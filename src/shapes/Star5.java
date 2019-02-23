package shapes;

import misc.FillStatus;
import patterns.Pattern;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Star5 extends ShapeAbstract implements Shape {
    static List<int[]> xy = new ArrayList<>();

    @Override
    public void drawShape(Graphics g, Graphics gr, Color c, FillStatus fill) {
        g.setColor(c);
        gr.setColor(c);
        Pattern p = selectPattern();

        for (int i = 0; i < getAmount(); i++) {
            int[] xys = setDrawVariables(c, p, fill);
            int x = xys[0];
            int y = xys[1];
            if (x == -1 || y == -1) {
                setDrawnAmount(i);
                setCanvasFilled(true);
                return;
            }
            xy.add(xys);
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
            if (fill == FillStatus.FULL) {
                g.fillPolygon(xInts, yInts, 11);
                gr.fillPolygon(xInts, yInts, 11);
            } else if (fill == FillStatus.NONE) {
                g.drawPolygon(xInts, yInts, 11);
                gr.drawPolygon(xInts, yInts, 11);
            }
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
        } else if (fill == FillStatus.NONE) {
            g.drawPolygon(xInts, yInts, 11);
        }
    }

    @Override
    public List<int[]> getXY() {
        return xy;
    }


}
