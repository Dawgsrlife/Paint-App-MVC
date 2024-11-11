package ca.utoronto.utm.assignment2.paint;

import ca.utoronto.utm.assignment2.paint.shapes.Point;

public class ObjectEraserStrategy implements ModeStrategy{
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
}
