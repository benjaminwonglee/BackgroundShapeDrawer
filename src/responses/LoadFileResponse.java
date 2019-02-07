package responses;

import borders.TextBorder;
import output.PNGOutput;
import panels.ShapePanel;

import javax.swing.*;
import java.io.File;

public class LoadFileResponse implements ButtonResponse {

    /**
     * Responds to when the load from text file button is pressed. First clears the
     * canvas then draws the shapes from the text file onto the canvas.
     */
    @Override
    public void respond(ShapePanel sp) {
        JFileChooser chooser = new JFileChooser(System.getProperty("user.dir"));
        int option = chooser.showOpenDialog(new JDialog());
        if (option != JFileChooser.CANCEL_OPTION) {
            if (option == JFileChooser.APPROVE_OPTION) {
                File file = chooser.getSelectedFile();
                JTextArea textDisplay = sp.getTextDisplay();
                if (file == null || !chooser.getSelectedFile().getName().endsWith(".txt")) {
                    TextBorder t = (TextBorder) textDisplay.getBorder();
                    t.setText("Please select an appropriate .txt file to load from. Please try again.");
                    textDisplay.repaint();
                    return;
                }

                // Clears the canvas
                sp.getClearButton().doClick();
                PNGOutput.loadFromTextFile(sp, chooser.getSelectedFile().getName());
                TextBorder textDisplayWrapper = (TextBorder) textDisplay.getBorder();
                textDisplayWrapper.setText("Loaded successfully.");
                textDisplay.repaint();
            }
        }
    }
}
