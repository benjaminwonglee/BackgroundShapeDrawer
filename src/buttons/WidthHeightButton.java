package buttons;

import output.PNGOutput;
import panels.ShapePanel;
import responses.ButtonResponse;

public class WidthHeightButton extends OptionButton {
    public WidthHeightButton(ShapePanel sp, PNGOutput png, ButtonResponse response) {
        super(sp, png, response);
    }

    @Override
    public String getButtonName() {
        return "Set Width & Height";
    }
}
