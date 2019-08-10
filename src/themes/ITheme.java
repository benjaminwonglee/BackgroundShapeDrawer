package themes;

import javax.swing.*;
import java.awt.*;

public interface ITheme {

    void applyTheme(Graphics g, JPanel sp);

    default String getName() {
        return getThemeEnum().getThemeName();
    }

    Theme getThemeEnum();

    default ITheme getTheme() {
        return this;
    }
}
