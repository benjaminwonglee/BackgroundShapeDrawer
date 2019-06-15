package responses;

import borders.ColorBorder;
import borders.TextBorder;
import misc.ColorChooser;
import panels.ShapePanel;

import javax.swing.*;
import java.awt.*;

public class AutoBackgroundColorResponse implements ButtonResponse {

    private final ColorChooser backgroundColorChooser;

    public AutoBackgroundColorResponse() {
        backgroundColorChooser = new ColorChooser();
    }

    /**
     * Responds to when the auto background color button is pressed. Changes the
     * color of the background.
     */
    @Override
    public void respond(ShapePanel sp) {
        backgroundColorChooser.chooseColor();

        // Change the canvas background colour
        Color color = backgroundColorChooser.getColor();
        sp.changeBackgroundColor(color);

        // Set the colour of the display button
        JTextArea changeBackgroundColor = sp.getChangeBackgroundPanelWrapper();
        ColorBorder colorLabel = (ColorBorder) changeBackgroundColor.getBorder();
        colorLabel.setColor(color);
        changeBackgroundColor.repaint();

        // Update the user of the status via text box
        sp.writeToTextBoxAndRepaint(sp.getTextDisplay(), "Background colour changed successfully");
        sp.setThemeDrawn(false);
    }
}
