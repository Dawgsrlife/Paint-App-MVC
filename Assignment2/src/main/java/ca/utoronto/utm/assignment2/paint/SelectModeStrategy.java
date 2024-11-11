package ca.utoronto.utm.assignment2.paint;

import ca.utoronto.utm.assignment2.paint.controlPanels.PropertiesPanel;
import ca.utoronto.utm.assignment2.paint.shapes.Point;
import ca.utoronto.utm.assignment2.paint.shapes.Shape;

public class SelectModeStrategy implements ModeStrategy {
    private final PaintModel model;
    private final PropertiesPanel properties;

    public SelectModeStrategy(PaintModel model, PropertiesPanel properties) {
        this.model = model;
        this.properties = properties;
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
        if (model.getCurrentShape() != null) properties.loadPaintProperties(model.getCurrentShape());
    }

    @Override
    public void onMouseDragged(Point point) {
        if (model.getCurrentShape() == null) return;

        Shape shape = model.getCurrentShape();
        Point lastPoint = model.getLastPoint();

        shape.move(point.getX() - lastPoint.getX(), point.getY() - lastPoint.getY());

        model.update();
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
