package ca.utoronto.utm.assignment2.paint;

import ca.utoronto.utm.assignment2.paint.controlPanels.PropertiesPanel;
import ca.utoronto.utm.assignment2.paint.shapes.Point;
import ca.utoronto.utm.assignment2.paint.shapes.Shape;

/**
 * The SelectModeStrategy class implements the ModeStrategy interface and handles the behavior
 * of selecting and moving shapes in the paint application. It responds to mouse events for
 * selecting a shape, dragging it, and finalizing the shape.
 * <p>
 * This strategy is used when the user is in select mode, which allows them to select an existing shape
 * from the canvas and move it around. It also updates the properties panel to display the properties
 * of the currently selected shape.
 * </p>
 * @author tianji61 / mengale1
 */
public class SelectModeStrategy implements ModeStrategy {
    private final PaintModel model;
    private final PropertiesPanel properties;

    /**
     * Constructs a SelectModeStrategy instance.
     *
     * @param model The PaintModel instance that manages the shapes.
     * @param properties The PropertiesPanel instance for loading and displaying shape properties.
     */
    public SelectModeStrategy(PaintModel model, PropertiesPanel properties) {
        this.model = model;
        this.properties = properties;
    }

    /**
     * This method is called when the mouse is moved. It doesn't perform any action in select mode.
     *
     * @param point The current mouse position.
     */
    @Override
    public void onMouseMoved(Point point) {
        // No-op
    }

    /**
     * This method is called when the mouse is pressed. It selects a shape if the primary mouse button
     * is pressed. It finalizes the current temporary shape if needed and updates the properties panel
     * to reflect the properties of the selected shape.
     *
     * @param point The point where the mouse was pressed.
     * @param isPrimaryButton Whether the primary mouse button (left-click) was pressed.
     * @param isSecondaryButton Whether the secondary mouse button (right-click) was pressed.
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
        properties.loadPaintProperties(model, model.getCurrentShape());
    }

    /**
     * This method is called when the mouse is dragged. It moves the currently selected shape based on
     * the movement of the mouse and updates the model.
     *
     * @param point The current mouse position.
     */
    @Override
    public void onMouseDragged(Point point) {
        if (model.getCurrentShape() == null) return;

        properties.loadPaintProperties(model, model.getCurrentShape());
        Shape shape = model.getCurrentShape();
        Point lastPoint = model.getLastPoint();
        shape.move(point.getX() - lastPoint.getX(), point.getY() - lastPoint.getY());
        model.update();
    }

    /**
     * This method is called when the mouse is released. It doesn't perform any action in select mode.
     *
     * @param point The point where the mouse was released.
     */
    @Override
    public void onMouseReleased(Point point) {
        // No-op
    }

    /**
     * Finalizes the current drawing process by adding the shape to the model and clearing the selected shape.
     * This is typically invoked when the user finishes selecting a shape to be moved.
     */
    public void finalizeShape() {
        Shape shape = model.getCurrentShape();
        model.addShape(shape);

        // Clean the cache on MOUSE_RELEASED
        if (shape != null) model.setCurrentShape(null);
    }
}
