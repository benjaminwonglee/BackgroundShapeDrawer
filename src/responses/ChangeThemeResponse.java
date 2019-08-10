package responses;

import panels.ShapePanel;
import themes.ITheme;
import themes.Theme;

public class ChangeThemeResponse implements ButtonResponse {

    /**
     * Set the current theme to the next one in the list.
     */
    @Override
    public void respond(ShapePanel sp) {
        ITheme theme = sp.getTheme();

        Theme next = Theme.getNext(theme.getThemeEnum());
        sp.setTheme(next.getTheme());
        sp.repaint();

        String themeName = sp.getTheme().getName();
        sp.writeToTextBoxAndRepaint(themeName.substring(0, 1).toUpperCase() + themeName.substring(1));
        sp.setThemeDrawn(false);
    }
}
