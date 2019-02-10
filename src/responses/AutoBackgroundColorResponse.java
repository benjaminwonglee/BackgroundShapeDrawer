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
        Color color = backgroundColorChooser.getColor();
        sp.setCanvasRedRGB(color.getRed());
        sp.setCanvasGreenRGB(color.getGreen());
        sp.setCanvasBlueRGB(color.getBlue());
        sp.getCanvas().setBackground(color);
        JTextArea changeBackgroundColor = sp.getChangeBackgroundPanelWrapper();
        ColorBorder colorLabel = (ColorBorder) changeBackgroundColor.getBorder();
        colorLabel.setColor(color);
        changeBackgroundColor.repaint();
        sp.writeToTextBoxAndRepaint(sp.getTextDisplay(), "Background colour changed successfully");
        sp.setThemeDrawn(false);
    }
}
