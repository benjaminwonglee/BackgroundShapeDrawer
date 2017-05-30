package output;

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

import javax.imageio.ImageIO;

import main.ShapePanel;
import shapes.*;

public class PNGOutput {

	private BufferedImage png;
	private int rgbBgc = 0;

	/**
	 * The constructor for a PNGOutput. Takes the canvasSize Rectangle as an
	 * argument for the size of the new PNG file it will output.
	 *
	 * @param canvasSize
	 */
	public PNGOutput(Rectangle canvasSize) {
		this.png = new BufferedImage((int) canvasSize.getWidth(), (int) canvasSize.getHeight() + 5,
				BufferedImage.TYPE_INT_ARGB);
	}

	/**
	 * Outputs the current drawing to a txt format.
	 *
	 * @param shapes
	 *            The ArrayList of shapes drawn.
	 * @param canvasRed
	 *            Red integer for the canvas background colour
	 * @param canvasGreen
	 *            Green integer for the canvas background colour
	 * @param canvasBlue
	 *            Blue integer for the canvas background colour
	 * @param filename
	 *            The name of the txt file
	 */
	public void outputToFile(ArrayList<Shape> shapes, int canvasRed, int canvasGreen, int canvasBlue, String filename) {
		// Create file and PrintWriter.
		PrintWriter pw = null;
		try {
			pw = new PrintWriter(new File(filename));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		// Get the RGB value of background colour
		Color bgc = new Color(canvasRed, canvasGreen, canvasBlue);
		rgbBgc = bgc.getRGB();

		// s.getXY() returns: [x, y, width, height, fill]
		for (Shape s : shapes) {
			for (int[] vars : s.getXY()) {
				pw.print(s.name() + " ");
				for (int i = 0; i < vars.length; i++) {
					pw.print(vars[i] + " ");
				}
				pw.println();
			}
		}
		pw.close();
	}

	/**
	 * Makes a png file from from a given txt file and assigns the png file
	 * newImageName.
	 *
	 * @param sp
	 *            The current ShapePanel
	 * @param filename
	 *            The file to read from that is created by this program.
	 * @param newImageName
	 *            The name of the new png file
	 * @throws FileNotFoundException
	 */
	public void pngFromFile(ShapePanel sp, String filename, String newImageName) throws FileNotFoundException {
		Scanner sc = null;
		sc = new Scanner(new File(filename));
		sp.setBackgroundColor(rgbBgc);
		while (sc.hasNext()) {
			String nm = sc.next();
			int x = sc.nextInt();
			int y = sc.nextInt();
			int wd = sc.nextInt();
			int ht = sc.nextInt();
			int fill = sc.nextInt();
			int rgb = sc.nextInt();
			Shape s = determineShape(nm);
			if (fill == 1) {
				ShapeAbstract.setFill(true);
			} else {
				ShapeAbstract.setFill(false);
			}
			s.drawFromXY(sp.getCanvas().getGraphics(), new Color(rgb), x, y, wd, ht);
			s.drawFromXY(png.getGraphics(), new Color(rgb), x, y, wd, ht);
		}
		try {
			ImageIO.write(png, "PNG", new File(newImageName));
		} catch (IOException e) {
			e.printStackTrace();
		}
		sc.close();
		return;
	}

	/**
	 * Helper method for pngFromFile method. Returns a shape from a name.
	 *
	 * @param name
	 * @return
	 */
	public static Shape determineShape(String name) {
		switch (name.toLowerCase()) {
		case ("circle"):
			return new Circle();
		case ("ellipse"):
			return new Ellipse();
		case ("hexagon"):
			return new Hexagon();
		case ("lightning"):
			return new Lightning();
		case ("octagon"):
			return new Octagon();
		case ("rectangle"):
			return new shapes.Rectangle();
		case ("square"):
			return new Square();
		case ("5-pointed_star"):
			return new Star5();
		case ("6-pointed_star"):
			return new Star6();
		case ("triangle"):
			return new Triangle();
		default:
			throw new NoSuchElementException();
		}
	}

	public BufferedImage getPng() {
		return png;
	}
}
