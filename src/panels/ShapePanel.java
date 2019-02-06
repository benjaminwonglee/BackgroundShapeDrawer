package panels;

import borders.*;
import buttons.*;
import misc.ColorChooser;
import misc.UserInputKeyListener;
import output.PNGOutput;
import responses.AutoBackgroundColorResponse;
import responses.AutoShapeColor;
import responses.DrawShapesResponse;
import responses.DrawThemeToCanvasResponse;
import shapes.Shape;
import shapes.*;
import textboxes.ChangeBackgroundColor;
import textboxes.ChangeOutlineColor;
import textboxes.TextBox;
import themes.*;

import javax.swing.*;
import java.awt.Rectangle;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.*;

public class ShapePanel extends JPanel {
    private static final long serialVersionUID = -2760824343231275996L;

    private final static int BUTTON_WD = 180;
    private final static int BUTTON_HT = 60;

    // Buttons
    private List<JButton> buttonList;
    private JButton clearButton;

    // TextAreas
    private JTextArea textDisplay;
    private JTextField userInput;

    // Sizes
    private Rectangle canvasSize;
    private int optionButtonWidth;

    // Cursors
    private static int xLoc = 20;
    private static int yLoc = 20;

    // Three color values that represent the background color
    private int canvasRed = 0;
    private int canvasBlue = 0;
    private int canvasGreen = 0;
    // Three color values that represent the previous background color
    private int prevCanvasRed = 0;
    private int prevCanvasGreen = 0;
    private int prevCanvasBlue = 0;

    private boolean fill = false;

    private List<String> shapesToDraw;
    private List<Shape> shapes;
    private HashSet<Shape> allShapes;

    private Color outlineColor;

    // Button response booleans
    private boolean changeBackground = false;
    private boolean drawShapes = false;
    private boolean shapeColour = false;
    private boolean widthHeight = false;
    private boolean changeWidth = false;
    private boolean changeHeight = false;

    // GUI display fields
    private JTextArea changeBackgroundColor;
    private JTextArea changeOutlineColor;
    private JTextArea widthText;
    private JTextArea heightText;
    private JComboBox<String> patternSelector;

    // Theme Variables
    private String themeName = "gradient red blue";
    private JTextArea themeText;
    private Theme theme = new GradientRedBlue();
    private boolean themeDrawn = false;

    // Canvas variables
    private int optionButtonHeight;
    private JPanel canvas;
    private int space;
    private PNGOutput png;
    private ColorChooser backgroundColorChooser;

