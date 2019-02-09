package buttons;

import output.PNGOutput;
import panels.ShapePanel;
import responses.ButtonResponse;

public class ChangeBackgroundButton extends OptionButton {
    public ChangeBackgroundButton(ShapePanel sp, PNGOutput png, ButtonResponse response) {
        super(sp, png, response);
    }

    @Override
    public String getButtonName() {
        return "Change Background";
    }
}
