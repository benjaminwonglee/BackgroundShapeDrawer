package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

import javax.imageio.ImageIO;
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
import shapes.Shape;
import shapes.ShapeAbstract;
import shapes.Square;
import shapes.Star5;
import shapes.Star6;
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
	private int prevCanvasRed = 0;
	private int prevCanvasGreen = 0;
	private int prevCanvasBlue = 0;

	private ArrayList<String> toDraw;
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
	private JPanel canvas;

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
		toDraw = new ArrayList<String>();
		shapes = new ArrayList<Shape>();
		outlineColor = new Color(200, 0, 0);
		createButtons();
		createTextAreas();
		defineCanvasBounds();
	}

	public void createButtons() {
		buttonList = new ArrayList<JButton>();
		defineButtons();
		for (JButton j : buttonList) {
			arrangeLayout(j);
		}
		createOptionsButtons();
	}

	private void defineButtons() {
		JButton circle = setButtonDefaults("Circle");
		JButton ellipse = setButtonDefaults("Ellipse");
		JButton hexagon = setButtonDefaults("Hexagon");
		JButton lightning = setButtonDefaults("Lightning");
		JButton octagon = setButtonDefaults("Octagon");
		JButton rectangle = setButtonDefaults("Rectangle");
		JButton square = setButtonDefaults("Square");
		JButton star5 = setButtonDefaults("5-Pointed Star");
		JButton star6 = setButtonDefaults("6-Pointed Star");
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
		JButton changeBackgroundButton = new JButton();
		changeBackgroundButton.setBounds(new Rectangle(xLoc, yLoc, optionButtonWidth, optionButtonHeight));
		changeBackgroundButton.setBorder(new OptionBorder("Change Background", optColour));
		changeBackgroundButton.addActionListener(new ActionListener() {
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
		JButton shapeColourButton = new JButton();
		shapeColourButton.setBounds(new Rectangle(xLoc, 20, optionButtonWidth, optionButtonHeight));
		shapeColourButton.setBorder(new OptionBorder("Shape Colour", optColour));
		shapeColourButton.addActionListener(new ActionListener() {
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
		JButton widthHeightButton = new JButton();
		widthHeightButton.setBounds(new Rectangle(xLoc, 20, optionButtonWidth, optionButtonHeight));
		widthHeightButton.setBorder(new OptionBorder("Set Width & Height", optColour));
		widthHeightButton.addActionListener(new ActionListener() {
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
		JButton drawShapesButton = new JButton();
		drawShapesButton.setBounds(new Rectangle(xLoc, 20, optionButtonWidth, optionButtonHeight * 4));
		drawShapesButton.setBorder(new OptionBorder("Draw Shapes", optColour));
		drawShapesButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				drawShapesButtonResponse();
			}
		});
		xLoc += (optionButtonWidth + space);

		this.add(changeBackgroundButton);
		this.add(changeBackgroundColour);
		this.add(shapeColourButton);
		this.add(changeOutlineColour);
		this.add(widthHeightButton);
		this.add(widthText);
		this.add(heightText);
		this.add(drawShapesButton);

		xLoc -= (optionButtonWidth * 4) + (space * 4);
		yLoc += optionButtonHeight * 2 + space;

		// Row 2
		// Add Fill Button
		JButton fillButton = new JButton();
		fillButton.setBounds(new Rectangle(xLoc, yLoc, optionButtonWidth, optionButtonHeight));
		fillButton.setBorder(new OptionBorder("Fill", optColour));

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
		fillButton.addActionListener(new ActionListener() {
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
		patternSelector.addItem("Bordering");
		patternSelector.addItem("Cross Alternating");
		patternSelector.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (patternSelector.getSelectedItem().equals("Random")) {
					ShapeAbstract.setPattern(ShapeAbstract.DrawPattern.RANDOM);
				} else if (patternSelector.getSelectedItem().equals("Aligned")) {
					ShapeAbstract.setPattern(ShapeAbstract.DrawPattern.ALIGNED);
				} else if (patternSelector.getSelectedItem().equals("Alternating")) {
					ShapeAbstract.setPattern(ShapeAbstract.DrawPattern.ALTERNATING);
				} else if (patternSelector.getSelectedItem().equals("Bordering")) {
					ShapeAbstract.setPattern(ShapeAbstract.DrawPattern.BORDERING);
				} else if (patternSelector.getSelectedItem().equals("Cross Alternating")) {
					ShapeAbstract.setPattern(ShapeAbstract.DrawPattern.CROSSALTERNATING);
				}
			}
		});

		xLoc += (optionButtonWidth + space);
		yLoc -= optionButtonHeight;

		JButton clear = new JButton();
		clear.setBounds(new Rectangle(xLoc, yLoc, optionButtonWidth, optionButtonHeight));
		clear.setBorder(new OptionBorder("Clear Drawing", optColour));
		clear.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Graphics g = canvas.getGraphics();
				g.setColor(new Color(canvasRed, canvasGreen, canvasBlue));
				g.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
				TextBorder t = (TextBorder) textDisplay.getBorder();
				t.setText("Drawing Cleared");
				textDisplay.repaint();
			}
		});

		this.add(fillButton);
		this.add(fillStatus);
		this.add(patternSelect);
		this.add(patternSelector);
		this.add(clear);

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

	private void defineCanvasBounds() {
		canvasSize = new Rectangle(xLoc, yLoc, this.getPreferredSize().width - xLoc - 20,
				this.getPreferredSize().height - yLoc - 100);
		// Set the width and height to more exact values
		int w = (int) canvasSize.getWidth() / 10 - 1;
		int h = (int) canvasSize.getHeight() / 10 - 1;
		ShapeAbstract.setWidth(w);
		ShapeAbstract.setHeight(h * 2);
		TextBorder text = (TextBorder) widthText.getBorder();
		text.setText("" + w);
		text = (TextBorder) heightText.getBorder();
		text.setText("" + h * 2);
		widthText.repaint();
		heightText.repaint();

		JPanel canvas = new JPanel();
		canvas.setBounds(new Rectangle(xLoc, yLoc, (int) canvasSize.getWidth(), (int) canvasSize.getHeight()));
		this.canvas = canvas;
		canvas.setBackground(new Color(canvasRed, canvasGreen, canvasBlue));
		this.add(canvas);

		// Set the static ShapeAbstract variables
		ShapeAbstract.setCanvasSize(canvasSize);
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
		this.toDraw = new ArrayList<String>();
		for (int i = 0; i < buttonList.size(); i++) {
			ActivateBorder border = (ActivateBorder) buttonList.get(i).getBorder();
			if (border.getActivated()) {
				String name = border.getLabel();
				toDraw.add(name);
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
		if (toDraw.size() > 0) {
			t.setText("How many " + toDraw.get(0).toLowerCase() + "s? ");
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
					createShape(toDraw.get(0), input);
					toDraw.remove(toDraw.get(0));
					if (!toDraw.isEmpty()) {
						// Continue. Pressing the ok button restarts this method
						t.setText("How many " + toDraw.get(0).toLowerCase() + "s? ");
						textDisplay.repaint();
					} else {
						// End case
						draw(canvas.getGraphics());
						PNGOutput png = new PNGOutput(canvasSize);
						createPNGFile(png);
						shapes = new ArrayList<Shape>();
						drawShapes = false;
					}
				} catch (NumberFormatException e) {
					t.setText("You didn't enter an integer number!");
					textDisplay.repaint();
				}
			}
		}
	}

	private void createPNGFile(PNGOutput png) {
		canvas.paint(png.getPng().getGraphics());
		draw(png.getPng().getGraphics());
		try {
			ImageIO.write(png.getPng(), "PNG", new File("output.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void createShape(String shapeName, int amount) {
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
		case ("5-Pointed Star"):
			Star5 star = new Star5();
			star.setAmount(amount);
			shapes.add(star);
			break;
		case ("6-Pointed Star"):
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

	public void draw(Graphics g) {
		for (Shape s : shapes) {
			s.drawShape(g, outlineColor);
			ShapeAbstract.setXCursor(0);
			ShapeAbstract.setYCursor(0);
			ShapeAbstract.setAlternatingInt(0);
			ShapeAbstract.setBorderingInt(0);
			ShapeAbstract.setCrossAlternatingInt(-1);
		}
		textDisplay.setText("And.... Draw!");
		textDisplay.repaint();

		// Finished drawing. Reset variables
		ShapeAbstract.setAlternatingInt(0);
		ShapeAbstract.setCrossAlternatingInt(0);
	}

	public void chooseBackgroundColour() {
		TextBorder t = (TextBorder) textDisplay.getBorder();
		boolean localShapeColour = shapeColour;
		shapeColour = false;

		Scanner sc = new Scanner(userInput.getText());
		prevCanvasRed = canvasRed;
		prevCanvasGreen = canvasGreen;
		prevCanvasBlue = canvasBlue;
		try {
			int x = 0;
			while (sc.hasNext() && x < 3) {
				x++;
				String newColor = "red";
				if (x == 2) {
					newColor = "green";
				} else if (x == 3) {
					newColor = "blue";
				}
				int color = Integer.parseInt(sc.next());
				// Error scenario 1
				if (color < 0 || color > 255) {
					sc.close();
					resetPrevColors();
					t.setText("The chosen " + newColor + " value was out of range, please try again");
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
				resetPrevColors();
				t.setText("Not enough integers were entered, please try again");
				textDisplay.repaint();
				return;
			}
			// Check if we are changing the shape colour or background colour
			Color change = new Color(canvasRed, canvasGreen, canvasBlue);
			if (!localShapeColour) {
				// Background colour change
				canvas.setBackground(change);
				ColorBorder colorLabel = (ColorBorder) changeBackgroundColour.getBorder();
				colorLabel.setColor(change);
				changeBackgroundColour.repaint();
				t.setText("Background colour changed successfully");
				textDisplay.repaint();
			} else {
				outlineColor = change;
				ColorBorder colorLabel = (ColorBorder) changeOutlineColour.getBorder();
				colorLabel.setColor(change);
				resetPrevColors();
				changeOutlineColour.repaint();
				t.setText("Outline colour changed successfully");
				textDisplay.repaint();
			}
		} catch (NumberFormatException e) {
			// Error scenario 3
			resetPrevColors();
			t.setText("An integer number was not entered, please try again.");
			textDisplay.repaint();
			return;
		}
		sc.close();
	}

	private void resetPrevColors() {
		canvasRed = prevCanvasRed;
		canvasGreen = prevCanvasGreen;
		canvasBlue = prevCanvasBlue;
	}

	public JPanel getCanvas() {
		return canvas;
	}
}
