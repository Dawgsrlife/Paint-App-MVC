package ca.utoronto.utm.assignment2.paint;

import ca.utoronto.utm.assignment2.paint.shapes.Point;
import ca.utoronto.utm.assignment2.paint.shapes.Shape;

/**
 * Strategy for erasing objects in the painting application.
 * <p>
 * This class handles the logic for the "Object Eraser" mode, where the user can click on shapes to remove them from the canvas.
 * It manages the interaction with shapes by allowing the user to select and undo them by clicking on them.
 * The strategy responds only to left-click events and performs actions based on the selected shape.
 * </p>
 *
 * @see ModeStrategy
 * @see PaintModel
 * @see Shape
 * @author tianji61
 */
public class ObjectEraserStrategy implements ModeStrategy {
    private final PaintModel model;

    /**
     * Constructs an ObjectEraserStrategy instance.
     * <p>
     * Initializes the strategy with the given {@link PaintModel}, which holds the current drawing state.
     * </p>
     *
     * @param model The paint model that manages the current shapes and state of the drawing.
     */
    public ObjectEraserStrategy(PaintModel model) {
        this.model = model;
    }

    /**
     * Handles mouse movement events.
     * <p>
     * This method is a no-op (no operation) for the ObjectEraserStrategy, as erasing objects is triggered by mouse presses rather than movement.
     * </p>
     *
     * @param point The current mouse position.
     */
    @Override
    public void onMouseMoved(Point point) {
        // No-op
    }

    /**
     * Handles mouse press events.
     * <p>
     * When the primary mouse button (left-click) is pressed, the strategy checks if a shape is selected at the mouse's position.
     * If a shape is found, it is "erased" by undoing the action and removing it from the model.
     * </p>
     *
     * @param point The point where the mouse was pressed.
     * @param isPrimaryButton True if the primary (left) mouse button was pressed.
     * @param isSecondaryButton True if the secondary (right) mouse button was pressed.
     */
    @Override
    public void onMousePressed(Point point, boolean isPrimaryButton, boolean isSecondaryButton) {
        // Only handle for left-clicks (or primary button usage)
        // Thus, exit if the primary button isn't used
        if (!isPrimaryButton) return;

        // Relies on short-circuit evaluation:
        if (model.getTempShape() != null && !model.getTempShape().isFinalized()) finalizeShape();
        // E.g. Polyline isn't finalized until the user right-clicks ^
        // so simply finalize it when they attempt to draw with another shape.

        model.setCurrentShape(model.getSelected(point));
        if (model.getCurrentShape() != null) model.undo(model.getCurrentShape());
    }

    /**
     * Handles mouse drag events.
     * <p>
     * This method is a no-op for the ObjectEraserStrategy, as no action is taken while the mouse is being dragged.
     * </p>
     *
     * @param point The current mouse position.
     */
    @Override
    public void onMouseDragged(Point point) {
        // No-op
    }

    /**
     * Handles mouse release events.
     * <p>
     * This method is a no-op for the ObjectEraserStrategy, as no action is taken when the mouse is released.
     * </p>
     *
     * @param point The point where the mouse was released.
     */
    @Override
    public void onMouseReleased(Point point) {
        // No-op
    }

    /**
     * Finalizes the shape drawing process.
     * <p>
     * This method updates the model by adding the shape to the list of drawn shapes and clears the selected shape from the cache.
     * </p>
     */
    public void finalizeShape() {
        Shape shape = model.getCurrentShape();
        model.addShape(shape);

        // Clean the cache on MOUSE_RELEASED
        if (shape != null) model.setCurrentShape(null);
    }
}
