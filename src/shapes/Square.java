package shapes;

import misc.FillStatus;
import util.ColouringUtils;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Square extends ShapeAbstract implements Shape {
    static List<ShapeMetadata> shapeMetadata = new ArrayList<>();

    @Override
    public String name() {
        return ShapeName.SQUARE.getShapeName();
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
            if (ColouringUtils.isDarkColor(c)) {
                for (int i = 0; i < width / 2; i++) {
                    g.fillRect(x, y + i, width, width - i * 2);
                    c = ColouringUtils.lightenColor(c, getGradientFactor());
                    g.setColor(c);
                }
            } else {
                for (int i = 0; i < width / 2; i++) {
                    g.fillRect(x, y + i, width, width - i * 2);
                    c = ColouringUtils.darkenColor(c, getGradientFactor());
                    g.setColor(c);
                }
            }
        } else if (fill == FillStatus.NONE) {
            g.drawRect(x, y, width, width);
        }
    }

    @Override
    public List<ShapeMetadata> getXY() {
        return shapeMetadata;
    }

}
