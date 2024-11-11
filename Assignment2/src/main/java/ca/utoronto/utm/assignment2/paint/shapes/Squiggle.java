package ca.utoronto.utm.assignment2.paint.shapes;

import ca.utoronto.utm.assignment2.paint.PaintProperties;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.Ellipse;

import java.util.ArrayList;

/**
 * This class represents a squiggle on the canvas, which is
 * an ordered set of points connected to each by a line.
 * The points are defined on every left click drag of the mouse
 * or release of the left mouse button.
 *
 * @author huaethan
 */
public class Squiggle extends Shape {
    // Special field for lines
    private final ArrayList<Point> points = new ArrayList<>();

    /**
     * Constructs a new Squiggle with the given starting point, paint properties,
     * and optional additional path points.
     *
     * @param point The starting point of the squiggle.
     * @param pp The paint properties for the squiggle's appearance.
     * @param path Additional points to define the squiggle's path.
     */
    public Squiggle(Point point, PaintProperties pp, ArrayList<Point> path) {
        super(point, point, "Squiggle", pp);
        points.add(point);
        if (path != null) this.points.addAll(path);
    }

    /**
     * Paints the squiggle on the canvas by drawing lines between successive points.
     *
     * @param g2d The graphics context used for painting.
     */
    @Override
    public void paint(GraphicsContext g2d) {
        g2d.setStroke(getProperties().getStrokeColor());
        g2d.setLineWidth(getProperties().getStrokeThickness());
        for (int i = 0; i < points.size() - 1; i++) {
            Point p1 = points.get(i);
            Point p2 = points.get(i + 1);
            g2d.strokeLine(p1.getX(), p1.getY(), p2.getX(), p2.getY());
        }
    }

    /**
     * Fills the squiggle shape. Currently does nothing, as squiggles are not filled.
     *
     * @param g2d The graphics context used for filling.
     */
    @Override
    public void fill(GraphicsContext g2d) {

    }

    /**
     * Returns an empty array as there is no additional paint information for squiggles.
     *
     * @return An empty array.
     */
    @Override
    public double[] getPaintInfo() {
        return new double[0];
    }

    /**
     * Determines whether the cursor is within the squiggle path by checking its points.
     *
     * @param p The point representing the cursor location.
     * @return True if the cursor is within the squiggle, otherwise false.
     */
    @Override
    public boolean includeCursor(Point p) {
        for (Point point : points) {
            Ellipse strokePoint = new Ellipse(point.getX(), point.getY(), getProperties().getStrokeThickness(), getProperties().getStrokeThickness());
            if (strokePoint.contains(p.getX(), p.getY())) {
                System.out.println("in");
                return true;
            }
        }
        return false;
    }

    /**
     * Returns the list of points that define the squiggle's path.
     *
     * @return The list of points.
     */
    public ArrayList<Point> getPoints() {
        return points;
    }

    /**
     * Adds the end point to the squiggle and updates the end point in the shape.
     *
     * @param end The new end point of the squiggle.
     */
    @Override
    public void setEnd(Point end) {
        points.add(end);
        super.setEnd(end);
    }

    /**
     * Returns a string representation of the squiggle, including its start point
     * and all points in the path.
     *
     * @return A string representation of the squiggle's points.
     */
    @Override
    public String toString() {
        StringBuilder path = new StringBuilder();
        for (Point p : getPoints()) {
            if (!p.equals(getStart())) path.append(",").append(p);
        }
        return super.toString() + path;
    }

    /**
     * Moves all points of the squiggle by the specified offsets.
     *
     * @param dx The change in the x-coordinate.
     * @param dy The change in the y-coordinate.
     */
    @Override
    public void move(double dx, double dy) {
        for (Point p : getPoints()) {
            p.setX(p.getX() + dx);
            p.setY(p.getY() + dy);
        }
    }
}
