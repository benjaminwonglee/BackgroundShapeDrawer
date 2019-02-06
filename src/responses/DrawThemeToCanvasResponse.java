package responses;

import borders.TextBorder;
import panels.ShapePanel;
import shapes.Shape;

import javax.swing.*;
import java.awt.*;

public class DrawThemeToCanvasResponse implements ButtonResponse {

    @Override
    public void respond(ShapePanel sp) {

        JPanel canvas = sp.getCanvas();

        // Set the background of the ShapePanel to black
        Graphics2D g2d = (Graphics2D) sp.getGraphics().create();
        g2d.setPaint(new Color(0, 0, 0));
        g2d.fillRect(0, 0, sp.getBounds().width, sp.getBounds().height);
        sp.setAllShapes(null);
        Shape.clearAllShapes();

        // Redraw all the buttons
        for (Component c : sp.getComponents()) {
            if (!c.equals(canvas)) {
                c.repaint();
            }
        }

        // Draw theme to canvas
        sp.getTheme().setTheme(canvas.getGraphics(), canvas);
        canvas.getGraphics().drawRect(0, 0, canvas.getBounds().width - 1, canvas.getBounds().height - 1);
        sp.setTheme(sp.getPng().getPng().getGraphics());
        sp.setThemeDrawn(true);

        // Update the text
        TextBorder t = (TextBorder) sp.getTextDisplay().getBorder();
        t.setText("Set background to black to avoid strain on eyes.");
        sp.getTextDisplay().repaint();
    }

}
