package buttons;

import output.PNGOutput;
import panels.ShapePanel;
import responses.ButtonResponse;

public class DrawThemeToCanvasButton extends OptionButton {
    public DrawThemeToCanvasButton(ShapePanel sp, PNGOutput png, ButtonResponse response) {
        super(sp, png, response);
    }

    @Override
    public String getButtonName() {
        return "Draw Theme To Canvas";
    }
}
