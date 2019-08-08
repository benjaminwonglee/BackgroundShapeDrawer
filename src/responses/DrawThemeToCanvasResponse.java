package responses;

import borders.TextBorder;
import panels.ShapePanel;
import shapes.Shape;

import javax.swing.*;
import java.awt.*;

public class DrawThemeToCanvasResponse implements ButtonResponse {

    @Override
    public void respond(ShapePanel sp) {

        sp.drawThemeToCanvas();

        // Update the text
        TextBorder t = (TextBorder) sp.getTextDisplay().getBorder();
        t.setText("Set background to black to avoid strain on eyes.");
        sp.getTextDisplay().repaint();
    }

}
