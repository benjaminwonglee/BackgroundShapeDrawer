package textboxes;

import java.awt.Color;

import borders.ColorBorder;

public class ColoredLabel extends TextBox {

    private final Color backgroundColor;

    public ColoredLabel(Color labelColor) {
        this.backgroundColor = labelColor;
        create();
    }

    public void create() {
        newTextAreaBounds(this);
        setBorder(new ColorBorder(backgroundColor));
    }
}
