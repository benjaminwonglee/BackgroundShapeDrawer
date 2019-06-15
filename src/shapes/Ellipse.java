package shapes;

import misc.FillStatus;
import patterns.Pattern;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Ellipse extends ShapeAbstract implements Shape {
    static List<ShapeMetadata> shapeMetadata = new ArrayList<>();

    @Override
    public void drawShape(Graphics g, Graphics gr, Color c, FillStatus fill) {
        g.setColor(c);
        gr.setColor(c);
        Pattern p = selectPattern();

        for (int i = 0; i < getAmount(); i++) {
            ShapeMetadata metadata = setDrawVariables(c, p, fill);
            if (metadata.getX() == -1 || metadata.getY() == -1) {
                setDrawnAmount(i);
                setCanvasFilled(true);
                return;
            }
            shapeMetadata.add(metadata);
            if (fill == FillStatus.FULL) {
                g.fillOval(metadata.getX(), metadata.getY(), getWidth(), getHeight());
                gr.fillOval(metadata.getX(), metadata.getY(), getWidth(), getHeight());
            } else if (fill == FillStatus.NONE) {
                g.drawOval(metadata.getX(), metadata.getY(), getWidth(), getHeight());
                gr.drawOval(metadata.getX(), metadata.getY(), getWidth(), getHeight());
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
