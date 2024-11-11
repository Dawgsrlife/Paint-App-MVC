package ca.utoronto.utm.assignment2.paint;

import ca.utoronto.utm.assignment2.paint.shapes.Point;

/**
 * Interface representing the strategy for handling different modes of interaction in the painting application.
 * <p>
 * This interface defines methods for handling mouse events during various drawing modes, such as drawing shapes or selecting tools.
 * Different modes of interaction (e.g., drawing, selecting, etc.) will implement this interface to define specific behaviors
 * for mouse movement, pressing, dragging, and releasing.
 * </p>
 *
 * @see DrawModeStrategy
 * @see PolylineModeStrategy
 * @author mengale1
 */
public interface ModeStrategy {
    /**
     * Handles the mouse moved event.
     * <p>
     * This method is called when the mouse is moved in the drawing area. The behavior varies depending on the active mode.
     * </p>
     *
     * @param point The point where the mouse is moved.
     */
    void onMouseMoved(Point point);

    /**
     * Handles the mouse pressed event.
     * <p>
     * This method is called when a mouse button is pressed. It is used to begin interactions, such as starting to draw a shape.
     * </p>
     *
     * @param point The point where the mouse was pressed.
     * @param isPrimaryButton Whether the primary (left) mouse button was pressed.
     * @param isSecondaryButton Whether the secondary (right) mouse button was pressed.
     */
    void onMousePressed(Point point, boolean isPrimaryButton, boolean isSecondaryButton);

    /**
     * Handles the mouse dragged event.
     * <p>
     * This method is called when the mouse is dragged across the drawing area. It updates the current shape being drawn.
     * </p>
     *
     * @param point The point where the mouse is dragged.
     */
    void onMouseDragged(Point point);

    /**
     * Handles the mouse released event.
     * <p>
     * This method is called when a mouse button is released. It is used to finalize the current interaction, such as completing a shape.
     * </p>
     *
     * @param point The point where the mouse was released.
     */
    void onMouseReleased(Point point);
}
