package tests;

import java.awt.Rectangle;

import org.junit.Test;

import main.PNGOutput;

public class OutputTests {

	@Test
	public void testPNGOutput1() {
		PNGOutput png = createPNGOutput();
		png.outputToFile("test.txt", 0, 0, 0);
		png.pngFromFile("test.txt", "test.png");
		// png.getColorPixelArray()
	}

	public PNGOutput createPNGOutput() {
		PNGOutput png = new PNGOutput(new Rectangle(0, 0, 10, 10));
		return png;
	}
}
