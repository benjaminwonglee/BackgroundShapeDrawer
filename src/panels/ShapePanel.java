package panels;

import borders.*;
import buttons.*;
import misc.FillStatus;
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

import static util.Utils.getScreenSize;

public class ShapePanel extends JPanel {

    private static final int BUTTON_WD = 180;
    private static final int BUTTON_HT = 60;

    // Cursors
    private static int xLoc = 20;
    private static int yLoc = 20;

    // Buttons
    private List<JButton> shapeButtonList;
    private JButton clearButton;

    // TextAreas
    private JTextArea textDisplay;
    private JTextField userInput;

    // Sizes
    private Rectangle canvasSize;
    private int optionButtonWidth;

    private List<ShapeName> shapesToDraw;
    private List<Shape> shapes;
    private List<Shape> allShapes;

    // Button response booleans
    private boolean toDrawShapes = false;
    private boolean toSetWidthHeight = false;
    private boolean toChangeWidth = false;
    private boolean toChangeHeight = false;

    // GUI display fields
    private JTextArea changeBackgroundPanelWrapper;
    private JTextArea changeOutlinePanelWrapper;
    private JTextArea widthText;
    private JTextArea heightText;
    private JComboBox<String> patternSelector;

    // Theme variables
    private JTextArea themeText;
    private ITheme theme = new GradientRedBlue();
    private boolean themeDrawn = false;

    // Canvas variables
    private int optionButtonHeight;
    private JPanel canvas;
    private int space;
    private FillStatus fillStatus = FillStatus.NONE;
    private Color backgroundColor = new Color(0, 0, 0);
    private Color outlineColor = new Color(200, 0, 0);

    // Output image and text file variable
    private PNGOutput png;

    public static int getXLoc() {
        return xLoc;
    }

    public static int getYLoc() {
        return yLoc;
    }

    /**
     * General constructor for the ShapePanel.
     */
    public ShapePanel() {
        Dimension screenSize = getScreenSize();
        this.setBounds(0, 0, screenSize.width, screenSize.height);

        // Setting layout to null is important for specifying specific tailored layout preferences in Swing
        this.setLayout(null);

        // Initialise fields
        textDisplay = new JTextArea();
        userInput = new JTextField();
        shapesToDraw = new ArrayList<>();
        shapes = new ArrayList<>();
        allShapes = new ArrayList<>();

        // Initialise swing components
        createButtons();
        createTextAreas();
        defineCanvasBounds();

        canvas.setBackground(backgroundColor);
    }

    @Override
    public boolean isOptimizedDrawingEnabled() {
        return false;
    }

    public void writeToTextBoxAndRepaint(String message) {
        TextBorder textDisplayWrapper = (TextBorder) textDisplay.getBorder();
        textDisplayWrapper.setText(message);
        textDisplay.repaint();
    }

    @Override
    public void paintComponent(Graphics mainFrameGraphics) {
        super.paintComponent(mainFrameGraphics);

        applyTheme(theme.getTheme(), mainFrameGraphics);
        this.draw(getGraphics(), png.getPng().getGraphics());
    }

    /**
     * Controls the booleans to flag when to handle user response and delegates the work to other methods.
     * This happens whenever the user selects ok, or presses the Enter button on their keyboard.
     */
    public void userInputResponse() {
        if (toSetWidthHeight) {
            setWidthAndHeight();
        } else if (toDrawShapes) {
            drawShapes();
        }
    }

    public void applyTheme(String themeName, Graphics mainFrameGraphics) {
        ITheme theme = Theme.getThemeFromName(themeName);
        if (theme == null) {
            return;
        }
        applyTheme(theme, mainFrameGraphics);
    }


    /**
     * Made for the paintComponent method. Minimises work done upon repaint. Sets current Theme to the theme
     * specified by the theme field string. Delegates to a theme class to draw.
     *
     * @param theme             The theme to apply
     * @param mainFrameGraphics The graphics object to draw the theme with
     */
    public void applyTheme(ITheme theme, Graphics mainFrameGraphics) {
        allShapes.clear();
        TextBorder t = (TextBorder) themeText.getBorder();
        String themeName = theme.getName();
        this.theme = theme;
        t.setText(themeName.substring(0, 1).toUpperCase() + themeName.substring(1));
        this.theme.applyTheme(mainFrameGraphics, this);
        writeToTextBoxAndRepaint("Select buttons, then either change the properties, or draw shapes");
    }

