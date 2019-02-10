package panels;

import borders.*;
import buttons.*;
import misc.UserInputKeyListener;
import output.PNGOutput;
import responses.*;
import shapes.Shape;
import shapes.*;
import textboxes.ColoredLabel;
import textboxes.TextBox;
import themes.*;

import javax.swing.*;
import java.awt.Rectangle;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

import static util.Utils.getScreenSize;

public class ShapePanel extends JPanel {
    private final static int BUTTON_WD = 180;
    private final static int BUTTON_HT = 60;

    // Buttons
    private List<JButton> shapeButtonList;
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
    private int canvasRedRGB = 0;
    private int canvasBlueRGB = 0;
    private int canvasGreenRGB = 0;

    private boolean toFill = false;

    private List<ShapeName> shapesToDraw;
    private List<Shape> shapes;
    private List<Shape> allShapes;

    private Color outlineColor;

    // Button response booleans
    private boolean toChangeBackground = false;
    private boolean toDrawShapes = false;
    private boolean toChangeShapeColor = false;
    private boolean toSetWidthHeight = false;
    private boolean toChangeWidth = false;
    private boolean toChangeHeight = false;

    // GUI display fields
    private JTextArea changeBackgroundPanelWrapper;
    private JTextArea changeOutlinePanelWrapper;
    private JTextArea widthText;
    private JTextArea heightText;
    private JComboBox<String> patternSelector;

    // Theme Variables
    private JTextArea themeText;
    private Theme theme = new GradientRedBlue();
    private boolean themeDrawn = false;

    // Canvas variables
    private int optionButtonHeight;
    private JPanel canvas;
    private int space;
    private PNGOutput png;

    /**
     * General constructor for the ShapePanel.
     */
    public ShapePanel() {
        // Set the size of the panel to the screen size
        Dimension screenSize = getScreenSize();
        this.setBounds(new Rectangle(0, 0, screenSize.width, screenSize.height));

        // Setting layout to null is important for specifying specific tailored layout preferences in Swing
        this.setLayout(null);

        // Initialise fields
        textDisplay = new JTextArea();
        userInput = new JTextField();
        shapesToDraw = new ArrayList<>();
        shapes = new ArrayList<>();
        allShapes = new ArrayList<>();
        outlineColor = new Color(200, 0, 0);

        // Initialise swing components
        createButtons();
        createTextAreas();
        defineCanvasBounds();

        canvas.setBackground(new Color(canvasRedRGB, canvasGreenRGB, canvasBlueRGB));
    }

    @Override
    public boolean isOptimizedDrawingEnabled() {
        return false;
    }

    public void writeToTextBoxAndRepaint(JTextArea textDisplay, String message) {
        TextBorder textDisplayWrapper = (TextBorder) textDisplay.getBorder();
        textDisplayWrapper.setText(message);
        textDisplay.repaint();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        applyTheme(g);
        this.draw(getGraphics(), png.getPng().getGraphics());
    }

    /**
     * Calls all button creator methods to create the shape buttons and option
     * buttons.
     */
    private void createButtons() {
        shapeButtonList = new ArrayList<>();
        defineShapeButtons();
        for (JButton shapeButton : shapeButtonList) {
            arrangeShapeButtonLayout(shapeButton);
        }
        createOptionsButtons();
    }

    /**
     * Creates all the shape buttons.
     */
    private void defineShapeButtons() {
        for (ShapeName shapeName : ShapeName.values()) {
            JButton shapeButton = setButtonDefaults(shapeName.getShapeName());
            shapeButtonList.add(shapeButton);
        }
    }

    /**
     * Creates a button with the defaults for a shape button. This includes the size, the borders and the design.
     *
     * @param shapeName The name of the shape to be put on the button
     * @return A button with the default button settings set
     */
    private JButton setButtonDefaults(String shapeName) {
        JButton button = new JButton();
        button.setPreferredSize(new Dimension(BUTTON_WD, BUTTON_HT));
        ActivateBorder onOffBorder = new ActivateBorder(shapeName);
        button.setBorder(onOffBorder);
        button.addActionListener(event -> onOffBorder.setActivated(!onOffBorder.getActivated()));
        return button;
    }

