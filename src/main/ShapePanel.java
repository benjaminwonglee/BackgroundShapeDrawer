package main;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;

public class ShapePanel extends JPanel {
	private static final long serialVersionUID = -2760824343231275996L;

	private final static int BUTTON_WD = 180;
	private final static int BUTTON_HT = 160;

	private List<JButton> buttonList;
	private int xLoc = 20;
	private int yLoc = 20;

	public ShapePanel(Dimension frameSize) {
		this.setPreferredSize(frameSize);
		createButtons();
		this.setLayout(null);
	}

	private void createButtons() {
		buttonList = new ArrayList<JButton>();
		defineButtons();
		for (JButton j : buttonList) {
			arrangeLayout(j);
		}
	}

	public void defineButtons() {
		JButton circle = setButtonDefaults("Circle");
		JButton ellipse = setButtonDefaults("Ellipse");
		JButton hexagon = setButtonDefaults("Hexagon");
		JButton lightning = setButtonDefaults("Lightning");
		JButton octagon = setButtonDefaults("Octagon");
		JButton polygon = setButtonDefaults("Polygon");
		JButton rectangle = setButtonDefaults("Rectangle");
		JButton square = setButtonDefaults("Square");
		JButton star = setButtonDefaults("Star");
		JButton triangle = setButtonDefaults("Triangle");
		buttonList.add(circle);
		buttonList.add(ellipse);
		buttonList.add(hexagon);
		buttonList.add(lightning);
		buttonList.add(octagon);
		buttonList.add(polygon);
		buttonList.add(rectangle);
		buttonList.add(square);
		buttonList.add(star);
		buttonList.add(triangle);
	}

	private JButton setButtonDefaults(String shape) {
		JButton button = new JButton();
		button.setPreferredSize(new Dimension(BUTTON_WD, BUTTON_HT));
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

	private void arrangeLayout(JButton j) {
		j.setBounds(new Rectangle(xLoc, yLoc, BUTTON_WD, BUTTON_HT));
		yLoc += (BUTTON_HT + 20);
		if (yLoc > 770) {
			xLoc += (BUTTON_WD + 20);
			yLoc = 20;
		}
		this.add(j);
	}

}
