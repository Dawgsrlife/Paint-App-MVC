package ca.utoronto.utm.assignment2.paint.shapes;

/**
 * The Point class represents a 2D point with x and y coordinates.
 * <p>
 * This class provides methods to get and set the x and y coordinates of the point,
 * as well as a method to return a string representation of the point.
 * </p>
 * @author tianji61
 */
public class Point {
    private double x, y; // Available to our package

    /**
     * Constructs a Point object with the specified x and y coordinates.
     *
     * @param x The x-coordinate of the point.
     * @param y The y-coordinate of the point.
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Gets the x-coordinate of the point.
     *
     * @return The x-coordinate of the point.
     */
    public double getX() {
        return x;
    }

    /**
     * Sets the x-coordinate of the point.
     *
     * @param x The new x-coordinate of the point.
     */
    public void setX(double x) {
        this.x = x;
    }

    /**
     * Gets the y-coordinate of the point.
     *
     * @return The y-coordinate of the point.
     */
    public double getY() {
        return y;
    }

    /**
     * Sets the y-coordinate of the point.
     *
     * @param y The new y-coordinate of the point.
     */
    public void setY(double y) {
        this.y = y;
    }

    /**
     * Returns a string representation of the point in the format "x,y".
     *
     * @return A string representing the point.
     */
    @Override
    public String toString() {
        return x + "," + y;
    }
}
