package ca.utoronto.utm.assignment2.paint;

import ca.utoronto.utm.assignment2.paint.controlPanels.PropertiesPanel;
import ca.utoronto.utm.assignment2.paint.shapes.Point;
import ca.utoronto.utm.assignment2.paint.shapes.Shape;

/**
 * A strategy class for handling drawing behavior in a painting application.
 * <p>
 * This class implements the {@link ModeStrategy} interface and provides logic for handling
 * mouse events (movement, clicks, dragging) during the drawing mode. It manages the creation
 * and finalization of shapes based on user interactions, such as left-clicking to start drawing,
 * dragging to update the shape, and releasing the mouse to finalize the shape.
 * </p>
 *
 * @see ModeStrategy
 * @see PaintModel
 * @see Shape
 * @author mengale1
 */
public class DrawModeStrategy implements ModeStrategy {
    protected final PaintModel model;
    protected final String mode;
    PropertiesPanel properties;

    /**
     * Constructs a new DrawModeStrategy with the specified paint model, mode, and properties panel.
     *
     * @param model The paint model to manage the shapes being drawn.
     * @param mode The current drawing mode (e.g., line, rectangle, etc.).
     * @param pp The properties panel containing the current paint properties (e.g., color, stroke width).
     */
    public DrawModeStrategy(PaintModel model, String mode, PropertiesPanel pp) {
        this.model = model;
        this.mode = mode;
        this.properties = pp;
    }

    /**
     * Handles mouse movement events. This implementation does nothing (no-op).
     *
     * @param point The point where the mouse moved.
     */
    @Override
    public void onMouseMoved(Point point) {
        // No-op
    }

    /**
     * Handles mouse press events to begin drawing a shape.
     * <p>
     * This method handles only the left mouse button (primary button). If a shape is being drawn
     * and the mouse is pressed with another button, it is ignored. The shape is finalized if
     * another shape is currently being drawn when the user presses the mouse.
     * </p>
     *
     * @param point The point where the mouse was pressed.
     * @param isPrimaryButton Whether the left mouse button (primary button) was pressed.
     * @param isSecondaryButton Whether the right mouse button (secondary button) was pressed.
     */
    @Override
    public void onMousePressed(Point point, boolean isPrimaryButton, boolean isSecondaryButton) {
        // Only handle for left-clicks (or primary button usage):
        // Thus, exit if the primary button isn't used
        if (!isPrimaryButton) return;

        // Relies on short-circuit evaluation:
        if (model.getTempShape() != null && !model.getTempShape().isFinalized()) finalizeShape();
        // E.g. Polyline isn't finalized until the user right-clicks ^
        // so simply finalize it when they attempt to draw with another shape.

        model.setCurrentShape(PaintStrategy.getPaintStrategy(mode, point, point, properties.getPaintProperties(), null));
    }

    /**
     * Handles mouse drag events to update the current shape's endpoint as the mouse is dragged.
     * The shape is updated with each new point and added to the temporary shapes in the model.
     *
     * @param point The point where the mouse is being dragged.
     */
    @Override
    public void onMouseDragged(Point point) {
        Shape shape = model.getCurrentShape();

        if (shape == null) return;

        shape.setEnd(point);
        model.addTempShape(shape);
    }

    /**
     * Handles mouse release events to finalize the current shape.
     * <p>
     * This method finalizes the current shape when the mouse button is released.
     * The shape is then added to the model and the drawing state is reset.
     * </p>
     *
     * @param point The point where the mouse was released.
     */
    @Override
    public void onMouseReleased(Point point) {
        if (model.getCurrentShape() == null) return;

        model.getCurrentShape().finalizeShape();
        finalizeShape();
    }

    /**
     * Finalizes the drawing process for the current shape.
     * <p>
     * This method updates the model with the shape to be created and clears the selected shape.
     * It is called when the shape is ready to be finalized, either on mouse release or when another
     * shape is being created.
     * </p>
     */
    public void finalizeShape() {
        Shape shape = model.getCurrentShape();
        model.addShape(shape);

        // Clean the cache on MOUSE_RELEASED
        if (shape != null) model.setCurrentShape(null);
    }
}
