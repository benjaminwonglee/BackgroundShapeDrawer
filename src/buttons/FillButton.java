package buttons;

import borders.OptionBorder;
import borders.TextBorder;
import output.PNGOutput;
import panels.ShapePanel;
import responses.ButtonResponse;

import javax.swing.*;
import java.awt.*;

public class FillButton extends OptionButton {

    public FillButton(ShapePanel sp, PNGOutput png, ButtonResponse response, String label) {
        super(sp, png, response, label);
    }

    @Override
    public void create(String label) {
        int textBoxSize = 100;
        ShapePanel sp = getShapePanel();

        newButtonBounds(this);
        Rectangle buttonSize = this.getBounds();
        this.setBounds(new Rectangle(buttonSize.x, buttonSize.y, buttonSize.width - textBoxSize,
                buttonSize.height));

        setBorder(new OptionBorder("Fill", getOptColor()));

        JTextArea fillStatus = new JTextArea();
        fillStatus.setBounds(new Rectangle(buttonSize.x + buttonSize.width - textBoxSize, buttonSize.y,
                textBoxSize, buttonSize.height));
        TextBorder fillBorder = new TextBorder("");
        if (sp.getFill()) {
            fillBorder.setText("Yes");
        } else {
            fillBorder.setText("No");
        }
        fillBorder.setFont(new Font("Arial", Font.BOLD, 32));
        fillStatus.setBorder(fillBorder);
        addActionListener(event -> {
            sp.setFill(!sp.getFill());
            if (sp.getFill()) {
                fillBorder.setText("Yes");
            } else {
                fillBorder.setText("No");
            }
            fillStatus.repaint();
        });
        sp.add(fillStatus);
    }
}
