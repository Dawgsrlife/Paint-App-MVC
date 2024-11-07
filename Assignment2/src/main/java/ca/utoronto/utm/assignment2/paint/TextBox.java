package ca.utoronto.utm.assignment2.paint;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

/**
 * This class represents a TextBox shape that can be added to the canvas.
 * A TextBox is defined by its start and end points, as well as the text it contains.
 */
public class TextBox extends Shape {

    private String text;
    private TextField textField;
    private Font font;

    public TextBox(Point start, Point end, boolean filled, Color color, Color borderColor, double borderWidth) {
        super(start, end, "TextBox", filled, color, borderColor, borderWidth);
        this.text = ""; // Initially no text
        this.font = new Font("Arial", 20); // Default font and size
        this.textField = new TextField();
        this.textField.setLayoutX(start.x);
        this.textField.setLayoutY(start.y);
        this.textField.setFont(font);
        this.textField.setVisible(false);
    }

    public void addTextFieldToPane(javafx.scene.layout.Pane pane) {
        textField.setVisible(true);
        pane.getChildren().add(textField);
        textField.requestFocus(); // Focus on the TextField to enable typing
        textField.setOnAction(e -> {
            text = textField.getText();
            textField.setVisible(false);
            pane.getChildren().remove(textField);
        });
    }

    @Override
    void paint(GraphicsContext g2d) {
        double x = Math.min(getStart().x, getEnd().x);
        double y = Math.min(getStart().y, getEnd().y);
        double width = Math.abs(getStart().x - getEnd().x);
        double height = Math.abs(getStart().y- getEnd().y);

        g2d.setStroke(getBorderColor());
        g2d.setLineWidth(getBorderWidth());
        g2d.setFont(font);
        g2d.setFill(getColor());

        if (isFilled()) {
            g2d.fillRect(x, y, width, height);
        }
        g2d.strokeRect(x, y, width, height);

        if (!text.isEmpty()) {
            // Draw the text inside the rectangle
            g2d.setFill(getColor());
            g2d.fillText(text, x + 5, y + 20); // Adding padding to make text look better
        }
    }

    @Override
    protected void fill(GraphicsContext g2d) {
        if (isFilled()) {
            double x = Math.min(getStart().x, getEnd().x);
            double y = Math.min(getStart().y, getEnd().y);
            double width = Math.abs(getStart().x - getEnd().x);
            double height = Math.abs(getStart().y - getEnd().y);
            g2d.setFill(getColor());
            g2d.fillRect(x, y, width, height);
        }
    }

    @Override
    protected double[] getPaintInfo() {
        double[] info = new double[4];
        info[0] = Math.min(getStart().x, getEnd().x);
        info[1] = Math.min(getStart().y, getEnd().y);
        info[2] = Math.abs(getStart().x - getEnd().x);
        info[3] = Math.abs(getStart().y - getEnd().y);
        return info;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public TextField getTextField() {
        return textField;
    }
}
