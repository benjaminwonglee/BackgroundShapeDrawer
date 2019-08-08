package themes;

import javax.swing.*;
import java.awt.*;

public interface ITheme {
    void applyTheme(Graphics g, JPanel sp);

    default String getName() {
        return getTheme().getThemeName();
    }

    Theme getTheme();
}
