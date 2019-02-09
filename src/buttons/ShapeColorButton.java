package buttons;

import output.PNGOutput;
import panels.ShapePanel;
import responses.ButtonResponse;

public class ShapeColorButton extends OptionButton {
    public ShapeColorButton(ShapePanel sp, PNGOutput png, ButtonResponse response) {
        super(sp, png, response);
    }

    @Override
    public String getButtonName() {
        return "Shape Colour";
    }
}
