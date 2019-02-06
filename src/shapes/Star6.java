package shapes;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.HashSet;

import patterns.Pattern;

public class Star6 extends ShapeAbstract implements Shape {
    static HashSet<int[]> xy = new HashSet<>();

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
            int[] xInts = new int[]{x, x + getWidth() / 3, x + getWidth() / 4, x + getWidth() / 2,
                    x + getWidth() - getWidth() / 4, x + getWidth() - getWidth() / 3, x + getWidth(),
                    x + getWidth() - getWidth() / 3, x + getWidth() - getWidth() / 4, x + getWidth() / 2,
                    x + getWidth() / 4, x + getWidth() / 3, x};
            int[] yInts = new int[]{y + getHeight() / 2, y + getHeight() / 3, y, y + getHeight() / 4, y,
                    y + getHeight() / 3, y + getHeight() / 2, y + getHeight() / 3 * 2, y + getHeight(),
                    y + getHeight() / 4 * 3, y + getHeight(), y + getHeight() / 3 * 2, y + getHeight() / 2};
            if (fill) {
                g.fillPolygon(xInts, yInts, 13);
                gr.fillPolygon(xInts, yInts, 13);
            } else {
                g.drawPolygon(xInts, yInts, 13);
                gr.drawPolygon(xInts, yInts, 13);
            }
        }
    }

    @Override
    public String name() {
        return "6-pointed_star";
    }

    @Override
    public boolean getCanvasFilled() {
        return canvasFilled;
    }

    @Override
    public void drawFromXY(Graphics g, Color c, int x, int y, int width, int height, boolean fill) {
        g.setColor(c);
        int[] xInts = new int[]{x, x + width / 3, x + width / 4, x + width / 2,
                x + width - width / 4, x + width - width / 3, x + width,
                x + width - width / 3, x + width - width / 4, x + width / 2,
                x + width / 4, x + width / 3, x};
        int[] yInts = new int[]{y + height / 2, y + height / 3, y, y + height / 4, y,
                y + height / 3, y + height / 2, y + height / 3 * 2, y + height,
                y + height / 4 * 3, y + height, y + height / 3 * 2, y + height / 2};
        if (fill) {
            g.fillPolygon(xInts, yInts, 13);
        } else {
            g.drawPolygon(xInts, yInts, 13);
        }
    }

    @Override
    public HashSet<int[]> getXY() {
        return xy;
    }
}
