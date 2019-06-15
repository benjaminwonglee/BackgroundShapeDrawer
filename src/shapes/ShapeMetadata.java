package shapes;

public class ShapeMetadata {

    int x = Integer.MIN_VALUE;
    int y = Integer.MIN_VALUE;
    int width = Integer.MIN_VALUE;
    int height = Integer.MIN_VALUE;
    int fillStatus = Integer.MIN_VALUE;
    int rgb = Integer.MIN_VALUE;

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getFillStatus() {
        return fillStatus;
    }

    public void setFillStatus(int fillStatus) {
        this.fillStatus = fillStatus;
    }

    public int getRgb() {
        return rgb;
    }

    public void setRgb(int rgb) {
        this.rgb = rgb;
    }
}
