package shapes;

import misc.FillStatus;
import patterns.Pattern;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Hexagon extends ShapeAbstract implements Shape {
    static List<ShapeMetadata> shapeMetadata = new ArrayList<>();

    @Override
    public void drawShape(Graphics g, Graphics gr, Color c, FillStatus fill) {
        g.setColor(c);
        gr.setColor(c);
        Pattern p = selectPattern();

        for (int i = 0; i < getAmount(); i++) {
            ShapeMetadata metadata = setDrawVariables(c, p, fill);
            int x = metadata.getX();
            int y = metadata.getY();
            if (x == -1 || y == -1) {
                setDrawnAmount(i);
                setCanvasFilled(true);
                return;
            }
            shapeMetadata.add(metadata);
            int width = getWidth();
            int height = getHeight();

            int[] xInts = new int[]{x, x + width / 3, x + width / 3 * 2, x + width,
                    x + width / 3 * 2, x + width / 3, x};
            int[] yInts = new int[]{y + height / 2, y, y, y + height / 2, y + height, y + height,
                    y + height / 2};
            if (fill == FillStatus.FULL) {
                g.fillPolygon(xInts, yInts, 7);
                gr.fillPolygon(xInts, yInts, 7);
            }  // TODO: Gradient fill
            else if (fill == FillStatus.NONE) {
                g.drawPolygon(xInts, yInts, 7);
                gr.drawPolygon(xInts, yInts, 7);
            }
        }
    }

    @Override
    public String name() {
        return ShapeName.HEXAGON.getShapeName();
    }

    @Override
    public boolean getCanvasFilled() {
        return canvasFilled;
    }

    @Override
    public void drawFromXY(Graphics g, Color c, int x, int y, int width, int height, FillStatus fill) {
        g.setColor(c);
        int[] xInts = new int[]{x, x + width / 3, x + width / 3 * 2, x + width, x + width / 3 * 2, x + width / 3, x};
        int[] yInts = new int[]{y + height / 2, y, y, y + height / 2, y + height, y + height, y + height / 2};
        if (fill == FillStatus.FULL) {
            g.fillPolygon(xInts, yInts, 7);
        } else if (fill == FillStatus.NONE) {
            g.drawPolygon(xInts, yInts, 7);
        }
    }

    @Override
    public List<ShapeMetadata> getXY() {
        return shapeMetadata;
    }
}
