package themes;

import java.awt.Graphics;

import javax.swing.JPanel;

public interface Theme {
    void setTheme(Graphics g, JPanel sp);

    String name();
}
