package output;

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
import javax.swing.JPanel;

import main.ShapePanel;
import shapes.Circle;

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

		for (int[] vars : Circle.xy) {
			for (int i = 0; i < vars.length - 1; i++) {
				System.out.println(vars.length);
				pw.print(vars[i] + " ");
			}
			pw.println();
		}
	}

	public void pngFromFile(ShapePanel sp, String filename, String newImageName) {
		Scanner sc = null;
		try {
			sc = new Scanner(new File(filename));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		//TODO: Complete this method
		JPanel canvas = sp.getCanvas();
		Graphics g = canvas.getGraphics();
		while(sc.hasNext()){
			int x = sc.nextInt();
			int y = sc.nextInt();
			int wd = sc.nextInt();
			int ht = sc.nextInt();
			int fill = sc.nextInt();
			
		}
		try {
			ImageIO.write(png, "PNG", new File(newImageName));
		} catch (IOException e) {
			e.printStackTrace();
		}
		sc.close();
		return;

	}

	public BufferedImage getPng() {
		return png;
	}
}
