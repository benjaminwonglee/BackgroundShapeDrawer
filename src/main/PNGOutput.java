package main;

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import javax.imageio.ImageIO;

public class PNGOutput {

	private BufferedImage png;
	private int[][] colorPixelArray;
	private int rgbBgc = 0;

	public PNGOutput(Rectangle canvasSize) {
		this.png = new BufferedImage((int) canvasSize.getWidth(), (int) canvasSize.getHeight(),
				BufferedImage.TYPE_INT_ARGB);
		this.colorPixelArray = new int[png.getWidth()][png.getHeight()];
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

		for (int row = 0; row < png.getWidth(); row++) {
			for (int col = 0; col < png.getHeight(); col++) {
				// Ensure that the background colours stay in the background
				if (colorPixelArray[row][col] != rgbBgc) {
					colorPixelArray[row][col] = png.getRGB(row, col);
				}
				pw.print(png.getRGB(row, col) + " ");
			}
		}
	}

	public void pngFromFile(String filename, String newImageName) {
		Scanner sc = null;
		try {
			sc = new Scanner(new File(filename));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		BufferedImage output = png;
		for (int row = 0; row < output.getWidth(); row++) {
			for (int col = 0; col < output.getHeight(); col++) {
				if (sc.hasNext()) {
					int nextrgb = sc.nextInt();
					if (nextrgb == 0) {
						nextrgb = rgbBgc;
					}
					output.setRGB(row, col, nextrgb);
				} else {
					try {
						ImageIO.write(output, "PNG", new File(newImageName));
					} catch (IOException e) {
						e.printStackTrace();
					}
					sc.close();
					return;
				}
			}
		}
	}

	public BufferedImage getPng() {
		return png;
	}

	public int[][] getColorPixelArray() {
		return colorPixelArray;
	}
}
