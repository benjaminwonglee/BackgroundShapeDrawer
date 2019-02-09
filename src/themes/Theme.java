package themes;

import java.awt.Graphics;

import javax.swing.JPanel;

public interface Theme {
    void applyTheme(Graphics g, JPanel sp);

    default String getName() {
        return getThemeName().getThemeName();
    }

    ThemeName getThemeName();
}
