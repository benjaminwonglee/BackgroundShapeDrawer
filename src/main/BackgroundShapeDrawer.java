package main;

import panels.ShapePanel;

import javax.swing.*;
import java.awt.*;

public class BackgroundShapeDrawer {
    public BackgroundShapeDrawer() {
        JFrame frame = new JFrame();
        ShapePanel sp = new ShapePanel();
        frame.add(sp);
        setFrameProperties(frame);
    }

    private void setFrameProperties(JFrame frame) {
        frame.setTitle("BackgroundShapeDrawer");
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setPreferredSize(new Dimension(screenSize.width, screenSize.height));
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.pack();
        frame.setLocationRelativeTo(null); // Center frame after pack
        frame.setVisible(true);
    }

    public static void main(String args[]) {
        new BackgroundShapeDrawer();
    }
}