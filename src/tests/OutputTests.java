package tests;

import static org.junit.Assert.*;

import java.awt.Rectangle;
import java.util.ArrayList;

import javax.swing.JPanel;

import org.junit.Test;

import main.ShapePanel;
import output.PNGOutput;
import shapes.Circle;
import shapes.Shape;

public class OutputTests {
	/**
	 * Testing should be in the similar format to:
	 * png.outputToFile("output.txt", getShapes(), canvasRed, canvasGreen,
	 * canvasBlue); png.pngFromFile(this, "output.txt", "output.png");
	 */

	private Rectangle testCanvasSize = new Rectangle(0, 0, 10, 10);

	@Test
	public void testPNGOutputToFile1() {
		PNGOutput png = createPNGOutput();
		ArrayList<Shape> shapes = new ArrayList<Shape>();
		ShapePanel sp = new ShapePanel();
		sp.setCanvas(sp);
		sp.draw(new TestCanvas().getGraphics(), png.getPng().getGraphics());

		shapes.add(new Circle());
		png.outputToFile("test.txt", shapes, 0, 0, 0);

		fail();
		// png.getColorPixelArray()
	}

	@Test
	public void testPNGOutputToPng1() {
		PNGOutput png = createPNGOutput();
		png.outputToFile("test.txt", null, 0, 0, 0);
		png.pngFromFile(null, "test.txt", "test.png");
		fail();
		// png.getColorPixelArray()
	}

	private class TestCanvas extends JPanel{
		public TestCanvas(){
			setBounds(testCanvasSize);
		}
	}

	public PNGOutput createPNGOutput() {
		PNGOutput png = new PNGOutput(testCanvasSize);
		return png;
	}
}
