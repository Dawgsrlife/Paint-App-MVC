package ca.utoronto.utm.assignment2.paint.shapes;

import ca.utoronto.utm.assignment2.paint.PaintProperties;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.TextField;
import javafx.scene.text.Font;

/**
 * This class represents a Text shape that can be added to the canvas.
 * A TextBox is defined by its start and end points, and the text it contains.
 * This class represents the style of textbox, so the style of text which users enter should not be changed by the
 * shape controller PropertiesPanel.
 * Author: chen2046
 */
public class Text extends Rectangle {
    private String textContent;
    private TextField textField;
    private final Font font;

    public Text(Point start, Point end, PaintProperties properties) {
        super(start, end, properties);
        properties.setFilled(true);
        setType("Text");
        this.textContent = ""; // Initially empty text
        this.font = new Font("Arial", 20); // Default font and size
        this.textField = new TextField();
        setupTextField(start);
    }

    public void setupTextField(Point start) {
        this.textField = new TextField();
        this.textField.setPromptText("Enter text");
        this.textField.setLayoutX(start.getX());
        this.textField.setLayoutY(start.getY());
        this.textField.setFont(font);
        this.textField.setStyle("-fx-background-color: transparent; -fx-border-color: transparent; -fx-text-fill: black;");
        this.textField.setVisible(true);
    }

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

    @Override
    protected void fill(GraphicsContext g2d) {
        double[] info = getPaintInfo();
        if (!textContent.isEmpty()) {
            g2d.setFill(getProperties().getFillColor());
            g2d.setFont(font);
            g2d.fillText(this.textContent, info[0] + 5, info[1] + 20); // Text offset
        }
    }

    public TextField getTextField() {
        return textField;
    }

    public void setTextField(TextField textField) {
        this.textField = textField;
    }

    public void setTextContent(String textContent) {
        this.textContent = textContent;
    }

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
