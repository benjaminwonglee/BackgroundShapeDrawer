package misc;

public enum FillStatus {
    NONE,
    GRADIENT,
    FULL;

    public static FillStatus getNext(FillStatus fillStatus) {
        FillStatus[] values = FillStatus.values();
        int ordinal = fillStatus.ordinal();
        return ordinal + 1 < values.length ? values[ordinal + 1]
                : values[0];
    }

    public String getFormattedName() {
        return name().substring(0, 1).toUpperCase() + name().substring(1).toLowerCase();
    }
}
