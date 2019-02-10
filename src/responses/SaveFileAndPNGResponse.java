package responses;

import output.PNGOutput;
import panels.ShapePanel;

import javax.swing.*;
import java.awt.*;

public class SaveFileAndPNGResponse implements ButtonResponse {

    @Override
    public void respond(ShapePanel sp) {
        JFileChooser chooser = new JFileChooser(System.getProperty("user.dir"));
        int option = chooser.showSaveDialog(new JDialog());
        if (option == JFileChooser.APPROVE_OPTION) {
            String fileName = chooser.getSelectedFile().getName();
            // Save a file to the path
            PNGOutput.outputToFile(
                    sp, new Color(sp.getCanvasRedRGB(), sp.getCanvasGreenRGB(), sp.getCanvasBlueRGB()), fileName + ".txt"
            );
            sp.getPng().pngFromFile(sp,fileName + ".txt", fileName + ".png");
        }
    }
}
