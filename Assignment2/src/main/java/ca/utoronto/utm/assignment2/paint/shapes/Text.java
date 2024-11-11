package ca.utoronto.utm.assignment2.paint.shapes;

import ca.utoronto.utm.assignment2.paint.PaintProperties;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.TextField;
import javafx.scene.text.Font;

/**
 * This class represents a Text shape that can be added to the canvas.
 * A TextBox is defined by its start and end points, and the text it contains.
 * This class represents the style of the textbox, so the style of the text
 * entered by users is not controlled by the shape's PropertiesPanel.
 *
 * @author chen2046
 */
public class Text extends Rectangle {
    private String textContent;
    private TextField textField;
    private final Font font;

    /**
     * Constructs a new Text shape with the given start and end points,
     * and paint properties.
     * Initializes text content and font settings.
     *
     * @param start The starting point of the Text shape.
     * @param end The ending point of the Text shape.
     * @param properties The paint properties of the Text shape.
     */
    public Text(Point start, Point end, PaintProperties properties) {
        super(start, end, properties);
        properties.setFilled(true);
        setType("Text");
        this.textContent = ""; // Initially empty text
        this.font = new Font("Arial", 20); // Default font and size
        this.textField = new TextField();
        setupTextField(start);
    }

    /**
     * Sets up the TextField at the specified starting point. The TextField allows users
     * to input text in the shape.
     *
     * @param start The starting point of the Text shape to position the TextField.
     */
    public void setupTextField(Point start) {
        this.textField = new TextField();
        this.textField.setPromptText("Enter text");
        this.textField.setLayoutX(start.getX());
        this.textField.setLayoutY(start.getY());
        this.textField.setFont(font);
        this.textField.setStyle("-fx-background-color: transparent; -fx-border-color: transparent; -fx-text-fill: black;");
        this.textField.setVisible(true);
    }

    /**
     * Paints the Text shape on the canvas. It draws the border and fills the shape
     * if required, and also renders the text content if it's not empty.
     *
     * @param g2d The graphics context used for painting.
     */
    @Override
    public void paint(GraphicsContext g2d) {
        double[] info = getPaintInfo();

        if (getProperties().getStrokeThickness() != 0.0) {
            g2d.setStroke(getProperties().getStrokeColor());
            g2d.setLineWidth(getProperties().getStrokeThickness());
            g2d.strokeRect(info[0], info[1], info[2], info[3]);
        }
        if (getProperties().isFilled()) {
            fill(g2d);
        }
    }

    /**
     * Fills the Text shape with its fill color and displays the text content inside the shape.
     * The text is positioned with an offset to avoid overlapping the shape's boundary.
     *
     * @param g2d The graphics context used for filling.
     */
    @Override
    protected void fill(GraphicsContext g2d) {
        double[] info = getPaintInfo();
        if (!textContent.isEmpty()) {
            g2d.setFill(getProperties().getFillColor());
            g2d.setFont(font);
            g2d.fillText(this.textContent, info[0] + 5, info[1] + 20); // Text offset
        }
    }


    /**
     * Gets the TextField associated with the Text shape, allowing user input.
     *
     * @return The TextField used for text input in the shape.
     */
    public TextField getTextField() {
        return textField;
    }

    /**
     * Sets the TextField associated with the Text shape.
     *
     * @param textField The TextField to be associated with the Text shape.
     */
    public void setTextField(TextField textField) {
        this.textField = textField;
    }

    /**
     * Sets the content of the text in the Text shape.
     *
     * @param textContent The content to set for the Text shape.
     */
    public void setTextContent(String textContent) {
        this.textContent = textContent;
    }

    /**
     * Moves the Text shape by the specified amounts in the x and y directions.
     * The TextField position is also updated accordingly.
     *
     * @param dx The change in the x-coordinate.
     * @param dy The change in the y-coordinate.
     */
    @Override
    public void move(double dx, double dy) {
        getStart().setX(getStart().getX() + dx);
        getStart().setY(getStart().getY() + dy);
        getEnd().setX(getEnd().getX() + dx);
        getEnd().setY(getEnd().getY() + dy);
        this.textField.setLayoutX(getStart().getX() + dx);
        textField.setDisable(true);
        this.textField.setLayoutY(getStart().getY() + dy);
    }
}
