package output;

import panels.ShapePanel;
import shapes.Shape;
import themes.Theme;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Scanner;

import static util.Utils.determineShapeFromName;
import static util.Utils.retrieveThemeFromName;

public class PNGOutput {

    private BufferedImage png;

    /**
     * The constructor for a PNGOutput. Takes the canvasSize Rectangle as an
     * argument for the size of the new PNG file it will output.
     *
     * @param canvasSize The size of the expected PNGOutput
     */
    public PNGOutput(Rectangle canvasSize) {
        this.png = new BufferedImage((int) canvasSize.getWidth(), (int) canvasSize.getHeight() + 5,
                BufferedImage.TYPE_INT_ARGB);
    }

    /**
     * Outputs the current drawing to a txt format.
     *
     * @param sp        The ShapePanel of the program.
     * @param allShapes The ArrayList of shapes drawn.
     * @param filename  The name of the txt file
     */
    public static void outputToFile(ShapePanel sp, HashSet<Shape> allShapes, Color backgroundColor, String filename) {
        // Create file and PrintWriter.
        PrintWriter pw = null;
        try {
            pw = new PrintWriter(new File(filename));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        if (pw == null) {
            return;
        }

        // Get the RGB value of background colour
        int rgbBgc = backgroundColor.getRGB();
        pw.println(rgbBgc);
        if (sp.isThemeDrawn()) {
            pw.println(sp.getThemeName());
        } else {
            pw.println("none");
        }

        // s.getXY() returns: [x, y, width, height, fill, rgbColor]
        for (Shape s : allShapes) {
            for (int[] vars : s.getXY()) {

                pw.print(s.name() + " ");
                for (int variable : vars) {
                    pw.print(variable + " ");
                }
                pw.println();
            }
        }
        pw.close();
    }

    /**
     * Makes a png file from a given txt file and assigns the png file newImageName.
     *
     * @param filename     The file to read from that is created by this program.
     * @param newImageName The name of the new png file
     */
    public void pngFromFile(String filename, String newImageName) {

        Scanner sc = createScannerOverFile(filename);
        if (sc == null) {
            return;
        }

        Color bgc = new Color(sc.nextInt());
        // Skip over the next line character
        sc.nextLine();
        String theme = sc.nextLine();

        if (theme.equals("none")) {
            Graphics2D g2d = png.createGraphics();
            g2d.setPaint(bgc);
            g2d.fillRect(0, 0, png.getWidth(), png.getHeight());
        } else {
            Theme th = retrieveThemeFromName(theme);
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
            Shape s = determineShapeFromName(nm);
            s.drawFromXY(png.getGraphics(), new Color(rgb), x, y, wd, ht, fill);
        }
        try {
            ImageIO.write(png, "PNG", new File(newImageName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        sc.close();
    }

    /**
     * Applies an image to the canvas from a given txt file
     *
     * @param sp       The ShapePanel containing the canvas
     * @param filename The file to read from that is created by this program.
     */
    public static void loadFromTextFile(ShapePanel sp, String filename) {

        Scanner sc = createScannerOverFile(filename);
        if (sc == null) {
            return;
        }

        Color bgc = new Color(sc.nextInt());
        // Skip over the next line character
        sc.nextLine();
        String themeName = sc.nextLine();

        if (themeName.equals("none")) {
            Graphics2D g2d = (Graphics2D) sp.getCanvas().getGraphics();
            g2d.setPaint(bgc);
            g2d.fillRect(0, 0, sp.getWidth(), sp.getHeight());
        } else {
            Theme th = retrieveThemeFromName(themeName);
            sp.setThemeFromName(themeName);
            sp.setOpaque(false);
            sp.getCanvas().setOpaque(true);
            sp.repaint();
            th.setTheme(sp.getCanvas().getGraphics(), sp);
        }

        HashSet<Shape> allShapes = new HashSet<>();

        while (sc.hasNext()) {
            // s.getXY() returns: [x, y, width, height, fill, rgbColor]
            String shapeName = sc.next();
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
            Shape s = determineShapeFromName(shapeName);
            s.drawFromXY(sp.getCanvas().getGraphics(), new Color(rgb), x, y, wd, ht, fill);
            allShapes.add(s);
        }
        sp.setAllShapes(allShapes);
        sc.close();
    }

    private static Scanner createScannerOverFile(String filename) {
        Scanner sc = null;
        try {
            sc = new Scanner(new File(filename));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        if (sc == null) {
            return null;
        }
        return sc;
    }

    public BufferedImage getPng() {
        return png;
    }
}
