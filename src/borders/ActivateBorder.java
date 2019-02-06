package borders;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Insets;

import javax.swing.border.Border;

public class ActivateBorder implements Border {

    private boolean activated = false;
    private String label;

    public ActivateBorder(String label) {
        this.label = label;
    }

    @Override
    public Insets getBorderInsets(Component arg0) {
        return new Insets(0, 0, 0, 0);
    }

    @Override
    public boolean isBorderOpaque() {
        return false;
    }

    @Override
    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
        int layers = 10;
        for (int i = 0; i < layers; i++) {
            g.setColor(new Color(20, 20, 20));
            g.drawRect(x + i, y + i, width - (i * 2), height - (i * 2));
            // Red or green
            if (!activated) {
                g.setColor(new Color(0, 0, 0));
                g.fillRect(x, y, width, height);
                g.setColor(new Color(200, 0, 0));
            } else {
                g.setColor(new Color(0, 120, 0));
                g.fillRect(x, y, width, height);
                g.setColor(new Color(0, 220, 0));
            }
            for (int j = 0; j < 5; j++) {
                g.drawRect(x + j, y + j, width - (j * 2), height - (j * 2));
            }
            g.setFont(new Font("Arial", Font.BOLD, 22));
            g.setColor(new Color(160, 160, 50));
            g.drawString(label, width / 2 - (g.getFontMetrics().stringWidth(label) / 2), height / 2 + 8);
        }
    }

    public void setActivated(boolean activated) {
        this.activated = activated;
    }

    public boolean getActivated() {
        return activated;
    }

    public String getLabel() {
        return label;
    }

}
