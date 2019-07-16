package themes;

import javax.swing.*;
import java.awt.*;

public interface Theme {
    void applyTheme(Graphics g, JPanel sp);

    default String getName() {
        return getThemeName().getThemeName();
    }

    ThemeName getThemeName();
}
