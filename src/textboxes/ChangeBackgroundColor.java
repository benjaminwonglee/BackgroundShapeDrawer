package textboxes;

import java.awt.Color;

import borders.ColorBorder;

public class ChangeBackgroundColor extends TextBox {
    private Color backgroundColor;

    public ChangeBackgroundColor(Color backgroundColor) {
        this.backgroundColor = backgroundColor;
        create();
    }

    public void create() {
        newTextAreaBounds(this);
        setBorder(new ColorBorder(backgroundColor));
    }
}
