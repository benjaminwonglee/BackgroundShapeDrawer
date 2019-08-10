package responses;

import borders.ActivateBorder;
import panels.ShapePanel;
import shapes.Shape;
import util.Utils;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class DrawShapesResponse implements ButtonResponse {

    /**
     * Responds to when the Set Width & height button is clicked. It updates the
     * user notification box appropriately, and puts focus on the user input text
     * box underneath. Sets booleans for userInputResponse.
     */
    @Override
    public void respond(ShapePanel sp) {

        sp.setToDrawShapes(true);

        // Add activated shapes
        List<Shape> shapesToDraw = new ArrayList<>();

        List<JButton> buttonList = sp.getShapeButtonList();
        for (JButton button : buttonList) {
            ActivateBorder border = (ActivateBorder) button.getBorder();
            if (border.getActivated()) {
                String shapeName = border.getLabel();
                shapesToDraw.add(Utils.determineShapeNameFromString(shapeName));
            }
        }
        JTextField userInput = sp.getUserInput();
        userInput.setText("");
        userInput.update(userInput.getGraphics());
        sp.setShapesToDraw(shapesToDraw);
        sp.userInputResponse();
    }
}
