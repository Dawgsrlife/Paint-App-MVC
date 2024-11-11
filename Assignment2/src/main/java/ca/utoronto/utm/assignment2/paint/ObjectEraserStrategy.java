package ca.utoronto.utm.assignment2.paint;

import ca.utoronto.utm.assignment2.paint.shapes.Point;
import ca.utoronto.utm.assignment2.paint.shapes.Shape;

public class ObjectEraserStrategy implements ModeStrategy {
    private final PaintModel model;

    public ObjectEraserStrategy(PaintModel model) {
        this.model = model;
    }

    @Override
    public void onMouseMoved(Point point) {
        // No-op
    }

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

    @Override
    public void onMouseDragged(Point point) {
        // No-op
    }

    @Override
    public void onMouseReleased(Point point) {
        // No-op
    }

    /**
     * Finalizes a drawing process.
     * <p>
     * Updates the model with the shape to be created and clears the selected shape.
     */
    public void finalizeShape() {
        Shape shape = model.getCurrentShape();
        model.addShape(shape);

        // Clean the cache on MOUSE_RELEASED
        if (shape != null) model.setCurrentShape(null);
    }
}
