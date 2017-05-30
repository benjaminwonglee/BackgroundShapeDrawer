package patterns;

public class AlignedPattern implements Pattern {

	private int width;
	private int height;

	@Override
	public int xInCanvas() {
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

	@Override
	public int yInCanvas() {
		if (xCursor >= canvasSize.getWidth()) {
			yCursor += height;
			if (yCursor + height >= canvasSize.height) {
				// Stop
				return -1;
			}
			return yCursor - height;
		}
		return yCursor;
	}

	@Override
	public int getWidth() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getHeight() {
		return 0;
	}

	@Override
	public void setWidth(int width) {
		this.width = width;
	}

	@Override
	public void setHeight(int height) {
		this.height = height;
	}
}
