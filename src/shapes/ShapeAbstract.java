package shapes;

import misc.FillStatus;
import patterns.*;
import util.ColouringUtils;

import java.awt.*;

public abstract class ShapeAbstract implements IShape {

    private static final int gradientFactor = 3;
    private static java.awt.Rectangle canvasSize;
    // Shape size variables
    private static int width = 90;
    private static int height = 90;
    private static int prevWidth = 90;
    private static int prevHeight = 90;
    // Cursor variables
    private static int xCursor = 0;
    private static int yCursor = 0;
    // Pattern variables
    private static DrawPattern pattern = DrawPattern.RANDOM;
    private static int crossAlternatingInt = 0;
    // Canvas variables
    protected boolean canvasFilled = false;
    // Amount variables
    private int amount = 0;
    private int drawnAmount = 0;
    // Colour variables
    private static Color initialColor = new Color(0, 0, 0);

    public static int getWidth() {
        return width;
    }

    public static void setWidth(int width) {
        ShapeAbstract.width = width;
    }

    public static int getHeight() {
        return height;
    }

    public static void setHeight(int height) {
        ShapeAbstract.height = height;
    }

    public static int getPrevWidth() {
        return prevWidth;
    }

    public static void setPrevWidth(int prevWidth) {
        ShapeAbstract.prevWidth = prevWidth;
    }

    public static int getPrevHeight() {
        return prevHeight;
    }

    public static void setPrevHeight(int prevHeight) {
        ShapeAbstract.prevHeight = prevHeight;
    }

    public static void setPattern(DrawPattern p) {
        ShapeAbstract.pattern = p;
    }

    public static void setXCursor(int xCursor) {
        ShapeAbstract.xCursor = xCursor;
    }

    public static void setYCursor(int yCursor) {
        ShapeAbstract.yCursor = yCursor;
    }

    public static void setCanvasSize(java.awt.Rectangle canvasSize) {
        ShapeAbstract.canvasSize = canvasSize;
    }

    public void drawShape(Graphics g, Graphics pngGraphics, Color c, FillStatus fill) {
        g.setColor(c);
        pngGraphics.setColor(c);
        IPattern p = selectPattern();
        initialColor = c;
        for (int i = 0; i < getAmount(); i++) {
            ShapeMetadata metadata = setDrawVariables(c, p, fill);
            if (metadata.getX() == -1 || metadata.getY() == -1) {
                setDrawnAmount(i);
                setCanvasFilled(true);
                return;
            }
            getXY().add(metadata);
            drawFromXY(g, c, metadata.getX(), metadata.getY(), ShapeAbstract.getWidth(), ShapeAbstract.getHeight(), fill);
            drawFromXY(pngGraphics, c, metadata.getX(), metadata.getY(), ShapeAbstract.getWidth(), ShapeAbstract.getHeight(), fill);
        }
    }

    @Override
    public ShapeMetadata setDrawVariables(Color c, IPattern p, FillStatus fill) {
        ShapeMetadata metadata = new ShapeMetadata();

        int x = p.xInCanvas(xCursor, yCursor);
        int y = p.yInCanvas(xCursor, yCursor);
        xCursor = x + width;
        yCursor = y;

        metadata.setX(x);
        metadata.setY(y);
        metadata.setWidth(getWidth());
        metadata.setHeight(getHeight());
        metadata.setFillStatus(fill.ordinal());
        metadata.setRgb(c.getRGB());
        return metadata;
    }


    @Override
    public IPattern selectPattern() {
        // Default to random pattern
        IPattern p = new RandomPattern();
        if (pattern == DrawPattern.ALIGNED) {
            p = new AlignedPattern();
        } else if (pattern == DrawPattern.ALTERNATING) {
            xCursor -= width;
            p = new AlternatingPattern(1);
        } else if (pattern == DrawPattern.BORDERING) {
            p = new BorderingPattern();
        } else if (pattern == DrawPattern.CROSS_ALTERNATING) {
            // The 2nd shape for cross alternating
            if (crossAlternatingInt == 0) {
                xCursor -= width;
                crossAlternatingInt = 1;
            } else {
                crossAlternatingInt = 0;
            }
            p = new AlternatingPattern(crossAlternatingInt);
        }
        p.setWidth(width);
        p.setHeight(height);
        p.setCanvasSize(canvasSize);
        return p;
    }

