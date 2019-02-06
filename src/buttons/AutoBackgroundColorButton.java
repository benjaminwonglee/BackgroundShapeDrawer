package buttons;

import borders.OptionBorder;
import output.PNGOutput;
import panels.ShapePanel;
import responses.ButtonResponse;

public class AutoBackgroundColorButton extends OptionButton {
    public AutoBackgroundColorButton(ShapePanel sp, PNGOutput png, ButtonResponse response, String label) {
        super(sp, png, response, label);
    }
}
