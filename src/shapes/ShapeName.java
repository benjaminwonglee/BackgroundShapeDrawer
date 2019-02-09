package shapes;

public enum ShapeName {
    CIRCLE,
    ELLIPSE,
    HEXAGON,
    LIGHTNING,
    OCTAGON,
    RECTANGLE,
    SQUARE,
    STAR5,
    STAR6,
    TRIANGLE;

    public String getShapeName() {
        // Special cases
        if (this == STAR5) {
            return "5-Pointed Star";
        } else if (this == STAR6) {
            return "6-Pointed Star";
        }
        String lower = name().substring(1).toLowerCase();
        return name().substring(0, 1) + lower;
    }
}
