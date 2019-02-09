package shapes;

import java.awt.Color;
import java.awt.Graphics;
import java.util.HashSet;

import patterns.Pattern;

public class Square extends ShapeAbstract implements Shape {
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
            if (fill) {
                g.fillRect(x, y, getWidth(), getWidth());
                gr.fillRect(x, y, getWidth(), getWidth());
            } else {
                g.drawRect(x, y, getWidth(), getWidth());
                gr.drawRect(x, y, getWidth(), getWidth());
            }
        }
    }

    @Override
    public String name() {
        return ShapeName.SQUARE.getShapeName();
    }

    @Override
    public boolean getCanvasFilled() {
        return canvasFilled;
    }

    @Override
    public void drawFromXY(Graphics g, Color c, int x, int y, int width, int height, boolean fill) {
        g.setColor(c);
        if (fill) {
            g.fillRect(x, y, width, width);
        } else {
            g.drawRect(x, y, width, width);
        }
    }

    @Override
    public HashSet<int[]> getXY() {
        return xy;
    }

}
