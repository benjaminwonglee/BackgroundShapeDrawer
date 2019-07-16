package buttons;

import borders.OptionBorder;
import output.PNGOutput;
import panels.ShapePanel;
import responses.ButtonResponse;

import javax.swing.*;
import java.awt.*;

public abstract class OptionButton extends JButton {
    private static Color optColor;
    private static int optionButtonWidth;
    private static int optionButtonHeight;

    private final ShapePanel sp;
    private final PNGOutput png;
    private final ButtonResponse response;

    public OptionButton(ShapePanel sp, PNGOutput png, ButtonResponse response) {
        this.sp = sp;
        this.png = png;
        this.response = response;
        create(getButtonName());
    }

    public static void newButtonBounds(JButton button) {
        button.setBounds(ShapePanel.getXLoc(), ShapePanel.getYLoc(), optionButtonWidth, optionButtonHeight);
    }

    public void create(String label) {
        newButtonBounds(this);
        setBorder(new OptionBorder(label, getOptColor()));
        addActionListener(event -> getResponse().respond(sp));
    }

    public static void setOptColor(Color optColor) {
        OptionButton.optColor = optColor;
    }

    public static Color getOptColor() {
        return optColor;
    }

    public static int getOptionButtonWidth() {
        return optionButtonWidth;
    }

    public static void setOptionButtonWidth(int optionButtonWidth) {
        OptionButton.optionButtonWidth = optionButtonWidth;
    }

    public static int getOptionButtonHeight() {
        return optionButtonHeight;
    }

    public static void setOptionButtonHeight(int optionButtonHeight) {
        OptionButton.optionButtonHeight = optionButtonHeight;
    }

    public ShapePanel getShapePanel() {
        return sp;
    }

    public PNGOutput getPng() {
        return png;
    }

    public ButtonResponse getResponse() {
        return response;
    }

    public abstract String getButtonName();
}
