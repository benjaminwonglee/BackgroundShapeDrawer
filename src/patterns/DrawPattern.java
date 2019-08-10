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

    public static DrawPattern getPatternFromName(String selectedItem) {
        for (DrawPattern drawPattern : values()) {
            if (drawPattern.getPatternName().equalsIgnoreCase(selectedItem)) {
                return drawPattern;
            }
        }
        return null;
    }

    public String getPatternName() {
        return patternName;
    }
}

