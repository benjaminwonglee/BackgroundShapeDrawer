package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import borders.ActivateBorder;
import borders.ColorBorder;
import borders.OptionBorder;
import borders.SimpleBorder;
import borders.TextBorder;
import shapes.Circle;
import shapes.Ellipse;
import shapes.Hexagon;
import shapes.Lightning;
import shapes.Octagon;
import shapes.Star6;
import shapes.Shape;
import shapes.ShapeAbstract;
import shapes.Square;
import shapes.Star5;
import shapes.Triangle;

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
	private int optionButtonWidth;

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
	private boolean widthHeight = false;
	private boolean changeWidth = false;
	private boolean changeHeight = false;

	private int optionButtonHeight;

	// GUI display fields
	private JTextArea changeBackgroundColour;
	private JTextArea changeOutlineColour;
	private JTextArea widthText;
	private JTextArea heightText;
	private JComboBox<String> patternSelector;

	public ShapePanel() {
		this.setPreferredSize(new Dimension(1500, 1000));
		this.setLayout(null); // Important for specifying own layout preferences
		textDisplay = new JTextArea();
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
		JButton rectangle = setButtonDefaults("Rectangle");
		JButton square = setButtonDefaults("Square");
		JButton star5 = setButtonDefaults("5 Point Star");
		JButton star6 = setButtonDefaults("6 Point Star");
		JButton triangle = setButtonDefaults("Triangle");
		buttonList.add(circle);
		buttonList.add(ellipse);
		buttonList.add(hexagon);
		buttonList.add(lightning);
		buttonList.add(octagon);
		buttonList.add(rectangle);
		buttonList.add(square);
		buttonList.add(star5);
		buttonList.add(star6);
		buttonList.add(triangle);
	}

	public JButton setButtonDefaults(String shape) {
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
		int space = 18;
		Color optColour = new Color(100, 200, 100);

		// Add Change Background Button
		optionButtonWidth = ((this.getPreferredSize().width - xLoc - 70) / 4);
		optionButtonHeight = BUTTON_HT / 3;
		JButton changeBackground = new JButton();
		changeBackground.setBounds(new Rectangle(xLoc, yLoc, optionButtonWidth, optionButtonHeight));
		changeBackground.setBorder(new OptionBorder("Change Background", optColour));
		changeBackground.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				changeBackgroundButtonResponse();
			}
		});
		yLoc += optionButtonHeight;
		this.changeBackgroundColour = new JTextArea();
		changeBackgroundColour.setBounds(new Rectangle(xLoc, yLoc, optionButtonWidth, optionButtonHeight));
		changeBackgroundColour.setBorder(new ColorBorder(new Color(canvasRed, canvasBlue, canvasGreen)));

		xLoc += (optionButtonWidth + space);
		yLoc -= optionButtonHeight;

		// Add Choose Shape Colour Button
		JButton shapeColour = new JButton();
		shapeColour.setBounds(new Rectangle(xLoc, 20, optionButtonWidth, optionButtonHeight));
		shapeColour.setBorder(new OptionBorder("Shape Colour", optColour));
		shapeColour.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				shapeColourButtonResponse();
			}

		});

		yLoc += optionButtonHeight;
		this.changeOutlineColour = new JTextArea();
		changeOutlineColour.setBounds(new Rectangle(xLoc, yLoc, optionButtonWidth, optionButtonHeight));
		changeOutlineColour.setBorder(new ColorBorder(outlineColor));

		yLoc -= optionButtonHeight;
		xLoc += (optionButtonWidth + space);

		// Add Choose Set width & height Button
		JButton widthHeight = new JButton();
		widthHeight.setBounds(new Rectangle(xLoc, 20, optionButtonWidth, optionButtonHeight));
		widthHeight.setBorder(new OptionBorder("Set Width & Height", optColour));
		widthHeight.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				widthHeightButtonResponse();
			}
		});

		yLoc += optionButtonHeight;
		this.widthText = new JTextArea();
		widthText.setBounds(new Rectangle(xLoc, yLoc, optionButtonWidth / 2, optionButtonHeight));
		widthText.setBorder(new TextBorder("" + ShapeAbstract.getWidth()));
		this.heightText = new JTextArea();
		heightText.setBounds(
				new Rectangle(xLoc + optionButtonWidth / 2, yLoc, optionButtonWidth / 2, optionButtonHeight));
		heightText.setBorder(new TextBorder("" + ShapeAbstract.getHeight()));

		yLoc -= optionButtonHeight;
		xLoc += (optionButtonWidth + space);

		// Add Draw Shapes Button
		JButton draw = new JButton();
		draw.setBounds(new Rectangle(xLoc, 20, optionButtonWidth, optionButtonHeight * 4));
		draw.setBorder(new OptionBorder("Draw Shapes", optColour));
		draw.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				drawShapesButtonResponse();
			}
		});
		xLoc += (optionButtonWidth + space);

		this.add(changeBackground);
		this.add(changeBackgroundColour);
		this.add(shapeColour);
		this.add(changeOutlineColour);
		this.add(widthHeight);
		this.add(widthText);
		this.add(heightText);
		this.add(draw);

		xLoc -= (optionButtonWidth * 4) + (space * 4);
		yLoc += optionButtonHeight * 2 + space;

		// Row 2
		// Add Fill Button
		JButton fill = new JButton();
		fill.setBounds(new Rectangle(xLoc, yLoc, optionButtonWidth, optionButtonHeight));
		fill.setBorder(new OptionBorder("Fill", optColour));

		yLoc += (optionButtonHeight);
		JTextArea fillStatus = new JTextArea();
		fillStatus.setBounds(new Rectangle(xLoc, yLoc, optionButtonWidth, optionButtonHeight));
		TextBorder fillBorder = new TextBorder("");
		fillBorder.setFont(new Font("Arial", Font.BOLD, 32));
		if (ShapeAbstract.getFill()) {
			fillBorder.setText("Yes");
		} else {
			fillBorder.setText("No");
		}
		fillStatus.setBorder(fillBorder);
		fill.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ShapeAbstract.setFill(!ShapeAbstract.getFill());
				if (ShapeAbstract.getFill()) {
					fillBorder.setText("Yes");
				} else {
					fillBorder.setText("No");
				}
				fillStatus.repaint();
			}
		});

		xLoc += (optionButtonWidth + space);
		yLoc -= optionButtonHeight;

		JTextArea patternSelect = new JTextArea();
		patternSelect.setBounds(new Rectangle(xLoc, yLoc, optionButtonWidth, optionButtonHeight));
		TextBorder patternBorder = new TextBorder("Select Pattern");
		patternBorder.setFont(new Font("Arial", 1, 32));
		patternSelect.setBorder(patternBorder);

		yLoc += optionButtonHeight;
		this.patternSelector = new JComboBox<String>();
		patternSelector.setBounds(new Rectangle(xLoc, yLoc, optionButtonWidth, optionButtonHeight));
		patternSelector.setFont(new Font("Arial", 1, 24));
		patternSelector.addItem("Random");
		patternSelector.addItem("Aligned");
		patternSelector.addItem("Alternating");
		patternSelector.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (patternSelector.getSelectedItem().equals("Random")) {
					ShapeAbstract.setPattern(ShapeAbstract.DrawPattern.RANDOM);
				} else if (patternSelector.getSelectedItem().equals("Aligned")) {
					ShapeAbstract.setPattern(ShapeAbstract.DrawPattern.ALIGNED);
				} else if (patternSelector.getSelectedItem().equals("Alternating")) {
					ShapeAbstract.setPattern(ShapeAbstract.DrawPattern.ALTERNATING);
				}
			}
		});

		xLoc += (optionButtonWidth + space);
		yLoc -= optionButtonHeight;

		this.add(fill);
		this.add(fillStatus);
		this.add(patternSelect);
		this.add(patternSelector);

		xLoc -= (optionButtonWidth * 2) + (space * 2);
		yLoc += optionButtonHeight * 2 + space;

	}

	private void createTextAreas() {
		int textBoxWidth = this.getPreferredSize().width - xLoc - 20;
		textDisplay.setBounds(new Rectangle(xLoc, yLoc - 5, textBoxWidth, 50));
		textDisplay.setBorder(new TextBorder("Select buttons, then either change the properties, or draw shapes"));
		yLoc += 55;

		userInput.setBounds(new Rectangle(xLoc, yLoc - 5, textBoxWidth - optionButtonWidth, 30));
		userInput.setBorder(new SimpleBorder());
		userInput.addKeyListener(new UserInputKeyListener(this));
		JButton ok = new JButton("OK!");
		ok.setBorder(new SimpleBorder());
		ok.setBounds(new Rectangle((xLoc + textBoxWidth) - optionButtonWidth + 5, yLoc - 5, optionButtonWidth - 5, 30));
		ok.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				userInputResponse();
			}
		});
		yLoc += 35;
		textDisplay.setEditable(false);

		userInput.setFont(new Font("Arial", 1, 18));
		ok.setFont(new Font("Arial", 1, 18));

		this.add(textDisplay);
		this.add(userInput);
		this.add(ok);
	}

	public void changeBackgroundButtonResponse() {
		this.changeBackground = true;
		TextBorder t = (TextBorder) textDisplay.getBorder();
		t.setText(
				"Changing background color: Choose rgb color in the panel below: input 3 integers; each between 0 and 255 (space separated) for red, green, blue values. Click \"OK!\" when ready");
		userInput.requestFocus();
		textDisplay.repaint();
	}

	public void shapeColourButtonResponse() {
		this.shapeColour = true;
		TextBorder t = (TextBorder) textDisplay.getBorder();
		t.setText(
				"Changing outline color: Choose rgb color in the panel below: input 3 integers; each between 0 and 255 (space separated) for red, green, blue values. Click \"OK!\" when ready");
		userInput.requestFocus();
		textDisplay.repaint();
	}

	public void widthHeightButtonResponse() {
		this.widthHeight = true;
		this.changeWidth = true;
		this.changeHeight = true;
		TextBorder t = (TextBorder) textDisplay.getBorder();
		t.setText("Choose width: (enter an integer between 0 to 400) ");
		userInput.requestFocus();
		textDisplay.repaint();
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

	public void userInputResponse() {
		if (changeBackground) {
			drawShapes = false;
			changeBackground = false;
			changeBackground();
		} else if (shapeColour) {
			drawShapes = false;
			changeBackground = false;
			changeBackground();
		} else if (widthHeight) {
			setWidthHeight();
		} else if (drawShapes) {
			changeBackground = false;
			drawShapes();
		}
	}

	public void setWidthHeight() {
		if (!userInput.getText().equals("")) {
			try {
				int input = Integer.parseInt(userInput.getText());
				TextBorder t = (TextBorder) textDisplay.getBorder();
				if (input < 0 || input > 400) {
					if (changeWidth) {
						t.setText("Please enter an integer between 0 to 400. "
								+ "Choose width: (enter an integer between 0 to 400) ");
						textDisplay.repaint();
					} else {
						t.setText("Please enter an integer between 0 to 400. "
								+ "Choose height: (enter an integer between 0 to 400) ");
						textDisplay.repaint();
					}
					return;
				}
				if (changeWidth) {
					ShapeAbstract.setWidth(input);
					changeWidth = false;
					t.setText("Choose height: (enter an integer between 0 to 400) ");
					userInput.requestFocus();
					TextBorder text = (TextBorder) widthText.getBorder();
					text.setText("" + input);
					textDisplay.repaint();
					widthText.repaint();
				} else if (changeHeight) {
					ShapeAbstract.setHeight(input);
					changeHeight = false;
					t.setText("Width and height adjusted.");
					TextBorder text = (TextBorder) heightText.getBorder();
					text.setText("" + input);
					textDisplay.repaint();
					heightText.repaint();
					widthHeight = false;
				}
			} catch (NumberFormatException e) {
				TextBorder t = (TextBorder) textDisplay.getBorder();
				t.setText("You didn't enter an integer number!");
				textDisplay.repaint();
			}
		}
	}

	public void changeBackground() {
		TextBorder t = (TextBorder) textDisplay.getBorder();
		if (userInput.getText().equals("")) {
			t.setText("No numbers were entered! Try again.");
			textDisplay.repaint();
			return;
		}
		chooseBackgroundColour();
	}

	public void drawShapes() {
		TextBorder t = (TextBorder) textDisplay.getBorder();
		if (activated.size() > 0) {
			t.setText("How many " + activated.get(0).toLowerCase() + "s? ");
			userInput.requestFocus();
			textDisplay.repaint();
			if (!userInput.getText().equals("")) {
				try {
					int input = Integer.parseInt(userInput.getText());
					if (input > 100 || input < 0) {
						t.setText("Please enter a number that is 100 or less and 0 or greater");
						textDisplay.repaint();
						return;
					}
					// Success
					createShape(activated.get(0), input);
					activated.remove(activated.get(0));
					if (!activated.isEmpty()) {
						// Continue. Pressing the ok button restarts this method
						t.setText("How many " + activated.get(0).toLowerCase() + "s? ");
						textDisplay.repaint();
					} else {
						// End case
						t.setText("And..... Draw!");
						textDisplay.repaint();
						draw();
						drawShapes = false;
					}
				} catch (NumberFormatException e) {
					t.setText("You didn't enter an integer number!");
					textDisplay.repaint();
				}
			}
		}
	}

	public void createShape(String shapeName, int amount) {
		// Set the static ShapeAbstract variables
		ShapeAbstract.setCanvasSize(canvasSize);
		ShapeAbstract.setXCursor((int) canvasSize.getX() + 1);
		ShapeAbstract.setYCursor((int) canvasSize.getY() + 1);
		switch (shapeName) {
		case ("Circle"):
			Circle c = new Circle();
			c.setAmount(amount);
			shapes.add(c);
			break;
		case ("Ellipse"):
			Ellipse e = new Ellipse();
			e.setAmount(amount);
			shapes.add(e);
			break;
		case ("Hexagon"):
			Hexagon h = new Hexagon();
			h.setAmount(amount);
			shapes.add(h);
			break;
		case ("Lightning"):
			Lightning l = new Lightning();
			l.setAmount(amount);
			shapes.add(l);
			break;
		case ("Octagon"):
			Octagon o = new Octagon();
			o.setAmount(amount);
			shapes.add(o);
			break;
		case ("Rectangle"):
			shapes.Rectangle r = new shapes.Rectangle();
			r.setAmount(amount);
			shapes.add(r);
			break;
		case ("Square"):
			Square s = new Square();
			s.setAmount(amount);
			shapes.add(s);
			break;
		case ("5 Point Star"):
			Star5 star = new Star5();
			star.setAmount(amount);
			shapes.add(star);
			break;
		case ("6 Point Star"):
			Star6 st = new Star6();
			st.setAmount(amount);
			shapes.add(st);
			break;
		case ("Triangle"):
			Triangle t = new Triangle();
			t.setAmount(amount);
			shapes.add(t);
			break;
		default:
			throw new NoSuchElementException();
		}
	}

	public void draw() {
		for (Shape s : shapes) {
			s.drawShape(getGraphics(), outlineColor);
			ShapeAbstract.setXCursor((int) canvasSize.getX());
			ShapeAbstract.setYCursor((int) canvasSize.getY());
			ShapeAbstract.setAlternatingInt(0);
		}
		ShapeAbstract.setAlternatingInt(0);
		shapes = new ArrayList<Shape>();
	}

	public void chooseBackgroundColour() {
		TextBorder t = (TextBorder) textDisplay.getBorder();
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
					t.setText("The chosen " + currentColor + " value was out of range, please try again");
					textDisplay.repaint();
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
				t.setText("Not enough integers were entered, please try again");
				textDisplay.repaint();
				return;
			}
			// Check if we are changing the shape colour or background colour
			if (!localShapeColour) {
				// Background colour change
				Color change = new Color(canvasRed, canvasGreen, canvasBlue);
				Graphics g = this.getGraphics();
				g.setColor(change);
				g.fillRect(canvasSize.x, canvasSize.y, canvasSize.width, canvasSize.height);
				ColorBorder bord = (ColorBorder) changeBackgroundColour.getBorder();
				bord.setColor(change);
				changeBackgroundColour.repaint();
				t.setText("Background colour changed successfully");
				textDisplay.repaint();
			} else {
				Color change = new Color(canvasRed, canvasGreen, canvasBlue);
				outlineColor = change;
				ColorBorder bord = (ColorBorder) changeOutlineColour.getBorder();
				bord.setColor(change);
				changeOutlineColour.repaint();
				t.setText("Outline colour changed successfully");
				textDisplay.repaint();
			}
		} catch (NumberFormatException e) {
			// Error scenario 3
			t.setText("An integer number was not entered, please try again.");
			textDisplay.repaint();
			return;
		}
		sc.close();
	}
}