    /**
     * "shapeName" is the name on each button in the program. Format: First letter
     * always capitalised. Amount is the amount to draw.
     *
     * @param shapeName The name of the shape to be drawn
     * @param amount    The amount of the shape to draw
     */
    public void addShapeToDrawQueue(ShapeName shapeName, int amount) {
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
            default:
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
     * @param canvasGraphics The graphics object of the canvas to draw with
     * @param pngGraphics    The graphics object of the png image to draw with
     */
    public void draw(Graphics canvasGraphics, Graphics pngGraphics) {

        for (Shape shapeType : shapes) {
            // Get the type of shape and draw a certain amount of the type of shape
            shapeType.drawShape(canvasGraphics, pngGraphics, outlineColor, fillStatus);
            ShapeAbstract.setXCursor(0);
            ShapeAbstract.setYCursor(0);
        }

        // Finished drawing. Reset variables
        allShapes.addAll(shapes);
        shapes.clear();
    }

    public void drawThemeToCanvas() {
        JPanel canvas = getCanvas();

        // Set the background of the ShapePanel to black
        Graphics2D g2d = (Graphics2D) getGraphics().create();
        g2d.setPaint(new Color(0, 0, 0));
        g2d.fillRect(0, 0, getBounds().width, getBounds().height);
        clearAllShapes();

        // Redraw all the buttons
        for (Component c : getComponents()) {
            if (!c.equals(canvas)) {
                c.repaint();
            }
        }

        // Draw theme to canvas
        getTheme().applyTheme(canvas.getGraphics(), canvas);
        canvas.getGraphics().drawRect(0, 0, canvas.getBounds().width - 1, canvas.getBounds().height - 1);
        applyTheme(theme.getTheme(), getPng().getPng().getGraphics());
        setThemeDrawn(true);
    }

    /**
     * Change the background colour and inform the user that the background has been changed
     *
     * @param color The colour to change the background to
     */
    public void changeBackgroundColor(Color color) {

        // Background colour change on canvas
        backgroundColor = color;
        canvas.setBackground(color);

        ColorBorder colorLabel = (ColorBorder) changeBackgroundPanelWrapper.getBorder();
        colorLabel.setColor(color);

        // For simplicity, clear the shapes when the background colour is changed
        clearDrawing();

        changeBackgroundPanelWrapper.repaint();
        writeToTextBoxAndRepaint("Background colour changed successfully");
        themeDrawn = false;
    }

    /**
     * Change the shape outline color and inform the user that the background has been changed
     *
     * @param color The color to change the shape outline to
     */
    public void shapeOutlineColorChange(Color color) {
        // Shape outline colour change
        outlineColor = color;
        ColorBorder colorLabel = (ColorBorder) changeOutlinePanelWrapper.getBorder();
        colorLabel.setColor(color);
        changeOutlinePanelWrapper.repaint();
        writeToTextBoxAndRepaint("Outline colour changed successfully");
    }

    /**
     * Set a theme of the panel from the name of the theme.
     *
     * @param themeName The name of the theme
     */
    public void setThemeFromName(String themeName) {
        if (themeName.equals("none")) {
            this.theme = null;
            return;
        }
        applyTheme(themeName, this.getGraphics());
    }

    public void clearAllShapes() {
        if (allShapes == null) {
            allShapes = new ArrayList<>();
        } else {
            allShapes.clear();
            Shape.clearAllShapes();
        }
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

    public JTextArea getTextDisplay() {
        return textDisplay;
    }

    public PNGOutput getPng() {
        return png;
    }

    public void setPng(PNGOutput png) {
        this.png = png;
    }

    public List<JButton> getShapeButtonList() {
        return shapeButtonList;
    }

    public void setShapesToDraw(List<ShapeName> shapesToDraw) {
        this.shapesToDraw = shapesToDraw;
    }

    public JTextField getUserInput() {
        return userInput;
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

    public JTextArea getChangeOutlinePanelWrapper() {
        return changeOutlinePanelWrapper;
    }

    public void setToDrawShapes(boolean toDrawShapes) {
        this.toDrawShapes = toDrawShapes;
    }

    public void setToSetWidthHeight(boolean toSetWidthHeight) {
        this.toSetWidthHeight = toSetWidthHeight;
    }

    public void setToChangeWidth(boolean toChangeWidth) {
        this.toChangeWidth = toChangeWidth;
    }

    public void setToChangeHeight(boolean toChangeHeight) {
        this.toChangeHeight = toChangeHeight;
    }

    public FillStatus getFillStatus() {
        return fillStatus;
    }

    public void setFillStatus(FillStatus fillStatus) {
        this.fillStatus = fillStatus;
    }

    public boolean isThemeDrawn() {
        return themeDrawn;
    }

    public void setThemeDrawn(boolean themeDrawn) {
        this.themeDrawn = themeDrawn;
    }

    public ITheme getTheme() {
        return theme;
    }

    public void setTheme(ITheme theme) {
        this.theme = theme;
    }

    public JButton getClearButton() {
        return clearButton;
    }

    public Color getBackgroundColor() {
        return backgroundColor;
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
        shapeButton.setBounds(xLoc, yLoc, BUTTON_WD, BUTTON_HT);
        yLoc += (BUTTON_HT + 20);
        if (yLoc > BUTTON_HT * 6) {
            xLoc += (BUTTON_WD + 20);
            yLoc = 20;
        }
        this.add(shapeButton);
    }

    /**
     * Execute the sequence of prompts when drawing shapes including asking which type of shape and the amount of shapes desired.
     */
    private void drawShapes() {

        if (shapesToDraw.size() > 0) {
            writeToTextBoxAndRepaint("How many " + shapesToDraw.get(0).getShapeName() + "s?");
            userInput.requestFocus();
            if (!userInput.getText().equals("")) {
                try {
                    int input = Integer.parseInt(userInput.getText());
                    if (input > 1000 || input < 0) {
                        writeToTextBoxAndRepaint("Please enter a number that is between 0 and 1000 inclusive");
                        return;
                    }

                    addShapeToDrawQueue(shapesToDraw.get(0), input);
                    shapesToDraw.remove(shapesToDraw.get(0));
                    if (!shapesToDraw.isEmpty()) {
                        // Continue. Pressing the ok button restarts this method
                        writeToTextBoxAndRepaint("How many " + shapesToDraw.get(0).getShapeName() + "s?");
                    } else {
                        /* End case */
                        // Set the background of the png before drawing
                        Graphics pngGraphics = png.getPng().getGraphics();
                        pngGraphics.setColor(backgroundColor);
                        pngGraphics.fillRect(0, 0, png.getPng().getWidth(), png.getPng().getHeight());

                        draw(canvas.getGraphics(), png.getPng().getGraphics());
                        PNGOutput.createPNGFile(this, png);
                        shapes = new ArrayList<>();
                        toDrawShapes = false;
                        writeToTextBoxAndRepaint("Drawn successfully");
                    }
                } catch (NumberFormatException e) {
                    writeToTextBoxAndRepaint("You didn't enter an integer number!");
                }
            }
        }
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
        this.add(new ChangeBackgroundButton(this, png, new ChangeBackgroundResponse()));
        yLoc += optionButtonHeight;
        changeBackgroundPanelWrapper = new ColoredLabel(backgroundColor);
        this.add(changeBackgroundPanelWrapper);
        moveXY();

        this.add(new ShapeColorButton(this, png, new ChangeShapeColorResponse()));
        yLoc += optionButtonHeight;
        changeOutlinePanelWrapper = new ColoredLabel(outlineColor);
        this.add(changeOutlinePanelWrapper);
        moveXY();

        this.add(new WidthHeightButton(this, png, new WidthHeightResponse()));
        yLoc += optionButtonHeight;
        addWidthHeightText();
        moveXY();

        addDrawShapesButton(space);

        // Set cursor for row 2
        xLoc += (optionButtonWidth + space);
        xLoc -= (optionButtonWidth * 4) + (space * 4);
        yLoc += optionButtonHeight * 2 + space;

        // Row 2
        this.add(new AutoBackgroundColorButton(this, png, new AutoBackgroundColorResponse()));
        yLoc += optionButtonHeight;

        this.add(new FillButton(this, png, null));
        moveXY();

        this.add(new AutoShapeColorButton(this, png, new AutoShapeColor()));
        yLoc += optionButtonHeight;

        this.add(new DrawThemeToCanvasButton(this, png, new DrawThemeToCanvasResponse()));
        moveXY();

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
        widthText.setBounds(xLoc, yLoc, optionButtonWidth / 2, optionButtonHeight);
        widthText.setBorder(new TextBorder("" + ShapeAbstract.getWidth()));
        this.heightText = new JTextArea();
        heightText.setBounds(xLoc + optionButtonWidth / 2, yLoc, optionButtonWidth / 2, optionButtonHeight);
        heightText.setBorder(new TextBorder("" + ShapeAbstract.getHeight()));
        this.add(widthText);
        this.add(heightText);
    }

    /**
     * Adds the draw shapes button to the GUI.
     */
    private void addDrawShapesButton(int space) {

        JButton drawShapesButton = new DrawShapesButton(this, png, new DrawShapesResponse());
        drawShapesButton.setBounds(xLoc, 20, optionButtonWidth, optionButtonHeight * 4 + space);
        this.add(drawShapesButton);
    }

    /**
     * Adds the clear button to the GUI.
     *
     * @param optColour The colour of the button.
     */
    private void addClearButton(Color optColour) {

        this.clearButton = new JButton();
        clearButton.setBounds(xLoc, yLoc, optionButtonWidth, optionButtonHeight * 2);
        clearButton.setBorder(new OptionBorder("Clear Drawing", optColour));
        clearButton.addActionListener(event -> clearDrawing());
        this.add(clearButton);
    }

    private void clearDrawing() {
        // Fill the background with the colour that is currently set
        Graphics canvasGraphics = canvas.getGraphics();
        Graphics pngGraphics = png.getPng().getGraphics();
        canvasGraphics.setColor(backgroundColor);
        pngGraphics.setColor(backgroundColor);
        canvasGraphics.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        pngGraphics.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());

        // Clear all memory of shapes
        themeDrawn = false;
        shapes.clear();
        allShapes.clear();
        shapesToDraw.clear();
        Shape.clearAllShapes();

        writeToTextBoxAndRepaint("Drawing Cleared");
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
        patternSelect.setBounds(xLoc, yLoc - BUTTON_HT * 2 + space, optionButtonWidth, optionButtonHeight);
        TextBorder patternBorder = new TextBorder("Select Pattern");
        patternBorder.setFont(new Font("Arial", Font.BOLD, 18));
        patternSelect.setBorder(patternBorder);
        this.add(patternSelect);
    }

    private void addPatternSelectDropdown() {
        yLoc += optionButtonHeight;
        this.patternSelector = new JComboBox<>();
        patternSelector.setBounds(xLoc, yLoc - BUTTON_HT * 2 + space, optionButtonWidth, optionButtonHeight);
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
        load.setBounds(xLoc, yLoc - BUTTON_HT * 2 + space - 10, optionButtonWidth, optionButtonHeight + 10);
        this.add(load);
    }

    /**
     * Define and add the save button to the main panel
     */
    private void addSaveToFileButton() {
        JButton save = new SaveButton(this, png, new SaveFileAndPNGResponse());
        save.setBounds(xLoc, yLoc - BUTTON_HT * 2 + space - 10, optionButtonWidth, optionButtonHeight + 10);
        this.add(save);
    }

    /**
     * Define and add the theme button to the main panel
     */
    private void addThemeButton() {
        JButton changeThemeButton = new ChangeThemeButton(this, png, new ChangeThemeResponse());
        changeThemeButton.setBounds(xLoc, yLoc - BUTTON_HT * 2 + space - 10, optionButtonWidth, optionButtonHeight + 10);
        yLoc += optionButtonHeight;

        String themeName = theme.getName();
        themeText = new JTextArea(themeName);
        themeText.setBounds(xLoc, yLoc - BUTTON_HT * 2 + space, optionButtonWidth, optionButtonHeight);
        TextBorder themeBorder = new TextBorder(themeName.substring(0, 1).toUpperCase() + themeName.substring(1));
        themeBorder.setFont(new Font("Arial", Font.BOLD, 18));
        themeText.setBorder(themeBorder);
        this.add(changeThemeButton);
        this.add(themeText);
    }

    /**
     * Creates the text areas for user notification and user input.
     */
    private void createTextAreas() {
        int textBoxWidth = this.getPreferredSize().width - xLoc - 20;
        textDisplay.setBounds(xLoc, yLoc - 5, textBoxWidth, 50);
        textDisplay.setBorder(new TextBorder("Select buttons, then either change the properties, or draw shapes"));
        yLoc += 55;

        userInput.setBounds(xLoc, yLoc - 5, textBoxWidth - optionButtonWidth, 30);
        userInput.setBorder(new SimpleBorder());
        userInput.addKeyListener(new UserInputKeyListener(this));
        JButton ok = new JButton("OK!");
        ok.setBorder(new SimpleBorder());
        ok.setBounds((xLoc + textBoxWidth) - optionButtonWidth + 5, yLoc - 5, optionButtonWidth - 5, 30);
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
    private void setCanvasSizeVariables() {
        // Set the width and height depending on how many shapes can fit across the canvas according to its size
        int divisor = 11;
        int w = canvasSize.width / divisor - (divisor / 2);
        // Set the static ShapeAbstract variables
        ShapeAbstract.setCanvasSize(canvasSize);
        ShapeAbstract.setWidth(w);
        ShapeAbstract.setPrevWidth(w);
        ShapeAbstract.setHeight(w);
        ShapeAbstract.setPrevHeight(w);
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
     * Execute the sequence of prompts to the user to take user input to set the width and height of shapes.
     */
    private void setWidthAndHeight() {
        if (!userInput.getText().equals("")) {
            try {
                int input = Integer.parseInt(userInput.getText());
                if (input < 0 || input > 400) {
                    String dimension = "width";
                    if (!toChangeWidth) {
                        dimension = "height";
                    }
                    writeToTextBoxAndRepaint(String.format("Please enter an integer between 0 to 400. "
                            + "Choose %s: (enter an integer between 0 to 400)", dimension));
                    resetShapeHeightAndWidth();
                    return;
                }
                if (toChangeWidth) {
                    // Successfully changed width
                    ShapeAbstract.setWidth(input);
                    toChangeWidth = false;

                    // Query change height
                    writeToTextBoxAndRepaint("Choose height: (enter an integer between 0 to 400)");
                    userInput.requestFocus();

                    TextBorder text = (TextBorder) widthText.getBorder();
                    text.setText("" + input);
                    widthText.repaint();
                } else if (toChangeHeight) {
                    ShapeAbstract.setHeight(input);
                    toChangeHeight = false;
                    writeToTextBoxAndRepaint("Width and height adjusted.");

                    // Update previous width and height to be current ones
                    ShapeAbstract.setPrevWidth(ShapeAbstract.getWidth());
                    ShapeAbstract.setPrevHeight(ShapeAbstract.getHeight());

                    TextBorder text = (TextBorder) heightText.getBorder();
                    text.setText("" + input);
                    heightText.repaint();
                    toSetWidthHeight = false;
                }
            } catch (NumberFormatException e) {
                writeToTextBoxAndRepaint("You didn't enter an integer number!");
                resetShapeHeightAndWidth();
                toSetWidthHeight = false;
            }
        }
    }

    private void resetShapeHeightAndWidth() {
        int prevWidth = ShapeAbstract.getPrevWidth();
        ShapeAbstract.setWidth(prevWidth);
        TextBorder wdText = (TextBorder) widthText.getBorder();
        wdText.setText("" + prevWidth);
        widthText.repaint();

        int prevHeight = ShapeAbstract.getPrevHeight();
        ShapeAbstract.setHeight(prevHeight);
        TextBorder htText = (TextBorder) heightText.getBorder();
        htText.setText("" + prevHeight);
        heightText.repaint();
    }
}
