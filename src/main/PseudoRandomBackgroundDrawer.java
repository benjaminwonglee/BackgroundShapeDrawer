package main;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;

public class PseudoRandomBackgroundDrawer {

	private List<JButton> shapeButtons;

	public PseudoRandomBackgroundDrawer() {
		JFrame frame = new JFrame();
		ShapePanel sp = new ShapePanel(frame);
		shapeButtons = new ArrayList<JButton>();
		defineButtons();
		for (JButton j : shapeButtons) {
			sp.add(j);
		}
		frame.add(sp);
		setFrameProperties(frame);
	}

	public void defineButtons() {
		JButton circle = setButtonDefaults("Circle");
		JButton ellipse = setButtonDefaults("Ellipse");
		JButton hexagon = setButtonDefaults("Hexagon");
		JButton octagon = setButtonDefaults("Octagon");
		JButton polygon = setButtonDefaults("Polygon");
		JButton rectangle = setButtonDefaults("Rectangle");
		JButton square = setButtonDefaults("Square");
		JButton star = setButtonDefaults("Star");
		JButton triangle = setButtonDefaults("Triangle");
		shapeButtons.add(circle);
		shapeButtons.add(ellipse);
		shapeButtons.add(hexagon);
		shapeButtons.add(octagon);
		shapeButtons.add(polygon);
		shapeButtons.add(rectangle);
		shapeButtons.add(square);
		shapeButtons.add(star);
		shapeButtons.add(triangle);
	}

	private JButton setButtonDefaults(String shape) {
		JButton button = new JButton();
		button.setPreferredSize(new Dimension(150, 100));
		ActivateBorder onOffBorder = new ActivateBorder(shape);
		button.setBorder(onOffBorder);
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				onOffBorder.setActivated(!onOffBorder.getActivated());
			}
		});
		return button;
	}

	private void setFrameProperties(JFrame frame) {
		frame.setTitle("PseudoRandomBackgroundDrawer");
		frame.setPreferredSize(new Dimension(1500, 1000));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setLocationRelativeTo(null); // Center frame after pack
		frame.setVisible(true);
	}

	public static void main(String args[]) {
		new PseudoRandomBackgroundDrawer();
	}
}