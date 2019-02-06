package buttons;

import output.PNGOutput;
import panels.ShapePanel;
import responses.ButtonResponse;

public class ShapeColorButton extends OptionButton {
    public ShapeColorButton(ShapePanel sp, PNGOutput png, ButtonResponse response, String label) {
        super(sp, png, response, label);
    }
}
