package shapes;

import misc.FillStatus;
import patterns.Pattern;
import util.Utils;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Circle extends ShapeAbstract implements Shape {
    static List<int[]> xy = new ArrayList<>();

    @Override
    public void drawShape(Graphics g, Graphics gr, Color c, FillStatus fill) {
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
            drawFromXY(g, c, xys[0], xys[1], getWidth(), getWidth(), fill);
            drawFromXY(gr, c, xys[0], xys[1], getWidth(), getWidth(), fill);
        }
    }

    @Override
    public String name() {
        return ShapeName.CIRCLE.getShapeName();
    }

    @Override
    public boolean getCanvasFilled() {
        return canvasFilled;
    }

    @Override
    public void drawFromXY(Graphics g, Color c, int x, int y, int width, int height, FillStatus fill) {
        g.setColor(c);
        if (fill == FillStatus.FULL) {
            g.fillOval(x, y, width, width);
        } else if (fill == FillStatus.GRADIENT) {
            int maxColorShade = Utils.findMaxColorShade(c);
            int[] colorArray = new int[]{c.getRed(), c.getGreen(), c.getBlue()};
            for (int i = 0; i < width / 2; i++) {
                g.fillOval(x, y + i, width, width - i * 2);
                if (colorArray[maxColorShade] > 2) {
                    colorArray[maxColorShade] -= 3;
                }
                g.setColor(new Color(colorArray[0], colorArray[1], colorArray[2]));
            }
        } else if (fill == FillStatus.NONE) {
            g.drawOval(x, y, width, width);
        }
    }

    @Override
    public List<int[]> getXY() {
        return xy;
    }
}
