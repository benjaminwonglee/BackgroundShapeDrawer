package tests;

import static org.junit.Assert.fail;

import java.awt.Rectangle;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.junit.Test;

import main.ShapePanel;
import output.PNGOutput;
import shapes.Shape;
import shapes.ShapeAbstract;
import shapes.ShapeAbstract.DrawPattern;
import shapes.Triangle;

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

		// Adds to ShapePanel shapes ArrayList.
		sp.createShape("Circle", testNum);
		sp.createShape("Square", testNum);
		ArrayList<Shape> shapes = sp.getShapes();

		// Before drawing, set the abstract variables
		sp.draw(new TestCanvas().getGraphics(), png.getPng().getGraphics());
		png.createAndSetFile("test.txt");
		png.outputToFile(shapes, 0, 0, 0);

		Scanner sc = null;
		try {
			sc = new Scanner(new File("test.txt"));
		} catch (FileNotFoundException e) {
			fail("File 'test.txt' wasn't created.");
		}
		int count = 0;

		String nm = null;
		int x = 0;
		int y = 0;
		int wd = 0;
		int ht = 0;
		int fill = 0;
		int rgb = 0;
		try {
			while (sc.hasNext()) {
				count++;
				nm = sc.next();
				x = sc.nextInt();
				y = sc.nextInt();
				wd = sc.nextInt();
				ht = sc.nextInt();
				fill = sc.nextInt();
				rgb = sc.nextInt();
			}
			if (count != testNum * 2) {
				fail("Outputted wrong number of shapes, should be: " + testNum + " but was: " + count);
			}
		} catch (InputMismatchException e) {
			fail("Formatting of txt file was incorrect. nm: " + nm + " x: " + x + " y: " + y + " wd: " + wd + " ht: "
					+ ht + " fill: " + fill + " rgb: " + rgb);
		}
		sc.close();
	}

	@Test
	public void drawCorrectWidth() {
		PNGOutput png = createPNGOutput();
		ShapePanel sp = new ShapePanel();
		int testNum = 2;

		// Adds to ShapePanel shapes arraylist.
		sp.createShape("Triangle", testNum);
		ArrayList<Shape> shapes = sp.getShapes();

		// Before drawing, set the abstract variables
		ShapeAbstract.setPattern(DrawPattern.ALIGNED);
		ShapeAbstract.setWidth(4);
		sp.draw(new TestCanvas().getGraphics(), png.getPng().getGraphics());
		Triangle t = (Triangle) shapes.get(0);
		ArrayList<int[]> vars = t.getXY();
		int[] first = vars.get(0);
		int[] second = vars.get(1);
		if (first[0] != 0) {
			fail();
		}
		if (second[0] != 4) {
			fail();
		}
		png.createAndSetFile("test.txt");
		png.outputToFile(shapes, 0, 0, 0);
		Scanner sc = null;
		try {
			sc = new Scanner(new File("test.txt"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		// TODO: test the file output
		while (sc.hasNext()) {
			System.out.println(sc.next());
		}
	}

	public PNGOutput createPNGOutput() {
		PNGOutput png = new PNGOutput(testCanvasSize);
		return png;
	}

	private class TestCanvas extends JPanel {
		private static final long serialVersionUID = -1863297224513004580L;

		public TestCanvas() {
			JFrame frame = new JFrame();
			frame.setBounds(testCanvasSize);
			frame.add(this);
			frame.setVisible(true);
		}
	}
}
