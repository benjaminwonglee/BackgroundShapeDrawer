package main;

import java.awt.Dimension;

import javax.swing.JFrame;

public class PseudoRandomBackgroundDrawer {
	public PseudoRandomBackgroundDrawer() {
		JFrame frame = new JFrame();
		ShapePanel sp = new ShapePanel();
		frame.add(sp);
		setFrameProperties(frame);
	}

	private void setFrameProperties(JFrame frame) {
		frame.setTitle("PseudoRandomBackgroundDrawer");
		frame.setPreferredSize(new Dimension(1500, 1000));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.pack();
		frame.setLocationRelativeTo(null); // Center frame after pack
		frame.setVisible(true);

	}

	public static void main(String args[]) {
		new PseudoRandomBackgroundDrawer();
	}
}