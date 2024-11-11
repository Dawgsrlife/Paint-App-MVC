package ca.utoronto.utm.assignment2.paint;

import ca.utoronto.utm.assignment2.paint.controlPanels.PropertiesPanel;
import ca.utoronto.utm.assignment2.paint.shapes.Point;
import ca.utoronto.utm.assignment2.paint.shapes.Shape;
import ca.utoronto.utm.assignment2.paint.shapes.Polyline;

/**
 * A class implementing the mode strategy for drawing a polyline on the canvas.
 * <p>
 * This strategy allows the user to interactively draw a polyline by clicking to
 * add points. Primary clicks add points to the polyline, while a secondary click
 * finalizes the polyline and ends the interaction.
 * </p>
 *
 * @see DrawModeStrategy
 * @see ModeStrategy
 * @see Polyline
 * @see PaintModel
 * @author mengale1
 */
public class PolylineModeStrategy extends DrawModeStrategy implements ModeStrategy {
    Polyline polyline;

    /**
     * Creates a new PolylineModeStrategy instance.
     *
     * @param model The PaintModel representing the canvas and shapes.
     * @param mode The current drawing mode as a String.
     * @param pp The properties panel used to configure shape properties.
     */
    public PolylineModeStrategy(PaintModel model, String mode, PropertiesPanel pp) {
        super(model, mode, pp);
        polyline = (Polyline)model.getCurrentShape();
    }

    /**
     * Called when the mouse is moved over the canvas.
     * Currently, this method performs no action, but it could be used in the
     * future to add a polyline trail.
     *
     * @param point The current mouse position.
     */
    @Override
    public void onMouseMoved(Point point) {
        // No-op
        // Maybe add a Polyline trail to here later!
    }

    /**
     * Called when the mouse button is pressed on the canvas. This method allows
     * the user to start or continue drawing the polyline, depending on whether
     * it has started.
     * <p>
     * Primary button:
     * - Starts a new polyline or continues it by adding a new point.
     * Secondary button:
     * - Finalizes the polyline.
     * </p>
     *
     * @param point The point where the mouse was pressed.
     * @param isPrimaryButton Indicates if the primary mouse button was pressed.
     * @param isSecondaryButton Indicates if the secondary mouse button was pressed.
     */
    @Override
    public void onMousePressed(Point point, boolean isPrimaryButton, boolean isSecondaryButton) {
        // Disable the ability to do anything by pressing both buttons
        if (isPrimaryButton && isSecondaryButton) return;

        if (polyline == null || !polyline.hasStarted()) {
            super.onMousePressed(point, isPrimaryButton, isSecondaryButton);
            polyline = (Polyline)model.getCurrentShape();
            polyline.setStarted(true);
        } else if (polyline.hasStarted()) {
            if (isPrimaryButton) {
                polyline.setEnd(point);
                model.addTempShape(polyline);
            } else if (isSecondaryButton) {
                polyline.finalizeShape();
                finalizeShape();  // from superclass
                polyline.setFinalized(true);  // After ending a Polyline, mark it finalized
            }
        }
    }

    /**
     * Called when the mouse is dragged over the canvas.
     * Currently, this method performs no action.
     *
     * @param point The point where the mouse was dragged.
     */
    @Override
    public void onMouseDragged(Point point) {
        // No-op
    }

    /**
     * Called when the mouse button is released on the canvas.
     * Currently, this method performs no action.
     *
     * @param point The point where the mouse was released.
     */
    @Override
    public void onMouseReleased(Point point) {
        // No-op
    }
}
