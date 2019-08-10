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
        if (fill == FillStatus.FULL) {
            g.fillRect(x, y, width, width);
        } else if (fill == FillStatus.GRADIENT) {
            for (int i = 0; i < width / 2; i++) {
                g.fillRect(x, y + i, width, width - i * 2);
                int[] colorArray = incrementGradient(c);
                c = new Color(colorArray[0], colorArray[1], colorArray[2]);
                g.setColor(c);
            }
        } else if (fill == FillStatus.NONE) {
            g.drawRect(x, y, width, width);
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
