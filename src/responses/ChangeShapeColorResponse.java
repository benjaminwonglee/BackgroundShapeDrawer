package responses;

import panels.ShapePanel;
import util.Utils;

public class ChangeShapeColorResponse implements ButtonResponse {

    /**
     * Responds to when the Shape Colour button is clicked.
     */
    @Override
    public void respond(ShapePanel sp) {
        sp.shapeOutlineColorChange(Utils.openJColorChooser(sp.getOutlineColor()));
    }
}
