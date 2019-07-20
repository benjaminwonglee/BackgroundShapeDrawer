package responses;

import panels.ShapePanel;
import util.ColouringUtils;

public class ChangeBackgroundResponse implements ButtonResponse {

    /**
     * This method responds to when the Change Background Button is clicked.
     */
    @Override
    public void respond(ShapePanel sp) {
        sp.changeBackgroundColor(ColouringUtils.openJColorChooser(sp.getBackgroundColor()));
    }
}
