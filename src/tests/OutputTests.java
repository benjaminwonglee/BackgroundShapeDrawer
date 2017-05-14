package tests;

import static org.junit.Assert.*;

import java.awt.Rectangle;

import org.junit.Test;

import output.PNGOutput;

public class OutputTests {

	@Test
	public void testPNGOutputToFile1() {
		PNGOutput png = createPNGOutput();
		png.outputToFile("test.txt", 0, 0, 0);

		fail();
		// png.getColorPixelArray()
	}

	@Test
	public void testPNGOutputToPng1() {
		PNGOutput png = createPNGOutput();
		png.outputToFile("test.txt", 0, 0, 0);
		png.pngFromFile(null, "test.txt", "test.png");
		fail();
		// png.getColorPixelArray()
	}

	public PNGOutput createPNGOutput() {
		PNGOutput png = new PNGOutput(new Rectangle(0, 0, 10, 10));
		return png;
	}
}
