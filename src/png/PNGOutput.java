package png;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

import javax.imageio.ImageIO;

public class PNGOutput {

	private BufferedImage png;
	private int rgbBgc = 0;

	public PNGOutput(Rectangle canvasSize) {
		this.png = new BufferedImage((int) canvasSize.getWidth(), (int) canvasSize.getHeight(),
				BufferedImage.TYPE_INT_ARGB);
	}

	public void outputToFile(String filename, int canvasRed, int canvasGreen, int canvasBlue) {
		// Create file and PrintWriter.
		PrintWriter pw = null;
		File f = null;
		try {
			f = new File(filename);
			pw = new PrintWriter(f);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		// Get the RGB value of background colour
		Color bgc = new Color(canvasRed, canvasGreen, canvasBlue);
		rgbBgc = bgc.getRGB();
		// for (int row = 0; row < png.getWidth(); row++) {
		// for (int col = 0; col < png.getHeight(); col++) {
		// // Ensure that the background colours stay in the background
		// pw.print(png.getRGB(row, col) + "," + row + "," + col + " ");
		// }
		// }
	}

	public void pngFromFile(String filename, String newImageName) {
		// Scanner sc = null;
		// try {
		// sc = new Scanner(new File(filename));
		// } catch (FileNotFoundException e) {
		// e.printStackTrace();
		// }
		// BufferedImage output = png;
		// for (int row = 0; row < output.getWidth(); row++) {
		// for (int col = 0; col < output.getHeight(); col++) {
		// if (sc.hasNext()) {
		// String next = sc.next();
		// String[] vals = next.split(",");
		// if (vals.length < 3) {
		// break;
		// }
		// output.setRGB(Integer.parseInt(vals[1]), Integer.parseInt(vals[2]),
		// Integer.parseInt(vals[0]));
		// }
		// }
		// }
		// try {
		// ImageIO.write(output, "PNG", new File(newImageName));
		// } catch (IOException e) {
		// e.printStackTrace();
		// }
		// sc.close();
		// return;

	}

	public BufferedImage getPng() {
		return png;
	}
}
