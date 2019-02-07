package responses;

import borders.TextBorder;
import panels.ShapePanel;

import javax.swing.*;

public class ChangeBackgroundResponse implements ButtonResponse {

    /**
     * This method responds to when the Change Background Button is clicked. It
     * updates the user notification box appropriately, and puts focus on the user
     * input text box underneath. Sets booleans for userInputResponse.
     */
    @Override
    public void respond(ShapePanel sp) {
        sp.setToChangeBackground(true);
        JTextArea textDisplay = sp.getTextDisplay();
        TextBorder t = (TextBorder) textDisplay.getBorder();
        t.setText(
                "Changing background color: Choose rgb color in the panel below: input 3 integers; each between 0 and 255 " +
                        "(space separated) for red, green, blue values. Click \"OK!\" when ready");
        sp.getUserInput().requestFocus();
        textDisplay.repaint();
    }
}
