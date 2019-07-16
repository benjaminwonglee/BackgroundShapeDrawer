package textboxes;

import borders.ColorBorder;

import java.awt.*;

public class ColoredLabel extends TextBox {

    private final Color backgroundColor;

    public ColoredLabel(Color labelColor) {
        this.backgroundColor = labelColor;
        create();
    }

    private void create() {
        newTextAreaBounds(this);
        setBorder(new ColorBorder(backgroundColor));
    }
}
