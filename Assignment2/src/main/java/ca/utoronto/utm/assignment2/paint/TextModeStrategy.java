package ca.utoronto.utm.assignment2.paint;

import ca.utoronto.utm.assignment2.paint.controlPanels.PropertiesPanel;
import ca.utoronto.utm.assignment2.paint.shapes.Point;
import ca.utoronto.utm.assignment2.paint.shapes.Text;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

/**
 * The TextModeStrategy class implements the ModeStrategy interface and handles the behavior
 * of creating and editing text boxes in the paint application.
 * <p>
 * This strategy is used when the user is in text mode, allowing them to create and edit text shapes
 * by clicking to create a text box and typing text inside it. Once the user finishes entering text, the
 * text box is saved to the model.
 * </p>
 * @author chen2046
 */
public class TextModeStrategy extends DrawModeStrategy implements ModeStrategy {
    private final Pane canvasPane;
    Text text;

    /**
     * Constructs a TextModeStrategy instance.
     *
     * @param model The PaintModel instance that manages the shapes.
     * @param mode The mode for the operation (e.g., "Text").
     * @param pp The PropertiesPanel instance for loading and displaying paint properties.
     * @param canvasPane The pane where the shapes and text fields are drawn.
     */
    public TextModeStrategy(PaintModel model, String mode, PropertiesPanel pp, Pane canvasPane) {
        super(model, mode, pp);
        this.canvasPane = canvasPane;
        this.text = (Text)model.getCurrentShape();
        this.properties = pp;
    }

    /**
     * This method is called when the mouse is pressed. It starts the creation of a new text box
     * at the mouse location and initializes the associated Text object.
     *
     * @param point The point where the mouse was pressed.
     * @param isPrimaryButton Whether the primary mouse button (left-click) was pressed.
     * @param isSecondaryButton Whether the secondary mouse button (right-click) was pressed.
     */
    @Override
    public void onMousePressed(Point point, boolean isPrimaryButton, boolean isSecondaryButton) {
        super.onMousePressed(point, isPrimaryButton, isSecondaryButton);

        text = new Text(point, point, properties.getPaintProperties());
        System.out.println("Started text box at: " + point);
    }

    /**
     * This method is called when the mouse is released. It finalizes the text box creation
     * and activates the text field for user input.
     *
     * @param point The point where the mouse was released.
     */
    @Override
    public void onMouseReleased(Point point) {
        super.onMouseReleased(point);
        activateTextField();
        System.out.println("Completed text bo x creation at: " + point);
    }

    /**
     * This method activates the text field, allowing the user to type inside the text box.
     * It adds the text field to the canvas pane and sets up event listeners for when the
     * user finishes typing.
     */
    private void activateTextField() {
        if (text == null) return;
        TextField textField = text.getTextField();
        text.setupTextField(text.getStart());
        canvasPane.getChildren().add(textField);
        System.out.println("TextField added at: " + textField.getLayoutX() + ", " + textField.getLayoutY());

        textField.setOnAction(e -> saveTextAndRemoveTextField());

        textField.focusedProperty().addListener((obs, wasFocused, isNowFocused) -> {
            if (!isNowFocused) {
                if (!textField.getText().trim().isEmpty()) {
                    saveTextAndRemoveTextField();
                } else {
                    canvasPane.getChildren().remove(textField);
                    text.setTextField(null);
                }
            }
        });
    }

    /**
     * This method saves the text content from the text field into the Text shape and removes
     * the text field from the canvas. It then adds the text shape to the model.
     */
    private void saveTextAndRemoveTextField() {
        TextField textField = text.getTextField();

        if (textField != null) {
            text.setTextContent(textField.getText());
            canvasPane.getChildren().remove(textField);
            model.addShape(text); // Save text shape in model
            System.out.println("Text saved");
        }
        text.setTextField(null);
    }
}
