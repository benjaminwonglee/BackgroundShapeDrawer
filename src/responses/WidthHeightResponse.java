package responses;

import borders.TextBorder;
import panels.ShapePanel;

import javax.swing.*;

public class WidthHeightResponse implements ButtonResponse {

    /**
     * Responds to when the Set Width & height button is clicked. It updates the
     * user notification box appropriately, and puts focus on the user input text
     * box underneath. Sets booleans for userInputResponse.
     */
    @Override
    public void respond(ShapePanel sp) {
        sp.setToSetWidthHeight(true);
        sp.setToChangeWidth(true);
        sp.setToChangeHeight(true);
        JTextArea textDisplay = sp.getTextDisplay();
        TextBorder t = (TextBorder) textDisplay.getBorder();
        t.setText("Choose width: (enter an integer between 0 to 400)");
        sp.getUserInput().requestFocus();
        textDisplay.repaint();
    }
}