    /**
     * General constructor for the ShapePanel.
     */
    public ShapePanel() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.setBounds(new Rectangle(0, 0, screenSize.width, screenSize.height));
        this.setLayout(null); // Important for specifying own layout preferences
        textDisplay = new JTextArea();
        userInput = new JTextField();
        shapesToDraw = new ArrayList<>();
        shapes = new ArrayList<>();
        allShapes = new HashSet<>();
        outlineColor = new Color(200, 0, 0);
        backgroundColorChooser = new ColorChooser();
        createButtons();
        createTextAreas();
        defineCanvasBounds();
        canvas.setBackground(new Color(canvasRed, canvasGreen, canvasBlue));
    }

    @Override
    public boolean isOptimizedDrawingEnabled() {
        return false;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        setTheme(g);
        this.draw(getGraphics(), png.getPng().getGraphics());
    }

    /**
     * Calls all button creator methods to create the shape buttons and option
     * buttons.
     */
    public void createButtons() {
        buttonList = new ArrayList<JButton>();
        defineButtons();
        for (int i = 0; i < buttonList.size(); i++) {
            arrangeLayout(buttonList.get(i));
        }
        createOptionsButtons();
    }

    /**
     * Creates all the shape buttons.
     */
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

    /**
     * Sets the defaults for the shape buttons. This includes the size, the borders
     * and the design.
     *
     * @param shape The name of the shape to be put on the button
     * @return
     */
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

    /**
     * Automatically adjusts the layout for the shape buttons. Should be called on
     * all shape buttons in the buttonList.
     *
     * @param j The shape button to be arranged.
     */
    private void arrangeLayout(JButton j) {
        j.setBounds(new Rectangle(xLoc, yLoc, BUTTON_WD, BUTTON_HT));
        yLoc += (BUTTON_HT + 20);
        if (yLoc > BUTTON_HT * 6) {
            xLoc += (BUTTON_WD + 20);
            yLoc = 20;
        }
        this.add(j);
    }

    /**
     * Creates all the buttons that give options for drawing the shapes. This
     * includes the pattern selector.
     */
    private void createOptionsButtons() {
        Color optColor = new Color(230, 0, 0);
        space = 18;

        optionButtonWidth = BUTTON_WD * 2 + 20;
        optionButtonHeight = BUTTON_HT / 2 + 10;

        xLoc -= (BUTTON_WD + 20) * 2;
        yLoc = (int) ((this.getPreferredSize().getHeight() / 2)) + 20;

        // Add pattern selector to left side
        addPatternSelector();
        yLoc += 240;
        yLoc += optionButtonHeight + 9;
        addLoadFromFileButton(optColor);
        yLoc += optionButtonHeight + 9;
        addSaveToFileButton(optColor);

        yLoc += (120 - optionButtonHeight - 10);
        addThemeButton(optColor);
        moveXY();

        // Set cursor for row 1
        optionButtonWidth = ((this.getPreferredSize().width - xLoc - 70) / 4);
        optionButtonHeight = BUTTON_HT / 2 + 20;
        yLoc = 20;

        OptionButton.setOptionButtonWidth(optionButtonWidth);
        OptionButton.setOptionButtonHeight(optionButtonHeight);
        OptionButton.setOptColor(optColor);
        TextBox.setOptionButtonWidth(optionButtonWidth);
        TextBox.setOptionButtonHeight(optionButtonHeight);

        // Row 1
        // Add Change Background Button
        // TODO: Shift responses for these buttons
        this.add(new ChangeBackgroundButton(this, png, null, "Change Background"));
        yLoc += optionButtonHeight;
        changeBackgroundColor = new ChangeBackgroundColor(new Color(canvasRed, canvasGreen, canvasBlue));
        this.add(changeBackgroundColor);
        moveXY();

        // Add Choose Shape Colour Button
        // TODO: Shift responses for these buttons
        this.add(new ShapeColorButton(this, png, null, "Shape Colour"));
        yLoc += optionButtonHeight;
        changeOutlineColor = new ChangeOutlineColor(outlineColor);
        this.add(changeOutlineColor);
        moveXY();

        // Add Choose Set width & height Button
        // TODO: Shift responses for these buttons
        this.add(new WidthHeightButton(this, png, null, "Set Width & Height"));
        yLoc += optionButtonHeight;
        addWidthHeightText();
        moveXY();

        // Add Draw Shapes Button
        addDrawShapesButton(optColor, space);

        // Set cursor for row 2
        xLoc += (optionButtonWidth + space);
        xLoc -= (optionButtonWidth * 4) + (space * 4);
        yLoc += optionButtonHeight * 2 + space;

        // Row 2
        this.add(new AutoBackgroundColorButton(this, png, new AutoBackgroundColorResponse(), "Auto Background Color"));
        yLoc += optionButtonHeight;

        // Add Fill Button
        this.add(new FillButton(this, png, null, null));
        moveXY();

        this.add(new AutoShapeColorButton(this, png, new AutoShapeColor(), "Auto Shape Color"));
        yLoc += optionButtonHeight;
        this.add(new DrawThemeToCanvasButton(this, png, new DrawThemeToCanvasResponse(), "Draw Theme To Canvas"));
        moveXY();

        // Add clear drawing button
        addClearButton(optColor);

        xLoc -= (optionButtonWidth * 2) + (space * 2);
        yLoc += optionButtonHeight * 2 + space;
    }

    /**
     * Moves the cursor of x and y along by a designated amount. This is used for
     * adding option buttons.
     */
    private void moveXY() {
        xLoc += (optionButtonWidth + space);
        yLoc -= optionButtonHeight;
    }

    /**
     * Adds the text areas displaying the current width and height settings of the
     * shapes.
     */
    private void addWidthHeightText() {
        this.widthText = new JTextArea();
        widthText.setBounds(new Rectangle(xLoc, yLoc, optionButtonWidth / 2, optionButtonHeight));
        widthText.setBorder(new TextBorder("" + ShapeAbstract.getWidth()));
        this.heightText = new JTextArea();
        heightText.setBounds(
                new Rectangle(xLoc + optionButtonWidth / 2, yLoc, optionButtonWidth / 2, optionButtonHeight));
        heightText.setBorder(new TextBorder("" + ShapeAbstract.getHeight()));
        this.add(widthText);
        this.add(heightText);
    }

    /**
     * Adds the draw shapes button to the GUI.
     *
     * @param optColor The colour of the button.
     */
    private void addDrawShapesButton(Color optColor, int space) {

        JButton drawShapesButton = new JButton();
        drawShapesButton.setBounds(new Rectangle(xLoc, 20, optionButtonWidth, optionButtonHeight * 4 + space));
        drawShapesButton.setBorder(new OptionBorder("Draw Shapes", optColor));
        drawShapesButton.addActionListener(event -> new DrawShapesResponse());
        this.add(drawShapesButton);
    }

    /**
     * Adds the clear button to the GUI.
     *
     * @param optColour The colour of the button.
     */
    private void addClearButton(Color optColour) {

        this.clearButton = new JButton();
        clearButton.setBounds(new Rectangle(xLoc, yLoc, optionButtonWidth, optionButtonHeight * 2));
        clearButton.setBorder(new OptionBorder("Clear Drawing", optColour));
        clearButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                Graphics g = canvas.getGraphics();
                Graphics gr = png.getPng().getGraphics();
                g.setColor(new Color(canvasRed, canvasGreen, canvasBlue));
                gr.setColor(new Color(canvasRed, canvasGreen, canvasBlue));
                g.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
                gr.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
                TextBorder t = (TextBorder) textDisplay.getBorder();
                t.setText("Drawing Cleared");
                themeDrawn = false;

                shapes.clear();
                allShapes.clear();
                Shape.clearAllShapes();
                textDisplay.repaint();
            }
        });
        this.add(clearButton);
    }

    /**
     * Adds the pattern selector combo box. This is the fold down menu which has the
     * pattern names on it.
     */
    private void addPatternSelector() {
        JTextArea patternSelect = new JTextArea();
        patternSelect
                .setBounds(new Rectangle(xLoc, yLoc - BUTTON_HT * 2 + space, optionButtonWidth, optionButtonHeight));
        TextBorder patternBorder = new TextBorder("Select Pattern");
        patternBorder.setFont(new Font("Arial", 1, 18));
        patternSelect.setBorder(patternBorder);

        yLoc += optionButtonHeight;
        this.patternSelector = new JComboBox<String>();
        patternSelector
                .setBounds(new Rectangle(xLoc, yLoc - BUTTON_HT * 2 + space, optionButtonWidth, optionButtonHeight));
        patternSelector.setFont(new Font("Arial", 1, 16));
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
        this.add(patternSelect);
        this.add(patternSelector);
    }

    private void addLoadFromFileButton(Color optColor) {
        JButton load = new JButton();
        load.setBounds(
                new Rectangle(xLoc, yLoc - BUTTON_HT * 2 + space - 10, optionButtonWidth, optionButtonHeight + 10));
        load.setBorder(new OptionBorder("Load From Text File", optColor));
        load.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadFileButtonResponse();
            }
        });
        this.add(load);
    }

    /**
     * Responds to when the load from text file button is pressed. First clears the
     * canvas then draws the shapes from the text file onto the canvas.
     */
    public void loadFileButtonResponse() {
        JFileChooser chooser = new JFileChooser(System.getProperty("user.dir"));
        int option = chooser.showOpenDialog(new JDialog());
        if (option == JFileChooser.CANCEL_OPTION) {
            return;
        } else if (option == JFileChooser.APPROVE_OPTION) {
            File file = chooser.getSelectedFile();
            if (file == null || !chooser.getSelectedFile().getName().endsWith(".txt")) {
                TextBorder t = (TextBorder) textDisplay.getBorder();
                t.setText("Please select an appropriate .txt file to load from. Please try again.");
                textDisplay.repaint();
                return;
            }

            // Clears the canvas
            clearButton.doClick();
            try {
                png.loadFromTextFile(this, chooser.getSelectedFile().getName());
            } catch (FileNotFoundException e) {
                TextBorder t = (TextBorder) textDisplay.getBorder();
                t.setText("Please choose an existing .txt file.");
                textDisplay.repaint();
            }
            TextBorder t = (TextBorder) textDisplay.getBorder();
            t.setText("Loaded successfully.");
            textDisplay.repaint();
        }
    }

    private void addSaveToFileButton(Color optColor) {
        JButton save = new JButton();
        save.setBounds(
                new Rectangle(xLoc, yLoc - BUTTON_HT * 2 + space - 10, optionButtonWidth, optionButtonHeight + 10));
        save.setBorder(new OptionBorder("Save To PNG and Text File", optColor));
        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveFileAndPNGButtonResponse();
            }
        });
        this.add(save);
    }

    private void addThemeButton(Color optColour) {
        JButton themeButton = new JButton();
        themeButton.setBounds(
                new Rectangle(xLoc, yLoc - BUTTON_HT * 2 + space - 10, optionButtonWidth, optionButtonHeight + 10));
        themeButton.setBorder(new OptionBorder("Change Theme", optColour));
        yLoc += optionButtonHeight;

        themeText = new JTextArea(themeName);
        themeText.setBounds(new Rectangle(xLoc, yLoc - BUTTON_HT * 2 + space, optionButtonWidth, optionButtonHeight));
        TextBorder themeBord = new TextBorder(this.themeName.substring(0, 1).toUpperCase() + this.themeName.substring(1));
        themeBord.setFont(new Font("Arial", 1, 18));
        themeText.setBorder(themeBord);

        themeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Set theme to the NEXT one in the list
                switch (theme.name()) {
                    case ("blue lightning"):
                        themeName = "gold purple stars";
                        break;
                    case ("gold purple stars"):
                        themeName = "gradient blue red";
                        break;
                    case ("gradient blue red"):
                        themeName = "gradient red blue";
                        break;
                    case ("gradient red blue"):
                        themeName = "metal theme";
                        break;
                    case ("metal theme"):
                        themeName = "random dot";
                        break;
                    case ("random dot"):
                        themeName = "semi random dot";
                        break;
                    case ("semi random dot"):
                        themeName = "traffic light theme";
                        break;
                    case ("traffic light theme"):
                        themeName = "yellow diamonds";
                        break;
                    case ("yellow diamonds"):
                        themeName = "blue lightning";
                        break;
                    default:
                        themeName = "gradient red blue";
                        break;
                }
                repaint();
                TextBorder t = (TextBorder) themeText.getBorder();
                t.setText(themeName.substring(0, 1).toUpperCase() + themeName.substring(1));
                themeDrawn = false;
            }
        });
        this.add(themeButton);
        this.add(themeText);
    }

    /**
     * Made for the paintComponent method. Minimises work done upon repaint. Sets ct
     * (Current Theme) to the theme specified by the theme field string. Delegates
     * to a theme class to draw.
     *
     * @param g
     */
    public void setTheme(Graphics g) {
        this.theme = null;
        allShapes.clear();
        switch (themeName) {
            case ("blue lightning"):
                theme = new BlueLightning();
                break;
            case ("gold purple stars"):
                theme = new GoldPurpleStars();
                break;
            case ("gradient red blue"):
                theme = new GradientRedBlue();
                break;
            case ("gradient blue red"):
                theme = new GradientBlueRed();
                break;
            case ("metal theme"):
                theme = new MetalTheme();
                break;
            case ("random dot"):
                theme = new RandomDot();
                break;
            case ("semi random dot"):
                theme = new SemiRandomDot();
                break;
            case ("traffic light theme"):
                theme = new TrafficLightTheme();
                break;
            case ("yellow diamonds"):
                theme = new YellowDiamonds();
                break;
            default:
                theme = new RandomDot();
                break;
        }
        this.theme.setTheme(g, this);
        TextBorder t = (TextBorder) this.textDisplay.getBorder();
        t.setText("Select buttons, then either change the properties, or draw shapes");
        this.textDisplay.repaint();
    }

    /**
     * Creates the text areas for user notification and user input.
     */
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

    /**
     * Define the canvas bounds based on the current cursor x and y positions.
     */
    private void defineCanvasBounds() {
        canvasSize = new Rectangle(xLoc, yLoc, this.getPreferredSize().width - xLoc - 20,
                this.getPreferredSize().height - yLoc - 100);
        JPanel canvas = new JPanel();
        canvas.setBounds(new Rectangle(xLoc, yLoc, (int) canvasSize.getWidth(), (int) canvasSize.getHeight()));
        this.canvas = canvas;
        canvas.setIgnoreRepaint(true);
        RepaintManager.currentManager(this).markCompletelyClean(canvas);
        this.add(this.canvas);
        ((JComponent) this.getComponents()[this.getComponentCount() - 1]).setOpaque(true);
        setCanvasSizeVariables();
    }

    /**
     * Sets up all variables related to the canvas size once the final canvas size
     * has been set.
     */
    public void setCanvasSizeVariables() {
        /*
         * The following is to set appropriate width and height of a single shape. Since
         * height is shorter than width, it is multiplied by 2.
         */
        // Set the width and height to more exact values
        int w = 104;
        // Set the static ShapeAbstract variables
        ShapeAbstract.setCanvasSize(canvasSize);
        ShapeAbstract.setWidth(w);
        ShapeAbstract.setHeight(w);
        // Update the width height text boxes
        TextBorder text = (TextBorder) widthText.getBorder();
        text.setText("" + ShapeAbstract.getWidth());
        text = (TextBorder) heightText.getBorder();
        text.setText("" + ShapeAbstract.getHeight());
        widthText.repaint();
        heightText.repaint();
        this.png = new PNGOutput(canvasSize);

    }

    /**
     * This method responds to when the Change Background Button is clicked. It
     * updates the user notification box appropriately, and puts focus on the user
     * input text box underneath. Sets booleans for userInputResponse.
     */
    public void changeBackgroundButtonResponse() {
        this.changeBackground = true;
        TextBorder t = (TextBorder) textDisplay.getBorder();
        t.setText(
                "Changing background color: Choose rgb color in the panel below: input 3 integers; each between 0 and 255 (space separated) for red, green, blue values. Click \"OK!\" when ready");
        userInput.requestFocus();
        textDisplay.repaint();
    }

    /**
     * Responds to when the Shape Colour button is clicked. It updates the user
     * notification box appropriately, and puts focus on the user input text box
     * underneath. Sets booleans for userInputResponse.
     */
    public void shapeColourButtonResponse() {
        this.shapeColour = true;
        TextBorder t = (TextBorder) textDisplay.getBorder();
        t.setText(
                "Changing outline color: Choose rgb color in the panel below: input 3 integers; each between 0 and 255 (space separated) for red, green, blue values. Click \"OK!\" when ready");
        userInput.requestFocus();
        textDisplay.repaint();
    }

    /**
     * Responds to when the Set Width & height button is clicked. It updates the
     * user notification box appropriately, and puts focus on the user input text
     * box underneath. Sets booleans for userInputResponse.
     */
    public void widthHeightButtonResponse() {
        this.widthHeight = true;
        this.changeWidth = true;
        this.changeHeight = true;
        TextBorder t = (TextBorder) textDisplay.getBorder();
        t.setText("Choose width: (enter an integer between 0 to 400) ");
        userInput.requestFocus();
        textDisplay.repaint();
    }

    public void saveFileAndPNGButtonResponse() {
        JFileChooser chooser = new JFileChooser(System.getProperty("user.dir"));
        int option = chooser.showSaveDialog(new JDialog());
        if (option == JFileChooser.CANCEL_OPTION) {
            return;
        } else if (option == JFileChooser.APPROVE_OPTION) {
            File temp = chooser.getSelectedFile();
            File file = temp;
            // Save a file to the path
            PNGOutput.outputToFile(this, allShapes, new Color(canvasRed, canvasGreen, canvasBlue), file.getName() + ".txt");
            try {
                png.pngFromFile(file.getName() + ".txt", file.getName() + ".png");
            } catch (FileNotFoundException e) {
            }
        }
    }

    /**
     * Controls the booleans to flag when to handle user response and delegates the
     * work to other methods.
     */
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
        if (shapesToDraw.size() > 0) {
            t.setText("How many " + shapesToDraw.get(0).toLowerCase() + "s? ");
            userInput.requestFocus();
            textDisplay.repaint();
            if (!userInput.getText().equals("")) {
                try {
                    int input = Integer.parseInt(userInput.getText());
                    if (input > 1000 || input < 0) {
                        t.setText("Please enter a number that is between 0 and 1000 inclusive");
                        textDisplay.repaint();
                        return;
                    }

                    // Success
                    createShape(shapesToDraw.get(0), input);
                    shapesToDraw.remove(shapesToDraw.get(0));
                    if (!shapesToDraw.isEmpty()) {
                        // Continue. Pressing the ok button restarts this method
                        t.setText("How many " + shapesToDraw.get(0).toLowerCase() + "s? ");
                        textDisplay.repaint();
                    } else {
                        /* End case */
                        // Set the background of the png before drawing
                        Graphics g = png.getPng().getGraphics();
                        g.setColor(new Color(canvasRed, canvasGreen, canvasBlue));
                        g.fillRect(0, 0, png.getPng().getWidth(), png.getPng().getHeight());

                        // Draw the shapes
                        draw(canvas.getGraphics(), png.getPng().getGraphics());
                        createPNGFile(png);
                        shapes = new ArrayList<Shape>();
                        drawShapes = false;
                        t.setText("Drawn successfully");
                        textDisplay.repaint();
                    }
                } catch (NumberFormatException e) {
                    t.setText("You didn't enter an integer number!");
                    textDisplay.repaint();
                }
            }
        }
    }

    private void createPNGFile(PNGOutput png) {
        // For storing RGB values to a file
        PNGOutput.outputToFile(this, allShapes, new Color(canvasRed, canvasGreen, canvasBlue), "output.txt");
        try {
            png.pngFromFile("output.txt", "output.png");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * "shapeName" is the name on each button in the program. Format: First letter
     * always capitalised. Amount is the amount to draw.
     *
     * @param shapeName
     * @param amount
     */
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

    /**
     * Draws the shapes in the shapes ArrayList onto the canvas and on the PNGOutput
     * BufferedImage Object.
     *
     * @param g
     * @param pngGraphics
     */
    public void draw(Graphics g, Graphics pngGraphics) {

        for (int i = 0; i < shapes.size(); i++) {

            // Get the type of shape
            Shape shapeType = shapes.get(i);

            // Draw a certain amount of the type of shape
            shapeType.drawShape(g, pngGraphics, outlineColor, fill);
            ShapeAbstract.setXCursor(0);
            ShapeAbstract.setYCursor(0);
        }

        // Finished drawing. Reset variables
        allShapes.addAll(shapes);
        shapes.clear();
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
                ColorBorder colorLabel = (ColorBorder) changeBackgroundColor.getBorder();
                colorLabel.setColor(change);
                changeBackgroundColor.repaint();
                t.setText("Background colour changed successfully");
                textDisplay.repaint();
                themeDrawn = false;

            } else {
                outlineColor = change;
                ColorBorder colorLabel = (ColorBorder) changeOutlineColor.getBorder();
                colorLabel.setColor(change);
                resetPrevColors();
                changeOutlineColor.repaint();
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

    public void setBackgroundColor(int rgbBgc) {
        Color c = new Color(rgbBgc);
        canvasRed = c.getRed();
        canvasGreen = c.getGreen();
        canvasBlue = c.getBlue();
        canvas.setBackground(c);
        ColorBorder border = (ColorBorder) changeBackgroundColor.getBorder();
        border.setColor(c);
        changeBackgroundColor.repaint();
    }

    public void updateBackgroundColourTextArea(Color color) {
        ColorBorder border = (ColorBorder) changeBackgroundColor.getBorder();
        border.setColor(color);
        changeBackgroundColor.repaint();
        Graphics2D g2d = (Graphics2D) canvas.getGraphics().create();
        g2d.setPaint(color);
        g2d.fillRect(0, 0, canvas.getBounds().width, canvas.getBounds().height);
    }

    public void setBackgroundColor(Color bgc) {
        canvasRed = bgc.getRed();
        canvasGreen = bgc.getGreen();
        canvasBlue = bgc.getBlue();
    }

    public void setTheme(String theme) {
        if (theme.equals("none")) {
            return;
        }
        themeName = theme;
        TextBorder t = (TextBorder) themeText.getBorder();
        t.setText(theme.substring(0, 1).toUpperCase() + theme.substring(1));
        themeDrawn = true;
        setTheme(this.getGraphics());
    }

    public JPanel getCanvas() {
        return canvas;
    }

    public void setCanvas(JPanel canvas) {
        this.canvas = canvas;
    }

    public static int getXLoc() {
        return xLoc;
    }

    public static void setXLoc(int xLoc) {
        ShapePanel.xLoc = xLoc;
    }

    public static int getYLoc() {
        return yLoc;
    }

    public static void setYLoc(int yLoc) {
        ShapePanel.yLoc = yLoc;
    }

    public List<Shape> getShapes() {
        return shapes;
    }

    public void setShapes(List<Shape> shapes) {
        this.shapes = shapes;
    }

    public HashSet<Shape> getAllShapes() {
        return allShapes;
    }

    public void setAllShapes(HashSet<Shape> allShapes) {
        this.allShapes = allShapes;
    }

    public boolean getFill() {
        return fill;
    }

    public void setFill(boolean fill) {
        this.fill = fill;
    }

    public boolean isThemeDrawn() {
        return themeDrawn;
    }

    public void setThemeDrawn(boolean themeDrawn) {
        this.themeDrawn = themeDrawn;
    }

    public String getThemeName() {
        return themeName;
    }

    public void setThemeName(String themeName) {
        this.themeName = themeName;
    }

    public Theme getTheme() {
        return theme;
    }

    public void setTheme(Theme theme) {
        this.theme = theme;
    }

    public JTextArea getTextDisplay() {
        return textDisplay;
    }

    public void setTextDisplay(JTextArea textDisplay) {
        this.textDisplay = textDisplay;
    }

    public boolean isDrawShapes() {
        return drawShapes;
    }

    public void setDrawShapes(boolean drawShapes) {
        this.drawShapes = drawShapes;
    }

    public List<JButton> getButtonList() {
        return buttonList;
    }

    public void setButtonList(List<JButton> buttonList) {
        this.buttonList = buttonList;
    }

    public List<String> getShapesToDraw() {
        return shapesToDraw;
    }

    public void setShapesToDraw(List<String> shapesToDraw) {
        this.shapesToDraw = shapesToDraw;
    }

    public JTextField getUserInput() {
        return userInput;
    }

    public void setUserInput(JTextField userInput) {
        this.userInput = userInput;
    }

    public Color getOutlineColor() {
        return outlineColor;
    }

    public void setOutlineColor(Color outlineColor) {
        this.outlineColor = outlineColor;
    }

    public JTextArea getChangeBackgroundColor() {
        return changeBackgroundColor;
    }

    public void setChangeBackgroundColor(JTextArea changeBackgroundColor) {
        this.changeBackgroundColor = changeBackgroundColor;
    }

    public JTextArea getChangeOutlineColor() {
        return changeOutlineColor;
    }

    public void setChangeOutlineColor(JTextArea changeOutlineColor) {
        this.changeOutlineColor = changeOutlineColor;
    }

    public int getCanvasBlue() {
        return canvasBlue;
    }

    public void setCanvasBlue(int canvasBlue) {
        this.canvasBlue = canvasBlue;
    }

    public int getCanvasRed() {
        return canvasRed;
    }

    public void setCanvasRed(int canvasRed) {
        this.canvasRed = canvasRed;
    }

    public int getCanvasGreen() {
        return canvasGreen;
    }

    public void setCanvasGreen(int canvasGreen) {
        this.canvasGreen = canvasGreen;
    }

    public PNGOutput getPng() {
        return png;
    }

    public void setPng(PNGOutput png) {
        this.png = png;
    }
}
