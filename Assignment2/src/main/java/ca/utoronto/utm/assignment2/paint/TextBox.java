package ca.utoronto.utm.assignment2.paint;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
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

    @Override
    public void paint(GraphicsContext g2d) {
        g2d.setStroke(getBorderColor());
        g2d.setLineWidth(getBorderWidth());
        g2d.strokeRect(getStart().x, getStart().y, Math.abs(getEnd().x - getStart().x), Math.abs(getEnd().y - getStart().y));

        // Display the text inside the box
        if (!text.isEmpty()) {
            g2d.setFill(getColor());
            g2d.setFont(font);
            g2d.fillText(this.text, getStart().x + 5, getStart().y + 20);
        }
    }

    private void setupField(Pane canvasPane) {
        textField = new TextField();
        textField.setPromptText("Please enter a text");
        textField.setLayoutX(this.getStart().x);
        textField.setLayoutY(this.getStart().y);
        textField.setMinWidth(100);
        textField.setFont(new Font("Arial", 20));
        textField.setStyle("-fx-background-color: transparent; -fx-border-color: transparent; -fx-text-fill: black;");

        // textField.setStyle("-fx-prompt-text-fill: derive(gray, -30%);");
        canvasPane.getChildren().add(textField);
    }

    public void activateTextField(Pane canvasPane, PaintController controller) {
        if (textField != null) {
            canvasPane.getChildren().remove(textField);
        }

        this.setupField(canvasPane);
        canvasPane.getChildren().add(textField);

        textField.setOnAction(e -> saveTextAndRemoveTextField(canvasPane, controller));
        textField.focusedProperty().addListener((obs, oldVal, newVal) -> {
            if (!newVal) {
                saveTextAndRemoveTextField(canvasPane, controller);
            }
        });
    }


    private void saveTextAndRemoveTextField(Pane canvasPane, PaintController controller) {
        if (textField != null) {
            this.text = textField.getText();
            canvasPane.getChildren().remove(textField);
            textField = null;

            controller.persistTextBox(this); // Return text into controller in order to be saved into model later
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
    public void setText(String text) {
        this.text = text;
    }
}
