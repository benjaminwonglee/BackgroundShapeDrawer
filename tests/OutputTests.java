import org.junit.Test;
import output.PNGOutput;
import panels.ShapePanel;
import shapes.ShapeName;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import java.util.Scanner;

import static org.junit.Assert.fail;

public class OutputTests {
    /*
     * Testing should be in a format similar to:
     * png.outputToFile("testOutput.txt", getShapes(), canvasRed, canvasGreen, canvasBlue);
     *
     * png.pngFromFile(this, "testOutput.txt", "testOutput.png");
     */

    private final Rectangle testCanvasSize = new Rectangle(0, 0, 100, 100);

    @Test
    public void testPNGOutputToFile1() {
        ShapePanel sp = new ShapePanel();
        PNGOutput png = createPNGOutput();
        int numShapes = 5;

        // Adds to ShapePanel shapes ArrayList.
        sp.addShapeToDrawQueue(ShapeName.CIRCLE, numShapes);
        sp.addShapeToDrawQueue(ShapeName.SQUARE, numShapes);

        // Before drawing, set the abstract variables
        sp.draw(new TestCanvas().getGraphics(), png.getPng().getGraphics());
        PNGOutput.outputToFile(sp, new Color(0, 0, 0), "test.txt");

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
            // Skip background colour line
            sc.nextLine();
            // Skip theme name line
            sc.nextLine();
            while (sc.hasNext()) {
                count++;
                String[] variables = sc.next().split(",");
                try {
                    nm = variables[0];
                    x = Integer.parseInt(variables[1]);
                    y = Integer.parseInt(variables[2]);
                    wd = Integer.parseInt(variables[3]);
                    ht = Integer.parseInt(variables[4]);
                    fill = Integer.parseInt(variables[5]);
                    rgb = Integer.parseInt(variables[6]);
                } catch (IndexOutOfBoundsException e) {
                    fail("Not enough variables detected. Expected: 7 variables but had: " + variables.length + "\n" + e);
                } catch (NumberFormatException e) {
                    fail("Failed to parse a variable as an integer:\n" + e);
                }
            }
            if (count != numShapes * 2) {
                fail("Outputted wrong number of shapes, should be: " + numShapes + " but was: " + count);
            }
        } catch (InputMismatchException e) {
            fail("Formatting of txt file was incorrect. nm: " + nm + " x: " + x + " y: " + y + " wd: " + wd + " ht: "
                    + ht + " fill: " + fill + " rgb: " + rgb);
        }
        sc.close();
    }

//	@Test
//	public void drawCorrectWidth() {
//		PNGOutput png = createPNGOutput();
//		ShapePanel sp = new ShapePanel();
//		int testNum = 2;
//
//		// Adds to ShapePanel shapes array list.
//		sp.addShapeToDrawQueue("Triangle", testNum);
//		HashSet<Shape> shapes = sp.getAllShapes();
//
//		// Before drawing, set the abstract variables
//		ShapeAbstract.setPattern(DrawPattern.ALIGNED);
//		ShapeAbstract.setWidth(4);
//		sp.draw(new TestCanvas().getGraphics(), png.getPng().getGraphics());
//		if (shapes.size() != 1) {
//			fail("Size should be equal to 1");
//		}
//
//		Triangle t = null;
//		for (Shape s : shapes) {
//			t = (Triangle) s;
//		}
//		HashSet<int[]> vars = t.getXY();
//		int[] first = vars.get(0);
//		int[] second = vars.get(1);
//		if (first[0] != 0) {
//			fail();
//		}
//		if (second[0] != 4) {
//			fail();
//		}
//		png.outputToFile(sp, shapes, new Color(0, 0, 0), "test.txt");
//		Scanner sc = null;
//		try {
//			sc = new Scanner(new File("test.txt"));
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		}
//		// TODO: test the file output
//		while (sc.hasNext()) {
//			System.out.println(sc.next());
//		}
//	}

    private PNGOutput createPNGOutput() {
        return new PNGOutput(testCanvasSize);
    }

    private class TestCanvas extends JPanel {
        private static final long serialVersionUID = -1863297224513004580L;

        TestCanvas() {
            JFrame frame = new JFrame();
            frame.setBounds(testCanvasSize);
            frame.add(this);
            frame.setVisible(true);
        }
    }
}
