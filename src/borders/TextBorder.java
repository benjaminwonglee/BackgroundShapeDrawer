package borders;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Insets;
import java.util.Scanner;

import javax.swing.border.Border;

public class TextBorder implements Border {
    private String text;
    private Font font;

    public TextBorder(String text) {
        this.text = text;
        font = new Font("Arial", 1, 18);
    }

    @Override
    public void paintBorder(Component c, Graphics g, int x, int y, int wd, int ht) {
        g.setColor(Color.WHITE);
        g.fillRect(x, y, wd, ht);
        g.setColor(Color.BLACK);
        g.drawRect(x, y, wd - 1, ht - 1);
        g.setFont(this.font);
        if (text.length() < 120) {
            g.drawString(text, wd / 2 - (g.getFontMetrics().stringWidth(text) / 2), ht / 2 + (this.font.getSize() / 2));
        } else {
            Scanner sc = new Scanner(text);
            String textOne = "";
            for (int i = 0; i < 15; i++) {
                textOne += sc.next() + " ";
            }
            String textTwo = "";
            while (sc.hasNext()) {
                textTwo += sc.next() + " ";
            }
            g.drawString(textOne, wd / 2 - (g.getFontMetrics().stringWidth(textOne) / 2),
                    ht / 3 + 2);
            g.drawString(textTwo, wd / 2 - (g.getFontMetrics().stringWidth(textTwo) / 2),
                    ((ht / 3) * 2) + (this.font.getSize() / 2));
            sc.close();
        }
    }

    @Override
    public boolean isBorderOpaque() {
        return false;
    }

    @Override
    public Insets getBorderInsets(Component arg0) {
        return new Insets(0, 0, 0, 0);
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setFont(Font f) {
        this.font = f;

    }
}
