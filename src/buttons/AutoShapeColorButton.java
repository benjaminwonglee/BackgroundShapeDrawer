package buttons;

import output.PNGOutput;
import panels.ShapePanel;
import responses.ButtonResponse;

public class AutoShapeColorButton extends OptionButton {
    public AutoShapeColorButton(ShapePanel sp, PNGOutput png, ButtonResponse response) {
        super(sp, png, response);
    }

    @Override
    public String getButtonName() {
        return "Auto Shape Color";
    }
}
