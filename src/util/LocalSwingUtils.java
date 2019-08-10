package util;

import java.awt.*;

public class LocalSwingUtils {

    public static Dimension getScreenSize() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int space = 40;
        return new Dimension(screenSize.width - space, screenSize.height - (space * 2));
    }

}
