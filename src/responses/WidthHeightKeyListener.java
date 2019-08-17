package responses;

import borders.TextBorder;
import shapes.ShapeAbstract;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class WidthHeightKeyListener implements KeyListener {

    private final JTextArea textArea;
    private final SizeDimension sizeDimension;

    public WidthHeightKeyListener(JTextArea textArea, SizeDimension sizeDimension) {
        this.textArea = textArea;
        this.sizeDimension = sizeDimension;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        checkText();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyChar() == KeyEvent.VK_ENTER) {
            try {
                String text = textArea.getText();
                int newWidthHeight = Integer.parseInt(text);
                if (sizeDimension == SizeDimension.WIDTH) {
                    ShapeAbstract.setWidth(newWidthHeight);
                } else if (sizeDimension == SizeDimension.HEIGHT) {
                    ShapeAbstract.setHeight(newWidthHeight);
                }
                textArea.setBorder(new TextBorder(newWidthHeight + ""));
            } catch (Exception ex) {
                // parsing exception, just ignore it and restore the old value
                if (sizeDimension == SizeDimension.WIDTH) {
                    textArea.setBorder(new TextBorder("" + ShapeAbstract.getWidth()));
                } else if (sizeDimension == SizeDimension.HEIGHT) {
                    textArea.setBorder(new TextBorder("" + ShapeAbstract.getHeight()));
                }
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        checkText();
    }

    private void checkText() {
        try {
            String text = textArea.getText();
            if (!text.isEmpty()) {
                int newWidthHeight = Integer.parseInt(text);
                textArea.setBorder(new TextBorder(newWidthHeight + ""));
            } else {
                textArea.setBorder(new TextBorder(""));
            }
        } catch (Exception ex) {
            // parsing exception, just ignore it and restore the old value
            String text;
            if (sizeDimension == SizeDimension.WIDTH) {
                text = "" + ShapeAbstract.getWidth();
            } else {
                assert sizeDimension == SizeDimension.HEIGHT;
                text = "" + ShapeAbstract.getHeight();
            }
            textArea.setBorder(new TextBorder(text));
            textArea.setText(text);
        }
    }
}
