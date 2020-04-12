package patterns;

public enum DrawPattern {
    RANDOM("Random"),
    ALIGNED("Aligned"),
    ALTERNATING("Alternating"),
    BORDERING("Bordering"),
    CROSS_ALTERNATING("Cross Alternating"),
    ;

    private final String patternName;

    DrawPattern(String patternName) {
        this.patternName = patternName;
    }

    public static DrawPattern parse(String patternStr) {
        String upperCase = patternStr.toUpperCase().trim();
        upperCase = upperCase.replaceAll(" ", "_");
        for (DrawPattern drawPattern : values()) {
            if (upperCase.equals(drawPattern.name())) {
                return drawPattern;
            }
        }
        return DrawPattern.RANDOM;
    }

    public static DrawPattern parseRelaxed(String patternStr) {
        for (DrawPattern drawPattern : values()) {
            if (patternStr.equals(drawPattern.getPatternName())) {
                return drawPattern;
            }
        }
        return DrawPattern.RANDOM;
    }

    public static DrawPattern findNext(DrawPattern currPattern) {
        DrawPattern[] values = values();
        for (int i = 0; i < values.length; i++) {
            DrawPattern pattern = values[i];
            if (pattern == currPattern) {
                if (i == values.length - 1) {
                    return values[0];
                } else {
                    return values[i + 1];
                }
            }
        }
        return DrawPattern.RANDOM;
    }

    public String getPatternName() {
        return patternName;
    }
}

