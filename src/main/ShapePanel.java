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
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;

import shapes.*;

public class ShapePanel extends JPanel {
	private static final long serialVersionUID = -2760824343231275996L;

	private final static int BUTTON_WD = 180;
	private final static int BUTTON_HT = 160;

	private List<JButton> buttonList;
	private int xLoc = 20;
	private int yLoc = 20;
	private JTextArea textDisplay;
	private JTextField userInput;
	private Rectangle canvasSize;

	private int canvasRed = 0;
	private int canvasBlue = 0;
	private int canvasGreen = 0;

	private ArrayList<String> activated;
	private ArrayList<Shape> shapes;

	private Color outlineColor;

	// Button response booleans
	private boolean changeBackground = false;
	private boolean drawShapes = false;
	private boolean shapeColour = false;

	public ShapePanel() {
		this.setPreferredSize(new Dimension(1500, 1000));
		this.setLayout(null); // Important for specifying own layout preferences
		textDisplay = new JTextArea("Choose buttons then either change the background or draw shapes");
		userInput = new JTextField();
		activated = new ArrayList<String>();
		shapes = new ArrayList<Shape>();
		outlineColor = new Color(200, 0, 0);
		createButtons();
	}

	public void createButtons() {
		buttonList = new ArrayList<JButton>();
		defineButtons();
		for (JButton j : buttonList) {
			arrangeLayout(j);
		}
		createOptionsButtons();
		createTextAreas();
		canvasSize = new Rectangle(xLoc, yLoc, this.getPreferredSize().width - xLoc - 20,
				this.getPreferredSize().height - yLoc - 100);
	}

	@Override
	public void paintComponent(Graphics g) {
		g.setColor(new Color(canvasRed, canvasBlue, canvasGreen));
		g.fillRect(canvasSize.x, canvasSize.y, canvasSize.width, canvasSize.height);
	}

