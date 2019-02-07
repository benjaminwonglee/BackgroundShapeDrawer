package textboxes;

import java.awt.Color;

import borders.ColorBorder;

public class ChangeOutlineColor extends TextBox {

    private Color outlineColor;

    public ChangeOutlineColor(Color outlineColor) {
        this.outlineColor = outlineColor;
        create();
    }

    public void create() {
        newTextAreaBounds(this);
        setBorder(new ColorBorder(outlineColor));
    }
}
