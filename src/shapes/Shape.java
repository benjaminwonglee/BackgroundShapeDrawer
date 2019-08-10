package shapes;

public enum Shape {
    CIRCLE("Circle", new Circle()),
    ELLIPSE("Ellipse", new Ellipse()),
    HEXAGON("Hexagon", new Hexagon()),
    LIGHTNING("Lightning", new Lightning()),
    OCTAGON("Octagon", new Octagon()),
    RECTANGLE("Rectangle", new Rectangle()),
    SQUARE("Square", new Square()),
    STAR5("5-Pointed Star", new Star5()),
    STAR6("6-Pointed Star", new Star6()),
    TRIANGLE("Triangle", new Triangle()),
    ;

    private final String shapeName;
    private final IShape shape;

    Shape(String shapeName, IShape shape) {
        this.shapeName = shapeName;
        this.shape = shape;
    }

    public static Shape getNext(Shape shape) {
        Shape[] values = Shape.values();
        int ordinal = shape.ordinal();
        return ordinal + 1 < values.length ? values[ordinal + 1] : values[0];
    }

    public static IShape getShapeFromName(String shapeName) {
        Shape shapeEnum = getShapeEnumFromName(shapeName);
        return shapeEnum != null ? shapeEnum.getShape() : null;
    }

    public static Shape getShapeEnumFromName(String shapeName) {
        Shape[] values = Shape.values();
        for (Shape shape : values) {
            if (shape.getShapeName().equalsIgnoreCase(shapeName)) {
                return shape;
            }
        }
        return null;
    }

    public String getShapeName() {
        return shapeName;
    }

    public IShape getShape() {
        return shape;
    }
}