    @Override
    public int getAmount() {
        return amount;
    }

    @Override
    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public int getDrawnAmount() {
        return drawnAmount;
    }

    @Override
    public void setDrawnAmount(int amountDrawn) {
        this.drawnAmount = amountDrawn;
    }

    @Override
    public boolean getCanvasFilled() {
        return canvasFilled;
    }

    @Override
    public void setCanvasFilled(boolean isCanvasFilled) {
        canvasFilled = isCanvasFilled;
    }

    static void drawGradientOval(Graphics g, Color c, int x, int y, int width, int height) {
        int minDimension = Math.min(width, height);
        for (int i = 0; i < minDimension / 2; i++) {
            g.fillOval(x + i, y + i, width - i * 2, height - i * 2);
            int[] colorArray = incrementGradient(c);
            c = new Color(colorArray[0], colorArray[1], colorArray[2]);
            g.setColor(c);
        }
    }

    static void drawGradientRectangle(Graphics g, Color c, int x, int y, int width, int height) {
        int minDimension = Math.min(width, height);
        for (int i = 0; i < minDimension / 2; i++) {
            g.fillRect(x + i, y + i, width - i * 2, height - i * 2);
            int[] colorArray = incrementGradient(c);
            c = new Color(colorArray[0], colorArray[1], colorArray[2]);
            g.setColor(c);
        }
    }

    static int[] incrementGradient(Color c) {
        int[] colorArray = new int[]{c.getRed(), c.getGreen(), c.getBlue()};
        for (int iColorVal = 0; iColorVal < colorArray.length; iColorVal++) {
            int colorVal = colorArray[iColorVal];
            if (ColouringUtils.isDarkColor(initialColor)) {
                colorVal += getGradientFactor();
                if (colorVal > 255) {
                    colorVal = 255;
                }
            } else {
                colorVal -= getGradientFactor();
                if (colorVal < 0) {
                    colorVal = 0;
                }
            }
            colorArray[iColorVal] = colorVal;
        }
        return colorArray;
    }

    static int getGradientFactor() {
        return gradientFactor;
    }

    void drawShapeWithColorGradient(Graphics g, Color c, int x, int y, int width, int height, int nPoints) {
        int[] tempXCoords = getXCoords(x, width);
        int[] tempYCoords = getYCoords(y, height);
        int tempX = x;
        int tempY = y;
        int tempWidth = width;
        int tempHeight = height;
        int incrGradient = 0;
        int sizeBasedColorIncr = Math.min(width, height) / 150;
        int maxIterations = Math.max(width, height);
        for (int i = 0; i < maxIterations; i++) {
            g.fillPolygon(tempXCoords, tempYCoords, nPoints);
            tempX += 1;
            tempY += 1;
            tempWidth -= 2;
            tempHeight -= 2;
            tempXCoords = getXCoords(tempX, tempWidth);
            tempYCoords = getYCoords(tempY, tempHeight);

            if (incrGradient == sizeBasedColorIncr) {
                incrGradient = -1;
                int[] colorArray = incrementGradient(c);
                c = new Color(colorArray[0], colorArray[1], colorArray[2]);
                if (colorArray[0] == 0 && colorArray[1] == 0 && colorArray[2] == 0) {
                    break;
                }
                if (colorArray[0] == 255 && colorArray[1] == 255 && colorArray[2] == 255) {
                    break;
                }
            }
            incrGradient++;

            if (tempWidth <= width / 8 || tempHeight <= height / 8) {
                break;
            }
            g.setColor(c);
        }
    }
}
