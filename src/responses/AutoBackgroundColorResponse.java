package responses;

import borders.ColorBorder;
import borders.TextBorder;
import misc.ColorChooser;
import panels.ShapePanel;

import javax.swing.*;
import java.awt.*;

public class AutoBackgroundColorResponse implements ButtonResponse {

    ColorChooser backgroundColorChooser;

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
        sp.setCanvasRed(color.getRed());
        sp.setCanvasGreen(color.getGreen());
        sp.setCanvasBlue(color.getBlue());
        sp.getCanvas().setBackground(color);
        JTextArea changeBackgroundColor = sp.getChangeBackgroundColor();
        ColorBorder colorLabel = (ColorBorder) changeBackgroundColor.getBorder();
        colorLabel.setColor(color);
        changeBackgroundColor.repaint();
        JTextArea textDisplay = sp.getTextDisplay();
        TextBorder t = (TextBorder) textDisplay.getBorder();
        t.setText("Background colour changed successfully");
        textDisplay.repaint();
        sp.setThemeDrawn(false);
    }
}
