package shapes;

import patterns.Pattern;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Ellipse extends ShapeAbstract implements Shape {
    static List<int[]> xy = new ArrayList<>();

    @Override
    public void drawShape(Graphics g, Graphics gr, Color c, boolean fill) {
        g.setColor(c);
        gr.setColor(c);
        Pattern p = selectPattern();

        for (int i = 0; i < getAmount(); i++) {
            int[] xys = setDrawVariables(c, p, fill);
            if (xys[0] == -1 || xys[1] == -1) {
                setDrawnAmount(i);
                setCanvasFilled(true);
                return;
            }
            xy.add(xys);
            if (fill) {
                g.fillOval(xys[0], xys[1], getWidth(), getHeight());
                gr.fillOval(xys[0], xys[1], getWidth(), getHeight());
            } else {
                g.drawOval(xys[0], xys[1], getWidth(), getHeight());
                gr.drawOval(xys[0], xys[1], getWidth(), getHeight());
            }
        }
    }

    @Override
    public String name() {
        return ShapeName.ELLIPSE.getShapeName();
    }

    @Override
    public boolean getCanvasFilled() {
        return canvasFilled;
    }

    @Override
    public void drawFromXY(Graphics g, Color c, int x, int y, int width, int height, boolean fill) {
        g.setColor(c);
        if (fill) {
            g.fillOval(x, y, width, height);
        } else {
            g.drawOval(x, y, width, height);
        }
    }

    @Override
    public List<int[]> getXY() {
        return xy;
    }

}