    /**
     * Automatically adjusts the layout for the shape buttons. Should be called on all shape buttons in the shape button list.
     *
     * @param shapeButton The shape button to be arranged.
     */
    private void arrangeShapeButtonLayout(JButton shapeButton) {
        shapeButton.setBounds(new Rectangle(xLoc, yLoc, BUTTON_WD, BUTTON_HT));
        yLoc += (BUTTON_HT + 20);
        if (yLoc > BUTTON_HT * 6) {
            xLoc += (BUTTON_WD + 20);
            yLoc = 20;
        }
        this.add(shapeButton);
    }

    /**
     * Creates all the buttons that give options for drawing the shapes. This includes the pattern selector.
     */
    private void createOptionsButtons() {
        OptionButton.setOptColor(new Color(230, 0, 0));
        space = 18;

        optionButtonWidth = BUTTON_WD * 2 + 20;
        optionButtonHeight = BUTTON_HT / 2 + 10;

        xLoc -= (BUTTON_WD + 20) * 2;
        yLoc = (int) ((this.getPreferredSize().getHeight() / 2)) + 20;

        // Add pattern selector to left side
        addPatternSelector();
        yLoc += 240;
        yLoc += optionButtonHeight + 9;
        addLoadFromFileButton();
        yLoc += optionButtonHeight + 9;
        addSaveToFileButton();

        yLoc += (120 - optionButtonHeight - 10);
        addThemeButton();
        moveXY();

        // Set cursor for row 1
        optionButtonWidth = ((this.getPreferredSize().width - xLoc - 70) / 4);
        optionButtonHeight = BUTTON_HT / 2 + 20;
        yLoc = 20;

        OptionButton.setOptionButtonWidth(optionButtonWidth);
        OptionButton.setOptionButtonHeight(optionButtonHeight);
        TextBox.setOptionButtonWidth(optionButtonWidth);
        TextBox.setOptionButtonHeight(optionButtonHeight);

        // Row 1
        // Add Change Background Button
        this.add(new ChangeBackgroundButton(this, png, new ChangeBackgroundResponse()));
        yLoc += optionButtonHeight;
        changeBackgroundPanelWrapper = new ColoredLabel(new Color(canvasRedRGB, canvasGreenRGB, canvasBlueRGB));
        this.add(changeBackgroundPanelWrapper);
        moveXY();

        // Add Choose Shape Colour Button
        this.add(new ShapeColorButton(this, png, new ChangeShapeColorResponse()));
        yLoc += optionButtonHeight;
        changeOutlinePanelWrapper = new ColoredLabel(outlineColor);
        this.add(changeOutlinePanelWrapper);
        moveXY();

        // Add Choose Set width & height Button
        this.add(new WidthHeightButton(this, png, new WidthHeightResponse()));
        yLoc += optionButtonHeight;
        addWidthHeightText();
        moveXY();

        // Add Draw Shapes Button
        addDrawShapesButton(space);

        // Set cursor for row 2
        xLoc += (optionButtonWidth + space);
        xLoc -= (optionButtonWidth * 4) + (space * 4);
        yLoc += optionButtonHeight * 2 + space;

        // Row 2
        this.add(new AutoBackgroundColorButton(this, png, new AutoBackgroundColorResponse()));
        yLoc += optionButtonHeight;

        // Add Fill Button
        this.add(new FillButton(this, png, null));
        moveXY();

        this.add(new AutoShapeColorButton(this, png, new AutoShapeColor()));
        yLoc += optionButtonHeight;
        this.add(new DrawThemeToCanvasButton(this, png, new DrawThemeToCanvasResponse()));
        moveXY();

        // Add clear drawing button
        addClearButton(OptionButton.getOptColor());

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
     */
    private void addDrawShapesButton(int space) {

        JButton drawShapesButton = new DrawShapesButton(this, png, new DrawShapesResponse());
        drawShapesButton.setBounds(new Rectangle(xLoc, 20, optionButtonWidth, optionButtonHeight * 4 + space));
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
        clearButton.addActionListener(event -> {
            Graphics canvasGraphics = canvas.getGraphics();
            Graphics pngGraphics = png.getPng().getGraphics();
            canvasGraphics.setColor(new Color(canvasRedRGB, canvasGreenRGB, canvasBlueRGB));
            pngGraphics.setColor(new Color(canvasRedRGB, canvasGreenRGB, canvasBlueRGB));
            canvasGraphics.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
            pngGraphics.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
            writeToTextBoxAndRepaint(textDisplay, "Drawing Cleared");

            themeDrawn = false;
            shapes.clear();
            allShapes.clear();
            Shape.clearAllShapes();
        });
        this.add(clearButton);
    }

    /**
     * Adds the pattern selector combo box. This is the fold down menu which has the pattern names on it.
     */
    private void addPatternSelector() {
        addPatternSelectPrompt();
        addPatternSelectDropdown();
    }

    private void addPatternSelectPrompt() {
        JTextArea patternSelect = new JTextArea();
        patternSelect
                .setBounds(new Rectangle(xLoc, yLoc - BUTTON_HT * 2 + space, optionButtonWidth, optionButtonHeight));
        TextBorder patternBorder = new TextBorder("Select Pattern");
        patternBorder.setFont(new Font("Arial", Font.BOLD, 18));
        patternSelect.setBorder(patternBorder);
        this.add(patternSelect);
    }

    private void addPatternSelectDropdown() {
        yLoc += optionButtonHeight;
        this.patternSelector = new JComboBox<>();
        patternSelector
                .setBounds(new Rectangle(xLoc, yLoc - BUTTON_HT * 2 + space, optionButtonWidth, optionButtonHeight));
        patternSelector.setFont(new Font("Arial", Font.BOLD, 16));
        patternSelector.addItem("Random");
        patternSelector.addItem("Aligned");
        patternSelector.addItem("Alternating");
        patternSelector.addItem("Bordering");
        patternSelector.addItem("Cross Alternating");
        patternSelector.addActionListener(event -> {
            if (patternSelector.getSelectedItem() != null) {
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
        this.add(patternSelector);
    }

    /**
     * Define and add the load button to the main panel
     */
    private void addLoadFromFileButton() {
        JButton load = new LoadButton(this, png, new LoadFileResponse());
        load.setBounds(
                new Rectangle(xLoc, yLoc - BUTTON_HT * 2 + space - 10, optionButtonWidth, optionButtonHeight + 10));
        this.add(load);
    }

    /**
     * Define and add the save button to the main panel
     */
    private void addSaveToFileButton() {
        JButton save = new SaveButton(this, png, new SaveFileAndPNGResponse());
        save.setBounds(
                new Rectangle(xLoc, yLoc - BUTTON_HT * 2 + space - 10, optionButtonWidth, optionButtonHeight + 10));
        this.add(save);
    }

    /**
     * Define and add the theme button to the main panel
     */
    private void addThemeButton() {
        JButton changeThemeButton = new ChangeThemeButton(this, png, new ChangeThemeResponse());
        changeThemeButton.setBounds(
                new Rectangle(xLoc, yLoc - BUTTON_HT * 2 + space - 10, optionButtonWidth, optionButtonHeight + 10));
        yLoc += optionButtonHeight;

        String themeName = theme.getName();
        themeText = new JTextArea(themeName);
        themeText.setBounds(new Rectangle(xLoc, yLoc - BUTTON_HT * 2 + space, optionButtonWidth, optionButtonHeight));
        TextBorder themeBorder = new TextBorder(themeName.substring(0, 1).toUpperCase() + themeName.substring(1));
        themeBorder.setFont(new Font("Arial", Font.BOLD, 18));
        themeText.setBorder(themeBorder);
        this.add(changeThemeButton);
        this.add(themeText);
    }

    /**
     * Made for the paintComponent method. Minimises work done upon repaint. Sets ct
     * (Current Theme) to the theme specified by the theme field string. Delegates
     * to a theme class to draw.
     *
     * @param g The graphics object to draw the theme with
     */
    public void applyTheme(Graphics g) {
        allShapes.clear();
        switch (theme.getThemeName()) {
            case BLUE_LIGHTNING:
                theme = new BlueLightning();
                break;
            case GOLD_PURPLE_STARS:
                theme = new GoldPurpleStars();
                break;
            case GRADIENT_RED_BLUE:
                theme = new GradientRedBlue();
                break;
            case GRADIENT_BLUE_RED:
                theme = new GradientBlueRed();
                break;
            case METAL_THEME:
                theme = new MetalTheme();
                break;
            case RANDOM_DOT:
                theme = new RandomDot();
                break;
            case SEMI_RANDOM_DOT:
                theme = new SemiRandomDot();
                break;
            case TRAFFIC_LIGHT:
                theme = new TrafficLight();
                break;
            case YELLOW_DIAMONDS:
                theme = new YellowDiamonds();
                break;
            default:
                throw new NoSuchElementException(String.format(
                        "The chosen theme %s could not be found while setting theme in ShapePanel",
                        theme == null ? theme : theme.getThemeName().name()));
        }
        this.theme.applyTheme(g, this);
        writeToTextBoxAndRepaint(textDisplay, "Select buttons, then either change the properties, or draw shapes");
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
        ok.addActionListener(event -> userInputResponse());
        yLoc += 35;
        textDisplay.setEditable(false);

        userInput.setFont(new Font("Arial", Font.BOLD, 18));
        ok.setFont(new Font("Arial", Font.BOLD, 18));

        this.add(textDisplay);
        this.add(userInput);
        this.add(ok);
    }

    /**
     * Define the canvas bounds based on the current cursor x and y positions.
     */
    private void defineCanvasBounds() {
        // Define the size of the canvas
        Dimension screenSize = getScreenSize();
        int canvasWidth = screenSize.width / 2;
        int canvasHeight = screenSize.height / 2;
        int spaceWidth = ((screenSize.width - xLoc) - canvasWidth) / 2;
        int spaceHeight = ((screenSize.height - yLoc) - canvasHeight) / 2;
        canvasSize = new Rectangle(xLoc + spaceWidth, yLoc + spaceHeight, canvasWidth, canvasHeight);

        // Set the canvas properties
        this.canvas = new JPanel();
        canvas.setBounds(canvasSize);
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
        int divisor = 15;
        int w = canvasSize.width / divisor - (divisor / 2);
        // Set the static ShapeAbstract variables
        ShapeAbstract.setCanvasSize(canvasSize);
        ShapeAbstract.setWidth(w);
        ShapeAbstract.setHeight(w);
        // Update the width height text boxes
        TextBorder text = (TextBorder) widthText.getBorder();
        text.setText(String.valueOf(ShapeAbstract.getWidth()));
        text = (TextBorder) heightText.getBorder();
        text.setText(String.valueOf(ShapeAbstract.getHeight()));
        widthText.repaint();
        heightText.repaint();
        this.png = new PNGOutput(canvasSize);
    }

    /**
     * Controls the booleans to flag when to handle user response and delegates the
     * work to other methods.
     */
    public void userInputResponse() {
        if (toChangeBackground) {
            toDrawShapes = false;
            toChangeBackground = false;
            askUserForColorInput();
        } else if (toChangeShapeColor) {
            toDrawShapes = false;
            askUserForColorInput();
        } else if (toSetWidthHeight) {
            setWidthAndHeight();
        } else if (toDrawShapes) {
            drawShapes();
        }
    }

    /**
     * Execute the sequence of prompts to the user to take user input to set the width and height of shapes.
     */
    public void setWidthAndHeight() {
        if (!userInput.getText().equals("")) {
            try {
                int input = Integer.parseInt(userInput.getText());
                if (input < 0 || input > 400) {
                    if (toChangeWidth) {
                        writeToTextBoxAndRepaint(textDisplay,
                                "Please enter an integer between 0 to 400. "
                                        + "Choose width: (enter an integer between 0 to 400) ");
                    } else {
                        writeToTextBoxAndRepaint(textDisplay,
                                "Please enter an integer between 0 to 400. "
                                        + "Choose height: (enter an integer between 0 to 400) ");
                    }
                    return;
                }
                if (toChangeWidth) {
                    ShapeAbstract.setWidth(input);
                    toChangeWidth = false;
                    writeToTextBoxAndRepaint(textDisplay, "Choose height: (enter an integer between 0 to 400) ");
                    userInput.requestFocus();

                    TextBorder text = (TextBorder) widthText.getBorder();
                    text.setText("" + input);
                    widthText.repaint();
                } else if (toChangeHeight) {
                    ShapeAbstract.setHeight(input);
                    toChangeHeight = false;
                    writeToTextBoxAndRepaint(textDisplay, "Width and height adjusted.");

                    TextBorder text = (TextBorder) heightText.getBorder();
                    text.setText("" + input);
                    heightText.repaint();
                    toSetWidthHeight = false;
                }
            } catch (NumberFormatException e) {
                writeToTextBoxAndRepaint(textDisplay, "You didn't enter an integer number!");
            }
        }
    }

    /**
     * Execute the sequence of prompts when drawing shapes including asking which type of shape and the amount of shapes desired.
     */
    public void drawShapes() {

        if (shapesToDraw.size() > 0) {
            writeToTextBoxAndRepaint(textDisplay,
                    "How many " + shapesToDraw.get(0).getShapeName() + "s? ");
            userInput.requestFocus();
            if (!userInput.getText().equals("")) {
                try {
                    int input = Integer.parseInt(userInput.getText());
                    if (input > 1000 || input < 0) {
                        writeToTextBoxAndRepaint(textDisplay, "Please enter a number that is between 0 and 1000 inclusive");
                        return;
                    }

                    // Success
                    createShape(shapesToDraw.get(0), input);
                    shapesToDraw.remove(shapesToDraw.get(0));
                    if (!shapesToDraw.isEmpty()) {
                        // Continue. Pressing the ok button restarts this method
                        writeToTextBoxAndRepaint(textDisplay,
                                "How many " + shapesToDraw.get(0).name().toLowerCase() + "s? ");
                    } else {
                        /* End case */
                        // Set the background of the png before drawing
                        Graphics g = png.getPng().getGraphics();
                        g.setColor(new Color(canvasRedRGB, canvasGreenRGB, canvasBlueRGB));
                        g.fillRect(0, 0, png.getPng().getWidth(), png.getPng().getHeight());

                        // Draw the shapes
                        draw(canvas.getGraphics(), png.getPng().getGraphics());
                        createPNGFile(png);
                        shapes = new ArrayList<>();
                        toDrawShapes = false;
                        writeToTextBoxAndRepaint(textDisplay, "Drawn successfully");
                    }
                } catch (NumberFormatException e) {
                    writeToTextBoxAndRepaint(textDisplay, "You didn't enter an integer number!");
                }
            }
        }
    }

    /**
     * Create a .png file from the current image on the panel canvas
     *
     * @param png The object to handle the image output
     */
    private void createPNGFile(PNGOutput png) {
        // For storing RGB values to a file
        PNGOutput.outputToFile(this, new Color(canvasRedRGB, canvasGreenRGB, canvasBlueRGB), "output.txt");
        png.pngFromFile(this, "output.txt", "output.png");
    }

    /**
     * "shapeName" is the name on each button in the program. Format: First letter
     * always capitalised. Amount is the amount to draw.
     *
     * @param shapeName The name of the shape to be drawn
     * @param amount    The amount of the shape to draw
     */
    public void createShape(ShapeName shapeName, int amount) {
        switch (shapeName) {
            case CIRCLE:
                Circle c = new Circle();
                c.setAmount(amount);
                shapes.add(c);
                break;
            case ELLIPSE:
                Ellipse e = new Ellipse();
                e.setAmount(amount);
                shapes.add(e);
                break;
            case HEXAGON:
                Hexagon h = new Hexagon();
                h.setAmount(amount);
                shapes.add(h);
                break;
            case LIGHTNING:
                Lightning l = new Lightning();
                l.setAmount(amount);
                shapes.add(l);
                break;
            case OCTAGON:
                Octagon o = new Octagon();
                o.setAmount(amount);
                shapes.add(o);
                break;
            case RECTANGLE:
                shapes.Rectangle r = new shapes.Rectangle();
                r.setAmount(amount);
                shapes.add(r);
                break;
            case SQUARE:
                Square s = new Square();
                s.setAmount(amount);
                shapes.add(s);
                break;
            case STAR5:
                Star5 star = new Star5();
                star.setAmount(amount);
                shapes.add(star);
                break;
            case STAR6:
                Star6 st = new Star6();
                st.setAmount(amount);
                shapes.add(st);
                break;
            case TRIANGLE:
                Triangle t = new Triangle();
                t.setAmount(amount);
                shapes.add(t);
                break;
        }
    }

    /**
     * Draws the shapes in the shapes ArrayList onto the canvas and on the PNGOutput
     * BufferedImage Object.
     *
     * @param g           The graphics object of the canvas to draw with
     * @param pngGraphics The graphics object of the png image to draw with
     */
    public void draw(Graphics g, Graphics pngGraphics) {

        for (Shape shapeType : shapes) {
            // Get the type of shape and draw a certain amount of the type of shape
            shapeType.drawShape(g, pngGraphics, outlineColor, toFill);
            ShapeAbstract.setXCursor(0);
            ShapeAbstract.setYCursor(0);
        }

        // Finished drawing. Reset variables
        allShapes.addAll(shapes);
        shapes.clear();
    }

    /**
     * Execute the sequence of prompts when requiring a color input from the user. Handles various scenarios including
     * if integers weren't entered, if the color values are outside of the RGB range, and if the format is incorrect
     * ie. not enough values parsed in.
     */
    public void askUserForColorInput() {
        if (userInput.getText().equals("")) {
            writeToTextBoxAndRepaint(textDisplay, "No numbers were entered! Try again.");
            return;
        }

        Scanner sc = new Scanner(userInput.getText());
        int[] colors = new int[3];
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
                    writeToTextBoxAndRepaint(textDisplay,
                            "The chosen " + newColor + " value was out of range, please try again");
                    return;
                }
                colors[x - 1] = color;
            }
            // Error scenario 2
            if (x != 3) {
                sc.close();
                writeToTextBoxAndRepaint(textDisplay, "Not enough integers were entered, please try again");
                return;
            }
            canvasRedRGB = colors[0];
            canvasGreenRGB = colors[1];
            canvasBlueRGB = colors[2];

            // Check if we are changing the shape colour or background colour
            Color change = new Color(canvasRedRGB, canvasGreenRGB, canvasBlueRGB);
            if (!toChangeShapeColor) {
                changeBackgroundColor(change);
            } else {
                toChangeShapeColor = false;
                shapeOutlineColorChange(change);
            }
        } catch (NumberFormatException e) {
            // Error scenario 3
            writeToTextBoxAndRepaint(textDisplay, "An integer number was not entered, please try again.");
            return;
        }
        sc.close();
    }

    /**
     * Change the background color and inform the user that the background has been changed
     *
     * @param color The color to change the background to
     */
    private void changeBackgroundColor(Color color) {
        // Background color change
        canvas.setBackground(color);
        ColorBorder colorLabel = (ColorBorder) changeBackgroundPanelWrapper.getBorder();
        colorLabel.setColor(color);
        changeBackgroundPanelWrapper.repaint();
        writeToTextBoxAndRepaint(textDisplay, "Background colour changed successfully");
        themeDrawn = false;
    }

    /**
     * Change the shape outline color and inform the user that the background has been changed
     *
     * @param color The color to change the shape outline to
     */
    private void shapeOutlineColorChange(Color color) {
        // Shape outline color change
        outlineColor = color;
        ColorBorder colorLabel = (ColorBorder) changeOutlinePanelWrapper.getBorder();
        colorLabel.setColor(color);
        changeOutlinePanelWrapper.repaint();
        writeToTextBoxAndRepaint(textDisplay, "Outline colour changed successfully");
    }

    /**
     * Set a theme of the panel from the name of the theme.
     *
     * @param themeName The name of the theme
     */
    public void setThemeFromName(String themeName) {
        if (themeName.equals("none")) {
            return;
        }
        TextBorder t = (TextBorder) themeText.getBorder();
        t.setText(themeName.substring(0, 1).toUpperCase() + themeName.substring(1));
        themeDrawn = true;
        applyTheme(this.getGraphics());
    }

    public List<Shape> getShapes() {
        return shapes;
    }

    public void setShapes(List<Shape> shapes) {
        this.shapes = shapes;
    }

    public List<Shape> getAllShapes() {
        return allShapes;
    }

    public void setAllShapes(List<Shape> allShapes) {
        this.allShapes = allShapes;
    }

    public JPanel getCanvas() {
        return canvas;
    }

    public void setCanvas(JPanel canvas) {
        this.canvas = canvas;
    }

    public JTextArea getTextDisplay() {
        return textDisplay;
    }

    public void setTextDisplay(JTextArea textDisplay) {
        this.textDisplay = textDisplay;
    }

    public PNGOutput getPng() {
        return png;
    }

    public void setPng(PNGOutput png) {
        this.png = png;
    }

    public boolean isToChangeBackground() {
        return toChangeBackground;
    }

    public void setToChangeBackground(boolean toChangeBackground) {
        this.toChangeBackground = toChangeBackground;
    }

    public boolean isToChangeShapeColor() {
        return toChangeShapeColor;
    }

    public void setToChangeShapeColor(boolean toChangeShapeColor) {
        this.toChangeShapeColor = toChangeShapeColor;
    }

    public List<JButton> getShapeButtonList() {
        return shapeButtonList;
    }

    public void setShapeButtonList(List<JButton> shapeButtonList) {
        this.shapeButtonList = shapeButtonList;
    }

    public List<ShapeName> getShapesToDraw() {
        return shapesToDraw;
    }

    public void setShapesToDraw(List<ShapeName> shapesToDraw) {
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

    public JTextArea getChangeBackgroundPanelWrapper() {
        return changeBackgroundPanelWrapper;
    }

    public void setChangeBackgroundPanelWrapper(JTextArea changeBackgroundPanelWrapper) {
        this.changeBackgroundPanelWrapper = changeBackgroundPanelWrapper;
    }

    public JTextArea getChangeOutlinePanelWrapper() {
        return changeOutlinePanelWrapper;
    }

    public void setChangeOutlinePanelWrapper(JTextArea changeOutlinePanelWrapper) {
        this.changeOutlinePanelWrapper = changeOutlinePanelWrapper;
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

    public boolean isToDrawShapes() {
        return toDrawShapes;
    }

    public void setToDrawShapes(boolean toDrawShapes) {
        this.toDrawShapes = toDrawShapes;
    }

    public boolean isToSetWidthHeight() {
        return toSetWidthHeight;
    }

    public void setToSetWidthHeight(boolean toSetWidthHeight) {
        this.toSetWidthHeight = toSetWidthHeight;
    }

    public boolean isToChangeWidth() {
        return toChangeWidth;
    }

    public void setToChangeWidth(boolean toChangeWidth) {
        this.toChangeWidth = toChangeWidth;
    }

    public boolean isToChangeHeight() {
        return toChangeHeight;
    }

    public void setToChangeHeight(boolean toChangeHeight) {
        this.toChangeHeight = toChangeHeight;
    }

    public boolean isToFill() {
        return toFill;
    }

    public void setToFill(boolean toFill) {
        this.toFill = toFill;
    }

    public boolean isThemeDrawn() {
        return themeDrawn;
    }

    public void setThemeDrawn(boolean themeDrawn) {
        this.themeDrawn = themeDrawn;
    }

    public Theme getTheme() {
        return theme;
    }

    public void setTheme(Theme theme) {
        this.theme = theme;
    }

    public int getCanvasBlueRGB() {
        return canvasBlueRGB;
    }

    public void setCanvasBlueRGB(int canvasBlueRGB) {
        this.canvasBlueRGB = canvasBlueRGB;
    }

    public int getCanvasRedRGB() {
        return canvasRedRGB;
    }

    public void setCanvasRedRGB(int canvasRedRGB) {
        this.canvasRedRGB = canvasRedRGB;
    }

    public int getCanvasGreenRGB() {
        return canvasGreenRGB;
    }

    public void setCanvasGreenRGB(int canvasGreenRGB) {
        this.canvasGreenRGB = canvasGreenRGB;
    }

    public JButton getClearButton() {
        return clearButton;
    }

    public JTextArea getThemeText() {
        return themeText;
    }
}

