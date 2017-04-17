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
import java.io.Console;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;
import javax.swing.border.Border;

public class ShapePanel extends JPanel {
	private static final long serialVersionUID = -2760824343231275996L;

	private final static int BUTTON_WD = 180;
	private final static int BUTTON_HT = 160;

	private List<JButton> buttonList;
	private int xLoc = 20;
	private int yLoc = 20;
	private JTextArea textDisplay;
	private JTextArea userEntry;
	private Rectangle canvasSize;

	private int canvasRed = 20;
	private int canvasBlue = 20;
	private int canvasGreen = 20;

	public ShapePanel() {
		this.setPreferredSize(new Dimension(1500, 1000));
		this.setLayout(null); // Important for specifying own layout preferences
		textDisplay = new JTextArea("Click \"Draw!\" when ready");
		userEntry = new JTextArea("Use this text area for answering queries");
		createButtons();
	}

	private void createButtons() {
		buttonList = new ArrayList<JButton>();
		defineButtons();
		for (JButton j : buttonList) {
			arrangeLayout(j);
		}
		createDrawButton();
		createTextAreas();
		canvasSize = new Rectangle(xLoc, yLoc, this.getPreferredSize().width - xLoc - 20,
				this.getPreferredSize().height - yLoc - 100);
	}

	@Override
	public void paintComponent(Graphics g) {
		g.setColor(new Color(canvasRed, canvasBlue, canvasGreen));
		g.fillRect(canvasSize.x, canvasSize.y, canvasSize.width, canvasSize.height);
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
				drawShapes();
			}
		});
		this.add(draw);
		yLoc = BUTTON_HT + 40;
	}

	private void createTextAreas() {
		textDisplay.setPreferredSize(new Dimension(this.getPreferredSize().width - xLoc, 30));
		userEntry.setPreferredSize(new Dimension(this.getPreferredSize().width - xLoc, 30));
		// this.getPreferredSize().width - xLoc == 1080
		textDisplay.setBounds(new Rectangle(xLoc, yLoc - 5, this.getPreferredSize().width - xLoc - 20, 30));
		yLoc += 40;
		userEntry.setBounds(new Rectangle(xLoc, yLoc - 5, this.getPreferredSize().width - xLoc - 20, 30));
		yLoc += 40;
		textDisplay.setFont(new Font("Times New Roman", 1, 16));
		userEntry.setFont(new Font("Times New Roman", 1, 16));
		textDisplay.setEditable(false);
		this.add(textDisplay);
		this.add(userEntry);
	}

	public void drawShapes() {
		boolean retry = true;
		while (retry) {
			textDisplay.setText(
					"Choose rgb color in the panel below: input 3 integers; each between 0 and 255 (with spaces in between them) for red, green, blue values: ");
			userEntry.setText("0 200 0");
			Scanner sc = new Scanner(userEntry.getText());
			System.out.println("WHOA");
			try {
				int red = Integer.parseInt(sc.next());
				if (red < 0 || red > 255) {
					textDisplay.setText("The chosen red value was out of range, please try again");
					continue;
				}
				canvasRed = red;

				int green = Integer.parseInt(sc.next());
				if (green < 0 || green > 255) {
					textDisplay.setText("The chosen red value was out of range, please try again");
					continue;
				}
				canvasGreen = green;

				int blue = Integer.parseInt(sc.next());
				if (blue < 0 || blue > 255) {
					textDisplay.setText("The chosen red value was out of range, please try again");
					continue;
				}
				canvasBlue = blue;
				retry = false;
				Graphics g = this.getGraphics();
				g.setColor(new Color(canvasRed, canvasGreen, canvasBlue));
				g.fillRect(canvasSize.x, canvasSize.y, canvasSize.width, canvasSize.height);
			} catch (NumberFormatException e) {
				textDisplay.setText("An integer number was not entered!");
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e1) {
				}
				retry = true;
				continue;
			}
			sc.close();
		}

		retry = true;
		for (JButton j : buttonList) {
			ActivateBorder border = (ActivateBorder) j.getBorder();
			if (border.getActivated()) {
				// Keep retrying until user enters integers
				while (retry) {
					textDisplay.setText("How many " + border.getLabel().toLowerCase() + "s: ");
					String s = "";
					try {
						int input = Integer.parseInt(s);
						if (input > 10) {
							textDisplay.setText("That's too many!");
						} else if (input < 0) {
							textDisplay.setText("That number's too little!");
						} else {
							retry = false;
						}
					} catch (NumberFormatException e) {
						textDisplay.setText("You didn't enter an integer number!");
						try {
							Thread.sleep(1000);
						} catch (InterruptedException e1) {
						}
						retry = true;
					}
				}
			}
		}
	}
}
