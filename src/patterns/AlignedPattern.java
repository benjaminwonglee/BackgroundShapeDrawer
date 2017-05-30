package patterns;

public class AlignedPattern extends Pattern{
	
	public int alignedXIntegerInCanvas() {
		xCursor += width;
		if (xCursor >= canvasSize.getWidth()) {
			if (alignedYIntegerInCanvas() != -1) {
				xCursor = getWidth();
			} else {
				// Stop
				return -1;
			}
		}
		return xCursor - getWidth();
	}

	public int alignedYIntegerInCanvas() {
		if (xCursor >= canvasSize.getWidth()) {
			yCursor += getHeight();
			if (yCursor + getHeight() >= canvasSize.getHeight()) {
				// Stop
				return -1;
			}
			return yCursor - getHeight();
		}
		return yCursor;
	}

}
