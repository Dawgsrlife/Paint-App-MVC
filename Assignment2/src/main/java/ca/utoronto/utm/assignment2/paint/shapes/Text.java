package ca.utoronto.utm.assignment2.paint.shapes;

import ca.utoronto.utm.assignment2.paint.PaintController;
import ca.utoronto.utm.assignment2.paint.PaintProperties;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;

/**
 * This class represents a Text shape that can be added to the canvas.
 * A TextBox is defined by its start and end points, and the text it contains.
 * This class represents the style of textbox, so the style of text which users enter should not be changed by the
 * shape controller PropertiesPanel.
 * Author: chen2046
 */
public class Text extends Rectangle {

    private String text;
    private TextField textField;
    private final Font font;

    public Text(Point start, Point end, PaintProperties properties) {
        super(start, end, properties);
        setType("Text");
        this.text = ""; // Initially empty text
        this.font = new Font("Arial", 20); // Default font and size

        this.textField = new TextField();
        setupTextField(start);
    }

    private void setupTextField(Point start) {
        this.textField = new TextField();
        this.textField.setPromptText("Enter text");
        this.textField.setLayoutX(start.getX());
        this.textField.setLayoutY(start.getY());
        this.textField.setFont(font);
        this.textField.setVisible(true);
        this.textField.setStyle("-fx-background-color: transparent; -fx-border-color: transparent; -fx-text-fill: black;");
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
        if (!text.isEmpty()) {
            g2d.setFill(getProperties().getFillColor());
            g2d.setFont(font);
            g2d.fillText(this.text, info[0] + 5, info[1] + 20); // Text offset
        }
    }

    public void activateTextField(Pane canvasPane, PaintController controller) {
        canvasPane.getChildren().remove(textField);
        setupTextField(getStart());
        canvasPane.getChildren().add(textField);
        textField.requestFocus();

        System.out.println("TextField added at: " + textField.getLayoutX() + ", " + textField.getLayoutY());

        textField.setOnAction(e -> saveTextAndRemoveTextField(canvasPane, controller));

        textField.focusedProperty().addListener((obs, wasFocused, isNowFocused) -> {
            if (!isNowFocused) {
                if (!textField.getText().trim().isEmpty()) {
                    saveTextAndRemoveTextField(canvasPane, controller);
                } else {
                    canvasPane.getChildren().remove(textField);
                    textField = null;
                }
            }
        });

    }

    private void saveTextAndRemoveTextField(Pane canvasPane, PaintController controller) {
        if (textField != null) {
            this.text = textField.getText();
            canvasPane.getChildren().remove(textField);
            textField = null;
            controller.persistTextBox(this); // Save text shape in model
            System.out.println("Text saved");
        }
    }

}
