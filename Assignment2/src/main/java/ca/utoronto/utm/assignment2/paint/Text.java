package ca.utoronto.utm.assignment2.paint;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

/**
 * This class represents a Text shape that can be added to the canvas.
 * A TextBox is defined by its start and end points, and the text it contains.
 * This class represents the style of textbox, so the style of text which users enter should not be changed by the
 * shape controller PropertiesPanel.
 * Author: chen2046
 */
public class Text extends Shape {

    private String text;
    private TextField textField;
    private Font font;

    public Text(Point start, Point end, PaintProperties properties) {
        super(start, end, "Text", properties);
        this.text = ""; // Initially empty text
        this.font = new Font("Arial", 20); // Default font and size

        this.textField = new TextField();
        setupTextField(start);
    }

    private void setupTextField(Point start) {
        this.textField = new TextField();
        this.textField.setPromptText("Enter text");
        this.textField.setLayoutX(start.x);
        this.textField.setLayoutY(start.y);
        this.textField.setFont(font);
        this.textField.setVisible(true);
        this.textField.setStyle("-fx-background-color: transparent; -fx-border-color: transparent; -fx-text-fill: black;");
    }

    @Override
    void paint(GraphicsContext g2d) {
        double[] info = getPaintInfo();

        if (getProperties().getStrokeThickness() != 0.0) {
            g2d.setStroke(getProperties().getStrokeColor());
            g2d.setLineWidth(getProperties().getStrokeThickness());
            g2d.strokeRect(info[0], info[1], info[2], info[3]);
        }
        if (getProperties().isFilled()) {
            fill(g2d);
        }

        if (!text.isEmpty()) {
            g2d.setFill(Color.BLACK);
            g2d.setFont(font);
            g2d.fillText(this.text, info[0] + 5, info[1] + 20); // Text offset
        }
    }

    @Override
    protected void fill(GraphicsContext g2d) {
        g2d.setFill(getProperties().getFillColor());
        double[] info = getPaintInfo();
        double width = getProperties().getStrokeThickness() / 2;
        g2d.fillRect(info[0] + width, info[1] + width,
                info[2] - width * 2, info[3] - width * 2);
    }

    public void activateTextField(Pane canvasPane, PaintController controller) {
        if (textField != null) {
            canvasPane.getChildren().remove(textField);
        }
        setupTextField(getStart());
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
            controller.persistTextBox(this); // Save text shape in model
        }
    }

    @Override
    protected double[] getPaintInfo() {
        double x = Math.min(getStart().x, getEnd().x);
        double y = Math.min(getStart().y, getEnd().y);
        double width = Math.abs(getEnd().x - getStart().x);
        double height = Math.abs(getEnd().y - getStart().y);
        return new double[]{x, y, width, height};
    }

    @Override
    boolean includeCursor(Point p) {
        double[] info = getPaintInfo();
        javafx.scene.shape.Rectangle bounds = new javafx.scene.shape.Rectangle(info[0], info[1], info[2], info[3]);
        return bounds.contains(p.x, p.y);
    }
}
