package shapes;

import misc.FillStatus;
import patterns.IPattern;

import java.awt.*;
import java.util.List;

public interface IShape {

    void drawShape(Graphics g, Graphics pngGraphics, Color c, FillStatus fill);

    String name();

    ShapeMetadata setDrawVariables(Color c, IPattern p, FillStatus fill);

    IPattern selectPattern();

    int getAmount();

    void setAmount(int amount);

    int getDrawnAmount();

    void setDrawnAmount(int amountDrawn);

    boolean getCanvasFilled();

    void setCanvasFilled(boolean isCanvasFilled);

    void clearShape();

    void drawFromXY(Graphics graphics, Color color, int x, int y, int width, int height, FillStatus fill);

    List<ShapeMetadata> getXY();
}
