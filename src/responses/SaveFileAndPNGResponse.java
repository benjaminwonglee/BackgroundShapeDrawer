package responses;

import output.PNGOutput;
import panels.ShapePanel;

import javax.swing.*;

public class SaveFileAndPNGResponse implements ButtonResponse {

    @Override
    public void respond(ShapePanel sp) {
        JFileChooser chooser = new JFileChooser(System.getProperty("user.dir"));
        int option = chooser.showSaveDialog(new JDialog());
        if (option == JFileChooser.APPROVE_OPTION) {
            String fileName = chooser.getSelectedFile().getName();
            // Adjust the file name
            if (fileName.endsWith(".png")) {
                fileName = fileName.replace(".png", "");
            }
            if (fileName.endsWith(".txt")) {
                fileName = fileName.replace(".txt", "");
            }
            // Save a file to the path
            PNGOutput.outputToFile(sp, sp.getBackgroundColor(), fileName + ".txt");
            sp.getPng().pngFromFile(sp, fileName + ".txt", fileName + ".png");
        }
    }
}
