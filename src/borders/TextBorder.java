package borders;

import javax.swing.border.Border;
import java.awt.*;
import java.util.Scanner;

public class TextBorder implements Border {
    private String text;
    private Font font;

    public TextBorder(String text) {
        this.text = text;
        font = new Font("Arial", Font.BOLD, 18);
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
            StringBuilder textOne = new StringBuilder();
            for (int i = 0; i < 15; i++) {
                textOne.append(sc.next()).append(" ");
            }
            StringBuilder textTwo = new StringBuilder();
            while (sc.hasNext()) {
                textTwo.append(sc.next()).append(" ");
            }
            g.drawString(textOne.toString(), wd / 2 - (g.getFontMetrics().stringWidth(textOne.toString()) / 2),
                    ht / 3 + 2);
            g.drawString(textTwo.toString(), wd / 2 - (g.getFontMetrics().stringWidth(textTwo.toString()) / 2),
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
