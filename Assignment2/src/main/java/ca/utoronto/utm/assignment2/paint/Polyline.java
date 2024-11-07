package ca.utoronto.utm.assignment2.paint;

import java.util.ArrayList;

public class Polyline extends Squiggle {
    private boolean isFinalized;

    public Polyline(Point point, PaintProperties pp, ArrayList<Point> path) {
        super(point, pp, path);
        setType("Polyline");
        isFinalized = false;
    }

    /**
     * Handles the behaviour of the Polyline when the mouse is moved.
     * <p>
     * The Polyline displays a temporary trail according to the mouse cursor.
     *
     * @param point
     */
    public void onMouseMoved(Point point) {
        // TODO: need to add this! (see docstring)
    }

    /**
     * Handles the behaviour of Polyline when the mouse is pressed.
     * <p>
     * Left clicks add new vertices to the Polyline.
     * Right clicks end the Polyline.
     *
     * @param point
     * @param isPrimaryButtonDown
     * @param isSecondaryButtonDown
     */
    @Override
    public void onMousePressed(Point point, boolean isPrimaryButtonDown, boolean isSecondaryButtonDown) {
        if (isPrimaryButtonDown && isSecondaryButtonDown) {
            // Do nothing
        } else if (isPrimaryButtonDown) {
            // Add an endpoint and signal displaying
            setEnd(point);
            setDisplayShape(true);
        } else if (isSecondaryButtonDown) {
            setCanFinalize(true);
        }
    }

    @Override
    public void onMouseDragged(Point point, boolean isPrimaryButtonDown, boolean isSecondaryButtonDown) {
        // No-op
    }

    @Override
    public void onMouseReleased(Point point) {
        // No-op
    }

    public void addPoint(Point point, boolean isFinal) {
        if (!isFinalized) {
            getPoints().add(point);
            setEnd(point);
            if (isFinal) {
                finalizeShape();
            }
        }
    }

    public void finalizeShape() {
        isFinalized = true;
    }

    public boolean isFinalized() {
        return isFinalized;
    }
}
