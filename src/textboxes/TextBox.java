package textboxes;

import panels.ShapePanel;

import javax.swing.*;
import java.awt.*;

public abstract class TextBox extends JTextArea {
    private static int optionButtonWidth;
    private static int optionButtonHeight;

    public static void newTextAreaBounds(JTextArea t) {
        t.setBounds(ShapePanel.getXLoc(), ShapePanel.getYLoc(), optionButtonWidth, optionButtonHeight);
    }

    public static int getOptionButtonWidth() {
        return optionButtonWidth;
    }

    public static void setOptionButtonWidth(int optionButtonWidth) {
        TextBox.optionButtonWidth = optionButtonWidth;
    }

    public static int getOptionButtonHeight() {
        return optionButtonHeight;
    }

    public static void setOptionButtonHeight(int optionButtonHeight) {
        TextBox.optionButtonHeight = optionButtonHeight;
    }
}
