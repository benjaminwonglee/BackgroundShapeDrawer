package main;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class PNGOutput {

	public PNGOutput(JPanel canvas, Rectangle canvasSize) {
		BufferedImage png = new BufferedImage((int) canvasSize.getWidth(), (int) canvasSize.getHeight(),
				BufferedImage.TYPE_INT_ARGB);
		canvas.paint(png.getGraphics());
		try {
			ImageIO.write(png, "PNG", new File("output.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
