package ca.utoronto.utm.assignment2.paint;

import java.util.ArrayList;

public class SmartShape extends Squiggle {
    public ArrayList<Point> tempPoints;
    public SmartShape(Point point, PaintProperties pp) {
        super(point, pp);
        tempPoints = new ArrayList<>();
    }

    @Override
    public void setEnd(Point point) {
        tempPoints.add(point);
    }

    /**
     * Finalizes the shape by interpolating a polygon using
     * tempPoints
     *
     * The algorithm works as follows:
     * - Check if the last point and first point are close enough
     *   to each other. If not, this shape remains a squiggle
     * - If the condition above is true, then the squiggle should
     *   form a closed shape
     * - Then, find the "center" of the closed shape
     * - Iterate through tempPoints to figure out which points are furthest
     *   away from the center. This set of points will be the vertices
     *   of the shape
     * - A maximum of 5 points are recorded before the squiggle is no longer
     *   considered a shape
     */
    public void finalizeShape() {

    }
}
