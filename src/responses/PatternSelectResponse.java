package responses;

import borders.TextBorder;
import panels.ShapePanel;
import patterns.DrawPattern;
import shapes.ShapeAbstract;

import javax.swing.*;

public class PatternSelectResponse implements ButtonResponse {

    /**
     * Responds to when the select pattern button is pressed. Changes the mode for pattern drawing.
     */
    @Override
    public void respond(ShapePanel sp) {
        JTextArea selectedPattern = sp.getPatternTextArea();
        TextBorder border = (TextBorder) selectedPattern.getBorder();
        String selectedItem = border.getText();
        DrawPattern drawPattern = DrawPattern.parseRelaxed(selectedItem);
        DrawPattern nextPattern = DrawPattern.findNext(drawPattern);
        if (nextPattern != null) {
            ShapeAbstract.setPattern(nextPattern);
            border.setText(nextPattern.getPatternName());
            selectedPattern.repaint();
        } else {
            sp.writeToTextBoxAndRepaint("Error: Selected pattern could not be established.");
        }
    }
}
