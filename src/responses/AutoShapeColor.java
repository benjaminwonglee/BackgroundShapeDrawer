package responses;

import borders.ColorBorder;
import borders.TextBorder;
import misc.ColorChooser;
import panels.ShapePanel;

import javax.swing.*;
import java.awt.*;

public class AutoShapeColor implements ButtonResponse {

    final ColorChooser shapeColorChooser;

    public AutoShapeColor() {
        shapeColorChooser = new ColorChooser();
    }

    /**
     * Responds to when the auto shape color button is pressed. Changes the color of
     * the shape outline.
     */
    @Override
    public void respond(ShapePanel sp) {
        shapeColorChooser.chooseColor();
        Color color = shapeColorChooser.getColor();
        sp.setOutlineColor(color);

        JTextArea changeOutlineColour = sp.getChangeOutlineColor();
        ColorBorder colorLabel = (ColorBorder) changeOutlineColour.getBorder();
        colorLabel.setColor(color);
        changeOutlineColour.repaint();

        TextBorder t = (TextBorder) sp.getTextDisplay().getBorder();
        t.setText("Outline colour changed successfully");
        sp.getTextDisplay().repaint();
    }
}
