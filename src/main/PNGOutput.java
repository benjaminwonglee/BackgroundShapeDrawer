package main;

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

public class PNGOutput {

	private BufferedImage png;
	private int[][] colorPixelArray;

	public BufferedImage getPng() {
		return png;
	}

	public PNGOutput(Rectangle canvasSize) {
		this.png = new BufferedImage((int) canvasSize.getWidth(), (int) canvasSize.getHeight(),
				BufferedImage.TYPE_INT_ARGB);
		this.colorPixelArray = new int[png.getWidth()][png.getHeight()];
	}

	public void outputToFile(int canvasRed, int canvasGreen, int canvasBlue) {
		PrintWriter pw = null;
		File f = null;
		try {
			f = new File("output.txt");
			pw = new PrintWriter(f);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		Color bgc = new Color(canvasRed, canvasGreen, canvasBlue);
		// Get the RGB value of background colour
		int bgColor = bgc.getRGB();

		pw.print("row " + "col " + "RGB\n");
		for (int row = 0; row < png.getWidth(); row++) {
			for (int col = 0; col < png.getHeight(); col++) {
				// // Ensure that the background colours stay in the background
				if (colorPixelArray[row][col]  != bgColor) {
					colorPixelArray[row][col] = png.getRGB(row, col);
				}
				pw.print(row + " " + col + " " + png.getRGB(row, col) + "\n");
			}
		}
	}
}
