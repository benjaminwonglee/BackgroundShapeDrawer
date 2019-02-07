package responses;

import borders.TextBorder;
import panels.ShapePanel;

import javax.swing.*;

public class ChangeShapeColorResponse implements ButtonResponse {

    /**
     * Responds to when the Shape Colour button is clicked. It updates the user
     * notification box appropriately, and puts focus on the user input text box
     * underneath. Sets booleans for userInputResponse.
     */
    @Override
    public void respond(ShapePanel sp) {
        sp.setToChangeShapeColor(true);
        JTextArea textDisplay = sp.getTextDisplay();
        TextBorder t = (TextBorder) textDisplay.getBorder();
        t.setText(
                "Changing outline color: Choose rgb color in the panel below: input 3 integers; " +
                        "each between 0 and 255 (space separated) for red, green, blue values. Click \"OK!\" when ready");
        sp.getUserInput().requestFocus();
        textDisplay.repaint();
    }
}
