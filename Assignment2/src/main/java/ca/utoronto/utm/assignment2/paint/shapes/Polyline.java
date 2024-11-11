package ca.utoronto.utm.assignment2.paint.shapes;

import ca.utoronto.utm.assignment2.paint.PaintProperties;

import java.util.ArrayList;

/**
 * The Polyline class represents a polyline shape, which is a series of connected points
 * that form a continuous line. This class extends the {@link Squiggle} class and adds
 * functionality to track whether the polyline has started or not.
 * <p>
 * A polyline is often used to draw a series of straight lines between multiple points.
 * </p>
 * @author mengale1
 */
public class Polyline extends Squiggle {
    private boolean started;

    /**
     * Constructs a Polyline object with the given starting point, paint properties,
     * and a list of points representing the path of the polyline.
     *
     * @param point The starting point of the polyline.
     * @param pp The paint properties associated with the polyline.
     * @param path An {@link ArrayList} of points representing the path of the polyline.
     */
    public Polyline(Point point, PaintProperties pp, ArrayList<Point> path) {
        super(point, pp, path);
        setType("Polyline");
        this.setFinalized(false);
    }

    /**
     * Checks if the polyline has started drawing.
     *
     * @return true if the polyline has started, false otherwise.
     */
    public boolean hasStarted() {
        return started;
    }

    /**
     * Sets the state of whether the polyline has started drawing.
     *
     * @param started The state to set. If true, the polyline is considered to have started.
     */
    public void setStarted(boolean started) {
        this.started = started;
    }
}
