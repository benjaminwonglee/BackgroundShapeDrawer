package tests;

import static org.junit.Assert.fail;

import java.awt.Rectangle;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.junit.Test;

import main.ShapePanel;
import output.PNGOutput;
import shapes.Shape;

public class OutputTests {
	/**
	 * Testing should be in the similar format to:
	 * png.outputToFile("output.txt", getShapes(), canvasRed, canvasGreen,
	 * canvasBlue); png.pngFromFile(this, "output.txt", "output.png");
	 */

	private Rectangle testCanvasSize = new Rectangle(0, 0, 100, 100);

	@Test
	public void testPNGOutputToFile1() {
		ShapePanel sp = new ShapePanel();
		PNGOutput png = createPNGOutput();
		int testNum = 5;

		// Adds to ShapePanel shapes arraylist.
		sp.createShape("Circle", testNum);
		sp.createShape("Square", testNum);
		ArrayList<Shape> shapes = sp.getShapes();

		// Before drawing, set the abstract variables
		sp.draw(new TestCanvas().getGraphics(), png.getPng().getGraphics());

		//png.outputToFile("test.txt", shapes, 0, 0, 0);

		Scanner sc = null;
		try {
			sc = new Scanner(new File("test.txt"));
		} catch (FileNotFoundException e) {
			fail("File 'test.txt' wasn't created.");
		}
		try {
			int count = 0;
			while (sc.hasNext()) {
				count++;
				String nm = sc.next();
				int x = sc.nextInt();
				int y = sc.nextInt();
				int wd = sc.nextInt();
				int ht = sc.nextInt();
				int fill = sc.nextInt();
			}
			if(count > testNum){
				fail("outputted too many shapes");
			}
		} catch (NumberFormatException e) {
			fail("Formatting of txt file was incorrect.");
		}
		sc.close();
	}

	@Test
	public void testPNGOutputToPng1() {
		ShapePanel sp = new ShapePanel();
		PNGOutput png = createPNGOutput();

		// Adds to ShapePanel shapes arraylist.
		sp.createShape("Circle", 5);
		ArrayList<Shape> shapes = sp.getShapes();

		// Before drawing, set the abstract variables
		sp.draw(new TestCanvas().getGraphics(), png.getPng().getGraphics());

		png.outputToFile("test.txt", shapes, 0, 0, 0);
	}

	public PNGOutput createPNGOutput() {
		PNGOutput png = new PNGOutput(testCanvasSize);
		return png;
	}

	private class TestCanvas extends JPanel {
		public TestCanvas() {
			JFrame frame = new JFrame();
			frame.setBounds(testCanvasSize);
			frame.add(this);
			frame.setVisible(true);
		}
	}
}
