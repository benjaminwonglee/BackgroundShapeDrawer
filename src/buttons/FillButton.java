package buttons;

import borders.OptionBorder;
import borders.TextBorder;
import output.PNGOutput;
import panels.ShapePanel;
import responses.ButtonResponse;

import javax.swing.*;
import java.awt.*;

public class FillButton extends OptionButton {

    public FillButton(ShapePanel sp, PNGOutput png, ButtonResponse response) {
        super(sp, png, response);
    }

    @Override
    public void create(String label) {
        int textBoxSize = 100;
        ShapePanel sp = getShapePanel();

        newButtonBounds(this);
        Rectangle buttonSize = this.getBounds();
        this.setBounds(new Rectangle(buttonSize.x, buttonSize.y, buttonSize.width - textBoxSize,
                buttonSize.height));

        setBorder(new OptionBorder(getButtonName(), getOptColor()));

        JTextArea fillStatus = new JTextArea();
        fillStatus.setBounds(new Rectangle(buttonSize.x + buttonSize.width - textBoxSize, buttonSize.y,
                textBoxSize, buttonSize.height));
        TextBorder fillBorder = new TextBorder("");
        if (sp.isToFill()) {
            fillBorder.setText("Yes");
        } else {
            fillBorder.setText("No");
        }
        fillBorder.setFont(new Font("Arial", Font.BOLD, 32));
        fillStatus.setBorder(fillBorder);
        addActionListener(event -> {
            sp.setToFill(!sp.isToFill());
            if (sp.isToFill()) {
                fillBorder.setText("Yes");
            } else {
                fillBorder.setText("No");
            }
            fillStatus.repaint();
        });
        sp.add(fillStatus);
    }

    @Override
    public String getButtonName() {
        return "Fill";
    }
}
