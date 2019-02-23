package shapes;

import misc.FillStatus;
import patterns.Pattern;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Rectangle extends ShapeAbstract implements Shape {
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
            if (fill == FillStatus.FULL) {
                g.fillRect(x, y, getWidth(), getHeight());
                gr.fillRect(x, y, getWidth(), getHeight());
            } else if (fill == FillStatus.NONE) {
                g.drawRect(x, y, getWidth(), getHeight());
                gr.drawRect(x, y, getWidth(), getHeight());
            }
        }
    }

    @Override
    public String name() {
        return ShapeName.RECTANGLE.getShapeName();
    }

    @Override
    public boolean getCanvasFilled() {
        return canvasFilled;
    }

    @Override
    public void drawFromXY(Graphics g, Color c, int x, int y, int width, int height, FillStatus fill) {
        g.setColor(c);
        if (fill == FillStatus.FULL) {
            g.fillRect(x, y, width, height);
        } else if (fill == FillStatus.NONE) {
            g.drawRect(x, y, width, height);
        }
    }

    @Override
    public List<int[]> getXY() {
        return xy;
    }

}
