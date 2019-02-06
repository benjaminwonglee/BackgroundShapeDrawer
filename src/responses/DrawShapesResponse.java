package responses;

import borders.ActivateBorder;
import panels.ShapePanel;

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

        sp.setDrawShapes(true);

        // Add activated shapes
        List<String> shapesToDraw = new ArrayList<>();

        List<JButton> buttonList = sp.getButtonList();
        for (int i = 0; i < buttonList.size(); i++) {
            ActivateBorder border = (ActivateBorder) buttonList.get(i).getBorder();
            if (border.getActivated()) {
                String name = border.getLabel();
                shapesToDraw.add(name);
            }
        }
        JTextField userInput = sp.getUserInput();
        userInput.setText("");
        userInput.update(userInput.getGraphics());
        sp.userInputResponse();
        sp.setShapesToDraw(shapesToDraw);
    }
}
