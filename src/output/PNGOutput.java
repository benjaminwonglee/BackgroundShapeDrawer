package output;

import misc.FillStatus;
import panels.ShapePanel;
import shapes.Shape;
import shapes.ShapeMetadata;
import themes.Theme;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static util.Utils.determineShapeFromName;
import static util.Utils.retrieveThemeFromName;

public class PNGOutput {

    private final BufferedImage png;

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
     * Outputs the current drawing to a .txt file.
     *
     * @param sp       The ShapePanel of the program.
     * @param filename The name of the txt file
     */
    public static void outputToFile(ShapePanel sp, Color backgroundColor, String filename) {
        // Create file and PrintWriter.
        PrintWriter pw = null;
        try {
            pw = new PrintWriter(new File(filename));
        } catch (FileNotFoundException e) {
            sp.writeToTextBoxAndRepaint("Could not save image: An output text file could not be written or created.");
        }
        if (pw == null) {
            return;
        }

        // Get the RGB value of background colour
        int rgbBgc = backgroundColor.getRGB();
        pw.println(rgbBgc);
        if (sp.isThemeDrawn()) {
            pw.println(sp.getTheme().getName());
        } else {
            pw.println("none");
        }

        for (Shape s : sp.getAllShapes()) {
            List<ShapeMetadata> shapeMetadata = s.getXY();
            for (ShapeMetadata metadata : shapeMetadata) {
                pw.print(s.name() + ",");
                pw.print(metadata.getX() + ",");
                pw.print(metadata.getY() + ",");
                pw.print(metadata.getWidth() + ",");
                pw.print(metadata.getHeight() + ",");
                pw.print(metadata.getFillStatus() + ",");

                // print the final variable without a comma
                pw.println(metadata.getRgb());
            }
        }
        pw.close();
    }

    /**
     * Makes a png file from a given txt file and assigns the png file newImageName.
     *
     * @param sp           The ShapePanel that this is being called from
     * @param filename     The file to read from that is created by this program.
     * @param newImageName The name of the new png file
     */
    public void pngFromFile(ShapePanel sp, String filename, String newImageName) {

        boolean successfullyParsed = parseImageInfoTextFile(sp, filename, true);
        if (!successfullyParsed) {
            return;
        }
        try {
            ImageIO.write(png, "PNG", new File(newImageName));
        } catch (IOException e) {
            sp.writeToTextBoxAndRepaint("An error occurred during creating a png from text file. " +
                    "Check that the file is in the correct format.");
        }
    }

    public void loadFromTextFile(ShapePanel sp, String filename) {
        if (sp.getAllShapes() == null) {
            sp.setAllShapes(new ArrayList<>());
        }
        parseImageInfoTextFile(sp, filename, false);
    }

    /**
     * Parse the text file to load a previously saved output
     *
     * @param sp       The shape panel object to display status information
     * @param filename The file to parse from
     * @param toImage  Whether the image is being loaded or being saved to an image file
     * @return
     */
    private boolean parseImageInfoTextFile(ShapePanel sp, String filename, boolean toImage) {
        Scanner sc;
        try {
            sc = createScannerOverFile(filename);
        } catch (FileNotFoundException e) {
            sp.writeToTextBoxAndRepaint("There was an reading the text file at " + filename + ".");
            return false;
        }

        Color backgroundColor = new Color(sc.nextInt());

        // Skip over the next line character
        sc.nextLine();
        String themeName = sc.nextLine();
        if (toImage) {
            applyThemeToBufferedImage(backgroundColor, themeName);
        } else {
            applyThemeToShapePanelCanvas(sp, backgroundColor, themeName);
        }

        // Retrieve the data from the file
        while (sc.hasNext()) {
            // File is in the form: [x, y, width, height, fill, rgbColour]
            String line = sc.nextLine();
            String[] vars = line.split(",");
            String shapeName;
            int x;
            int y;
            int wd;
            int ht;
            int fillInt;
            int rgb;
            try {
                shapeName = vars[0];
                x = Integer.parseInt(vars[1]);
                y = Integer.parseInt(vars[2]);
                wd = Integer.parseInt(vars[3]);
                ht = Integer.parseInt(vars[4]);
                fillInt = Integer.parseInt(vars[5]);
                rgb = Integer.parseInt(vars[6]);
            } catch (IndexOutOfBoundsException | NumberFormatException ex) {
                sp.writeToTextBoxAndRepaint("Error parsing image info file");
                return false;
            }

            // Process and draw read variables
            // TODO: Feature: Add an option to be able to draw in random order so the shape colours are mixed up
            Shape shape = determineShapeFromName(shapeName);
            if (!toImage) {
                shape.drawFromXY(sp.getCanvas().getGraphics(), new Color(rgb), x, y, wd, ht, FillStatus.values()[fillInt]);

                // Add the metadata to the shape object it belongs to for continuation
                ShapeMetadata metadata = new ShapeMetadata();
                metadata.setX(x);
                metadata.setY(y);
                metadata.setWidth(wd);
                metadata.setHeight(ht);
                metadata.setFillStatus(fillInt);
                metadata.setRgb(rgb);
                shape.getXY().add(metadata);
            } else {
                shape.drawFromXY(png.getGraphics(), new Color(rgb), x, y, wd, ht, FillStatus.values()[fillInt]);
            }
        }
        sc.close();
        return true;
    }

    private void applyThemeToBufferedImage(Color backgroundColor, String themeName) {
        if (themeName.equals("none")) {
            // Set background to the background colour
            Graphics2D g2d = png.createGraphics();
            g2d.setPaint(backgroundColor);
            g2d.fillRect(0, 0, png.getWidth(), png.getHeight());
        } else {
            // Apply a theme if it exists
            Theme th = retrieveThemeFromName(themeName);
            JPanel pngSize = new JPanel();
            pngSize.setBounds(0, 0, png.getWidth(), png.getHeight());
            th.applyTheme(png.getGraphics(), pngSize);
        }
    }

    private void applyThemeToShapePanelCanvas(ShapePanel sp, Color backgroundColor, String themeName) {
        if (themeName.equals("none")) {
            Graphics2D g2d = (Graphics2D) sp.getCanvas().getGraphics();
            g2d.setPaint(backgroundColor);
            g2d.fillRect(0, 0, sp.getWidth(), sp.getHeight());
        } else {
            Theme th = retrieveThemeFromName(themeName);
            sp.setThemeFromName(themeName);
            sp.setOpaque(false);
            sp.getCanvas().setOpaque(true);
            sp.repaint();
            th.applyTheme(sp.getCanvas().getGraphics(), sp);
        }
    }

    private static Scanner createScannerOverFile(String filename) throws FileNotFoundException {
        return new Scanner(new File(filename));
    }

    public BufferedImage getPng() {
        return png;
    }
}
