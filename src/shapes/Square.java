package shapes;

import misc.FillStatus;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Square extends ShapeAbstract implements IShape {

    private static List<ShapeMetadata> shapeMetadata = new ArrayList<>();

    @Override
    public String name() {
        return Shape.SQUARE.getShapeName();
    }

    @Override
    public boolean getCanvasFilled() {
        return canvasFilled;
    }

    @Override
    public void drawFromXY(Graphics g, Color c, int x, int y, int width, int height, FillStatus fill) {
        g.setColor(c);
        height = width;
        if (fill == FillStatus.FULL) {
            g.fillRect(x, y, width, height);
        } else if (fill == FillStatus.GRADIENT) {
            drawGradientRectangle(g, c, x, y, width, height);
        } else if (fill == FillStatus.NONE) {
            g.drawRect(x, y, width, height);
        }
    }

    @Override
    public List<ShapeMetadata> getXY() {
        return shapeMetadata;
    }

    @Override
    public void clearShape() {
        shapeMetadata.clear();
    }
}
