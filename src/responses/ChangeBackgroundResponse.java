package responses;

import panels.ShapePanel;
import util.Utils;

public class ChangeBackgroundResponse implements ButtonResponse {

    /**
     * This method responds to when the Change Background Button is clicked.
     */
    @Override
    public void respond(ShapePanel sp) {
        sp.changeBackgroundColor(Utils.openJColorChooser(sp.getBackgroundColor()));
    }
}