	private void defineButtons() {
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

	private void createOptionsButtons() {
		// Add Change Background Button
		int width = (this.getPreferredSize().width - xLoc - 30) / 4;
		JButton changeBackground = new JButton();
		changeBackground.setPreferredSize(new Dimension(xLoc, BUTTON_HT));
		changeBackground.setBounds(new Rectangle(xLoc, 20, width - 10, BUTTON_HT));
		changeBackground.setBorder(new OptionBorder("Change Background"));
		changeBackground.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				changeBackgroundButtonResponse();
			}
		});
		xLoc += (width + 20);

		// Add Choose Shape Colour Button
		JButton shapeColour = new JButton();
		shapeColour.setPreferredSize(new Dimension(xLoc, BUTTON_HT));
		shapeColour.setBounds(new Rectangle(xLoc, 20, width - 10, BUTTON_HT));
		shapeColour.setBorder(new OptionBorder("Shape Colour"));
		shapeColour.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				shapeColourButtonResponse();
			}

		});
		xLoc += (width + 20);

		// Add Draw Shapes Button
		JButton draw = new JButton();
		draw.setPreferredSize(new Dimension(xLoc, BUTTON_HT));
		draw.setBounds(new Rectangle(xLoc, 20, width - 10, BUTTON_HT));
		draw.setBorder(new OptionBorder("Draw Shapes"));
		draw.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				drawShapesButtonResponse();
			}
		});

		this.add(changeBackground);
		this.add(shapeColour);
		this.add(draw);

		xLoc -= ((width * 2) + 40);
		yLoc = BUTTON_HT + 40;
	}

	private class OptionBorder implements Border {
		private String label;

		public OptionBorder(String label) {
			this.label = label;
		}

		@Override
		public void paintBorder(Component c, Graphics g, int x, int y, int wd, int ht) {
			g.setColor(new Color(100, 200, 100));
			g.setFont(new Font("Georgia", 1, 22));
			g.drawString(label, wd / 2 - (g.getFontMetrics().stringWidth(label) / 2), ht / 2 + 14);
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
	}

	private void createTextAreas() {
		textDisplay.setPreferredSize(new Dimension(this.getPreferredSize().width - xLoc, 30));
		textDisplay.setBounds(new Rectangle(xLoc, yLoc - 5, this.getPreferredSize().width - xLoc - 20, 50));
		yLoc += 55;

		userInput.setPreferredSize(new Dimension(this.getPreferredSize().width - xLoc, 30));
		userInput.setBounds(new Rectangle(xLoc, yLoc - 5, this.getPreferredSize().width - xLoc - 90, 30));
		JButton ok = new JButton("OK!");
		ok.setBounds(new Rectangle(this.getPreferredSize().width - 90, yLoc - 5, 70, 30));
		ok.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				userInputResponse();
			}
		});
		yLoc += 35;

		textDisplay.setFont(new Font("Times New Roman", 1, 18));
		textDisplay.setEditable(false);

		userInput.setFont(new Font("Times New Roman", 1, 16));
		ok.setFont(new Font("Times New Roman", 1, 14));

		this.add(textDisplay);
		this.add(userInput);
		this.add(ok);
	}

	public void changeBackgroundButtonResponse() {
		this.changeBackground = true;
		textDisplay.setText(
				"Changing background color: Choose rgb color in the panel below: input 3 integers; each between 0 and 255 (space separated)\nfor red, green, blue values. Click \"OK!\" when ready");
		textDisplay.update(textDisplay.getGraphics());
	}

	public void changeBackground() {
		if (userInput.getText().equals("")) {
			textDisplay.setText("No numbers were entered! Try again.");
			textDisplay.update(textDisplay.getGraphics());
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e1) {
			}
			return;
		}
		chooseBackgroundColour();
	}

	public void shapeColourButtonResponse() {
		this.shapeColour = true;
		textDisplay.setText(
				"Changing outline color: Choose rgb color in the panel below: input 3 integers; each between 0 and 255 (space separated)\nfor red, green, blue values. Click \"OK!\" when ready");
		textDisplay.update(textDisplay.getGraphics());
	}

	public void drawShapesButtonResponse() {
		this.drawShapes = true;

		// Add activated shapes
		this.activated = new ArrayList<String>();
		for (int i = 0; i < buttonList.size(); i++) {
			ActivateBorder border = (ActivateBorder) buttonList.get(i).getBorder();
			if (border.getActivated()) {
				String name = border.getLabel();
				activated.add(name);
			}
		}
		userInput.setText("");
		userInput.update(userInput.getGraphics());
		userInputResponse();
	}

	public void drawShapes() {
		if (activated.size() > 0) {
			textDisplay.setText("How many " + activated.get(0).toLowerCase() + "s? ");
			textDisplay.update(textDisplay.getGraphics());
			if (!userInput.getText().equals("")) {
				try {
					int input = Integer.parseInt(userInput.getText());
					if (input > 40 || input <= 0) {
						textDisplay.setText("Please enter a number that is less than 41 and greater than 0");
						textDisplay.update(textDisplay.getGraphics());
						return;
					}
					// Success
					createShape(activated.get(0), input);
					activated.remove(activated.get(0));
					if (!activated.isEmpty()) {
						// Continue. Pressing the ok button restarts this method
						textDisplay.setText("How many " + activated.get(0).toLowerCase() + "s? ");
						textDisplay.update(textDisplay.getGraphics());
					} else {
						// End case
						textDisplay.setText("And..... Draw!");
						textDisplay.update(textDisplay.getGraphics());
						draw();
						drawShapes = false;
					}
				} catch (NumberFormatException e) {
					textDisplay.setText("You didn't enter an integer number!");
					textDisplay.update(textDisplay.getGraphics());
				}
			}
		}
	}

	private void createShape(String shapeName, int amount) {
		if (shapeName.equalsIgnoreCase("circle")) {
			Circle c = new Circle();
			c.setAmount(amount);
			shapes.add(c);
		} else if (shapeName.equalsIgnoreCase("ellipse")) {
			Ellipse e = new Ellipse();
			e.setAmount(amount);
			shapes.add(e);
		} else if (shapeName.equalsIgnoreCase("hexagon")) {
			Hexagon h = new Hexagon();
			h.setAmount(amount);
			shapes.add(h);
		} else if (shapeName.equalsIgnoreCase("lightning")) {
			Lightning l = new Lightning();
			l.setAmount(amount);
			shapes.add(l);
		} else if (shapeName.equalsIgnoreCase("octagon")) {
			Octagon o = new Octagon();
			o.setAmount(amount);
			shapes.add(o);
		} else if (shapeName.equalsIgnoreCase("polygon")) {
			Polygon p = new Polygon();
			p.setAmount(amount);
			shapes.add(p);
		} else if (shapeName.equalsIgnoreCase("rectangle")) {
			shapes.Rectangle r = new shapes.Rectangle();
			r.setAmount(amount);
			shapes.add(r);
		} else if (shapeName.equalsIgnoreCase("square")) {
			Square s = new Square();
			s.setAmount(amount);
			shapes.add(s);
		} else if (shapeName.equalsIgnoreCase("star")) {
			Star s = new Star();
			s.setAmount(amount);
			shapes.add(s);
		} else if (shapeName.equalsIgnoreCase("triangle")) {
			Triangle t = new Triangle();
			t.setAmount(amount);
			shapes.add(t);
		}
	}

	private void draw() {
		for (Shape s : shapes) {
			s.drawShape(getGraphics(), outlineColor, canvasSize);
		}
		shapes = new ArrayList<Shape>();
	}

	private void userInputResponse() {
		if (changeBackground) {
			drawShapes = false;
			changeBackground = false;
			changeBackground();
		} else if (shapeColour) {
			drawShapes = false;
			changeBackground = false;
			changeBackground();
		} else if (drawShapes) {
			changeBackground = false;
			drawShapes();
		}
	}

	private void chooseBackgroundColour() {
		boolean localShapeColour = shapeColour;
		shapeColour = false;

		Scanner sc = new Scanner(userInput.getText());
		try {
			int x = 0;
			while (sc.hasNext() && x < 3) {
				x++;
				String currentColor = "red";
				if (x == 2) {
					currentColor = "green";
				} else if (x == 3) {
					currentColor = "blue";
				}
				int color = Integer.parseInt(sc.next());
				// Error scenario 1
				if (color < 0 || color > 255) {
					sc.close();
					textDisplay.setText("The chosen " + currentColor + " value was out of range, please try again");
					textDisplay.update(textDisplay.getGraphics());
					return;
				}
				if (x == 1) {
					canvasRed = color;
				} else if (x == 2) {
					canvasGreen = color;
				} else if (x == 3) {
					canvasBlue = color;
				}
			}
			// Error scenario 2
			if (x != 3) {
				sc.close();
				textDisplay.setText("Not enough integers were entered, please try again");
				textDisplay.update(textDisplay.getGraphics());
				return;
			}
			// Check if we are changing the shape colour or background colour
			if (!localShapeColour) {
				Graphics g = this.getGraphics();
				g.setColor(new Color(canvasRed, canvasGreen, canvasBlue));
				g.fillRect(canvasSize.x, canvasSize.y, canvasSize.width, canvasSize.height);
			} else {
				outlineColor = new Color(canvasRed, canvasGreen, canvasBlue);
				textDisplay.setText("Outline colour successfully changed");
				textDisplay.update(textDisplay.getGraphics());
			}
		} catch (NumberFormatException e) {
			// Error scenario 3
			textDisplay.setText("An integer number was not entered, please try again.");
			textDisplay.update(textDisplay.getGraphics());
			return;
		}
		sc.close();
	}
}
