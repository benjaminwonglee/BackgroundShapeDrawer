package shapes;

import misc.FillStatus;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Lightning extends ShapeAbstract implements Shape {
    static List<ShapeMetadata> shapeMetadata = new ArrayList<>();

    @Override
    public String name() {
        return ShapeName.LIGHTNING.getShapeName();
    }

    @Override
    public boolean getCanvasFilled() {
        return canvasFilled;
    }

    @Override
    public void drawFromXY(Graphics g, Color c, int x, int y, int width, int height, FillStatus fill) {
        g.setColor(c);
        int[] xInts = new int[]{x + width / 5 * 2, x + width, x + width / 5 * 3, x + width, x + width / 5 * 3,
                x + width, x + width / 5, x + width / 5 * 2, x, x + width / 5 * 2, x, x + width / 5 * 2};
        int[] yInts = new int[]{y, y, y + height / 7 * 2, y + height / 7 * 2, y + height / 7 * 4, y + height / 7 * 4,
                y + height, y + height / 7 * 5, y + height / 7 * 5, y + height / 7 * 3, y + height / 7 * 3,
                y + height / 7 * 3};
        if (fill == FillStatus.FULL) {
            g.fillPolygon(xInts, yInts, 11);
        } // TODO: Gradient Fill
        else if (fill == FillStatus.NONE) {
            g.drawPolygon(xInts, yInts, 11);
        }
    }

    @Override
    public List<ShapeMetadata> getXY() {
        return shapeMetadata;
    }
}
