package ca.utoronto.utm.assignment2.paint;

import ca.utoronto.utm.assignment2.paint.controlPanels.PropertiesPanel;
import ca.utoronto.utm.assignment2.paint.shapes.Point;
import ca.utoronto.utm.assignment2.paint.shapes.Shape;

public class DrawModeStrategy implements ModeStrategy {
    protected final PaintModel model;
    protected final String mode;
    PropertiesPanel properties;

    public DrawModeStrategy(PaintModel model, String mode, PropertiesPanel pp) {
        this.model = model;
        this.mode = mode;
        this.properties = pp;
    }

    @Override
    public void onMouseMoved(Point point) {
        // No-op
    }

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

    @Override
    public void onMouseDragged(Point point) {
        Shape shape = model.getCurrentShape();

        if (shape == null) return;

        shape.setEnd(point);
        model.addTempShape(shape);
    }

    @Override
    public void onMouseReleased(Point point) {
        if (model.getCurrentShape() == null) return;

        model.getCurrentShape().finalizeShape();
        finalizeShape();
    }

    /**
     * Finalizes a drawing process.
     * <p>
     * Updates the model with the shape to be created and clears the selected shape.
     */
    public void finalizeShape() {
        Shape shape = model.getCurrentShape();
        model.addShape(shape);
        // TODO: delete the following line later for the final product:
        if (shape != null) System.out.println("    ^ Added");

        // Clean the cache on MOUSE_RELEASED
        if (shape != null) model.setCurrentShape(null);
    }
}
