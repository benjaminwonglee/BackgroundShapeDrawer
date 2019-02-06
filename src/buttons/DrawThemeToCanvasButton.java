package buttons;

import output.PNGOutput;
import panels.ShapePanel;
import responses.ButtonResponse;

public class DrawThemeToCanvasButton extends OptionButton {
    public DrawThemeToCanvasButton(ShapePanel sp, PNGOutput png, ButtonResponse response, String label) {
        super(sp, png, response, label);
    }
}
