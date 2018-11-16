package output;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import main.ShapePanel;
import shapes.Circle;
import shapes.Ellipse;
import shapes.Hexagon;
import shapes.Lightning;
import shapes.Octagon;
import shapes.Shape;
import shapes.Square;
import shapes.Star5;
import shapes.Star6;
import shapes.Triangle;
import themes.BlueLightning;
import themes.GoldPurpleStars;
import themes.GradientBlueRed;
import themes.GradientRedBlue;
import themes.MetalTheme;
import themes.RandomDot;
import themes.SemiRandomDot;
import themes.Theme;
import themes.TrafficLightTheme;
import themes.YellowDiamonds;

public class PNGOutput {

	private BufferedImage png;

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
	 * @param sp
	 *            The ShapePanel of the program.
	 * @param allShapes
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
	public void outputToFile(ShapePanel sp, HashSet<Shape> allShapes, Color backgroundColor, String filename) {
		// Create file and PrintWriter.
		PrintWriter pw = null;
		try {
			pw = new PrintWriter(new File(filename));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		// Get the RGB value of background colour
		Color bgc = backgroundColor;
		int rgbBgc = bgc.getRGB();
		pw.println(rgbBgc);
		if (sp.isThemeDrawn()) {
			pw.println(sp.getTheme());
		} else {
			pw.println("none");
		}

		// s.getXY() returns: [x, y, width, height, fill, rgbColor]
		for (Shape s : allShapes) {
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
	 * Makes a png file from a given txt file and assigns the png file newImageName.
	 *
	 * @param filename
	 *            The file to read from that is created by this program.
	 * @param newImageName
	 *            The name of the new png file
	 * @throws FileNotFoundException
	 */
	public void pngFromFile(String filename, String newImageName) throws FileNotFoundException {

		Scanner sc = null;
		sc = new Scanner(new File(filename));
		Color bgc = new Color(sc.nextInt());
		// Skip over the next line character
		sc.nextLine();
		String theme = sc.nextLine();

		if (theme.equals("none")) {
			Graphics2D g2d = png.createGraphics();
			g2d.setPaint(bgc);
			g2d.fillRect(0, 0, png.getWidth(), png.getHeight());
		} else {
			Theme th = setTheme(theme);
			JPanel pngSize = new JPanel();
			pngSize.setBounds(0, 0, png.getWidth(), png.getHeight());
			th.setTheme(png.getGraphics(), pngSize);
		}
		while (sc.hasNext()) {
			// s.getXY() returns: [x, y, width, height, fill, rgbColor]
			String nm = sc.next();
			int x = sc.nextInt();
			int y = sc.nextInt();
			int wd = sc.nextInt();
			int ht = sc.nextInt();
			int fillInt = sc.nextInt();
			boolean fill = false;
			if (fillInt == 1) {
				fill = true;
			}
			int rgb = sc.nextInt();
			Shape s = determineShape(nm);
			s.drawFromXY(png.getGraphics(), new Color(rgb), x, y, wd, ht, fill);
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
	 * Applies an image to the canvas from a given txt file
	 *
	 * @param sp
	 *            The ShapePanel containing the canvas
	 * @param filename
	 *            The file to read from that is created by this program.
	 * @throws FileNotFoundException
	 */
	public void loadFromTextFile(ShapePanel sp, String filename) throws FileNotFoundException {

		Scanner sc = null;
		sc = new Scanner(new File(filename));
		Color bgc = new Color(sc.nextInt());
		// Skip over the next line character
		sc.nextLine();
		String theme = sc.nextLine();

		if (theme.equals("none")) {
			Graphics2D g2d = (Graphics2D) sp.getCanvas().getGraphics();
			g2d.setPaint(bgc);
			g2d.fillRect(0, 0, sp.getWidth(), sp.getHeight());
		} else {
			Theme th = setTheme(theme);
			th.setTheme(sp.getCanvas().getGraphics(), sp);
		}
		while (sc.hasNext()) {
			// s.getXY() returns: [x, y, width, height, fill, rgbColor]
			String nm = sc.next();
			int x = sc.nextInt();
			int y = sc.nextInt();
			int wd = sc.nextInt();
			int ht = sc.nextInt();
			int fillInt = sc.nextInt();
			boolean fill = false;
			if (fillInt == 1) {
				fill = true;
			}
			int rgb = sc.nextInt();
			Shape s = determineShape(nm);
			s.drawFromXY(sp.getCanvas().getGraphics(), new Color(rgb), x, y, wd, ht, fill);
			sp.getShapes().add(s);
			sp.setTheme(theme);
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

	private Theme setTheme(String theme) {
		Theme t = null;
		switch (theme) {
		case ("blue lightning"):
			t = new BlueLightning();
			break;
		case ("gold purple stars"):
			t = new GoldPurpleStars();
			break;
		case ("gradient red blue"):
			t = new GradientRedBlue();
			break;
		case ("gradient blue red"):
			t = new GradientBlueRed();
			break;
		case ("metal theme"):
			t = new MetalTheme();
			break;
		case ("random dot"):
			t = new RandomDot();
			break;
		case ("semi random dot"):
			t = new SemiRandomDot();
			break;
		case ("traffic light theme"):
			t = new TrafficLightTheme();
			break;
		case ("yellow diamonds"):
			t = new YellowDiamonds();
			break;
		default:
			t = new RandomDot();
			break;
		}
		return t;
	}

	public BufferedImage getPng() {
		return png;
	}
}
