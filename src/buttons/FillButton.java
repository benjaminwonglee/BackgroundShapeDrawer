package buttons;

import borders.OptionBorder;
import borders.TextBorder;
import misc.FillStatus;
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
        int textBoxSize = 150;
        ShapePanel sp = getShapePanel();

        newButtonBounds(this);
        Rectangle buttonSize = this.getBounds();
        this.setBounds(new Rectangle(buttonSize.x, buttonSize.y, buttonSize.width - textBoxSize,
                buttonSize.height));
        setBorder(new OptionBorder(getButtonName(), getOptColor()));

        JTextArea fillStatusBox = new JTextArea();
        fillStatusBox.setBounds(new Rectangle(buttonSize.x + buttonSize.width - textBoxSize, buttonSize.y,
                textBoxSize, buttonSize.height));
        TextBorder fillBorder = new TextBorder("");
        fillBorder.setText(sp.getFillStatus().getFormattedName());
        fillBorder.setFont(new Font("Arial", Font.BOLD, 24));
        fillStatusBox.setBorder(fillBorder);
        addActionListener(event -> {
            FillStatus nextFill = FillStatus.getNext(sp.getFillStatus());
            sp.setFillStatus(nextFill);
            fillBorder.setText(nextFill.getFormattedName());
            fillStatusBox.repaint();
        });
        sp.add(fillStatusBox);
    }

    @Override
    public String getButtonName() {
        return "Fill";
    }
}