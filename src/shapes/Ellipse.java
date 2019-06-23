package shapes;

import misc.FillStatus;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Ellipse extends ShapeAbstract implements Shape {
    static List<ShapeMetadata> shapeMetadata = new ArrayList<>();

    @Override
    public String name() {
        return ShapeName.ELLIPSE.getShapeName();
    }

    @Override
    public boolean getCanvasFilled() {
        return canvasFilled;
    }

    @Override
    public void drawFromXY(Graphics g, Color c, int x, int y, int width, int height, FillStatus fill) {
        g.setColor(c);
        if (fill == FillStatus.FULL) {
            g.fillOval(x, y, width, height);
        } // TODO: Gradient Fill
        else if (fill == FillStatus.NONE) {
            g.drawOval(x, y, width, height);
        }
    }

    @Override
    public List<ShapeMetadata> getXY() {
        return shapeMetadata;
    }
}
