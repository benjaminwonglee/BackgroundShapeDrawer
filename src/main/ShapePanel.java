package main;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class ShapePanel extends JPanel {
	private static final long serialVersionUID = -2760824343231275996L;

	private final static int BUTTON_WD = 180;
	private final static int BUTTON_HT = 160;

	private List<JButton> buttonList;
	private int xLoc = 20;
	private int yLoc = 20;

	public ShapePanel() {
		this.setPreferredSize(new Dimension(1500, 1000));
		createButtons();
		this.setLayout(null);
	}

	private void createButtons() {
		buttonList = new ArrayList<JButton>();
		defineButtons();
		for (JButton j : buttonList) {
			arrangeLayout(j);
		}
		createDrawButton();
	}

	private void createDrawButton() {
		// Add Draw! JButton
		JButton draw = new JButton();
		draw.setPreferredSize(new Dimension(xLoc, BUTTON_HT));
		draw.setBounds(new Rectangle(xLoc, 20, this.getPreferredSize().width - xLoc - 20, BUTTON_HT));
		draw.setBorder(new Border() {
			@Override
			public void paintBorder(Component c, Graphics g, int x, int y, int wd, int ht) {
				g.setColor(new Color(100, 200, 100));
				g.setFont(new Font("Georgia", 1, 44));
				g.drawString("Draw!", wd / 2 - (g.getFontMetrics().stringWidth("Draw!") / 2), ht / 2 + 14);
				for (int i = 0; i < 5; i++) {
					g.drawRect(x + i, y + i, wd - (i * 2), ht - (i * 2));
				}
			}

			@Override
			public boolean isBorderOpaque() {
				return false;
			}

			@Override
			public Insets getBorderInsets(Component arg0) {
				return new Insets(0, 0, 0, 0);
			}
		});
		draw.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		this.add(draw);
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

	public void drawShapes() {

	}

}
