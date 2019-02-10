package shapes;

import patterns.Pattern;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Octagon extends ShapeAbstract implements Shape {
    static List<int[]> xy = new ArrayList<>();

    @Override
    public void drawShape(Graphics g, Graphics gr, Color c, boolean fill) {
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
            int[] xInts = new int[]{x, x + getWidth() / 4, x + getWidth() / 4 * 3, x + getWidth(), x + getWidth(),
                    x + getWidth() / 4 * 3, x + getWidth() / 4, x, x};
            int[] yInts = new int[]{y + (getHeight() / 4), y, y, y + (getHeight() / 4), y + getHeight() / 4 * 3,
                    y + getHeight(), y + getHeight(), y + (getHeight() / 4) * 3, y + (getHeight() / 4)};
            if (fill) {
                g.fillPolygon(xInts, yInts, 9);
                gr.fillPolygon(xInts, yInts, 9);
            } else {
                g.drawPolygon(xInts, yInts, 9);
                gr.drawPolygon(xInts, yInts, 9);
            }
        }
    }

    @Override
    public String name() {
        return ShapeName.OCTAGON.getShapeName();
    }

    @Override
    public boolean getCanvasFilled() {
        return canvasFilled;
    }

    @Override
    public void drawFromXY(Graphics g, Color c, int x, int y, int width, int height, boolean fill) {
        g.setColor(c);
        int[] xInts = new int[]{x, x + width / 4, x + width / 4 * 3, x + width, x + width, x + width / 4 * 3,
                x + width / 4, x, x};
        int[] yInts = new int[]{y + (height / 4), y, y, y + (height / 4), y + height / 4 * 3, y + height, y + height,
                y + (height / 4) * 3, y + (height / 4)};
        if (fill) {
            g.fillPolygon(xInts, yInts, 9);
        } else {
            g.drawPolygon(xInts, yInts, 9);
        }
    }

    @Override
    public List<int[]> getXY() {
        return xy;
    }

}
