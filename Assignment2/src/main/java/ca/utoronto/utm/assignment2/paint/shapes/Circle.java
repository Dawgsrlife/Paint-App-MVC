package ca.utoronto.utm.assignment2.paint.shapes;

import ca.utoronto.utm.assignment2.paint.PaintProperties;

/**
 * A class representing a circle on the canvas.
 * <p>
 * The Circle class extends the Oval class and overrides certain behaviors to ensure
 * that the shape is a perfect circle, defined by a radius derived from the distance
 * between its center and an endpoint.
 * </p>
 *
 * <p>
 * Note: This class relies on a potentially problematic interpretation of
 * radius and center, as it does not account for the possibility that the start
 * and end points might yield an ellipse. Ensure only appropriate use where
 * true circular behavior is expected.
 * </p>
 *
 * @see Oval
 * @see Point
 * @see PaintProperties
 * @author tianji61
 */
public class Circle extends Oval {

    /**
     * Creates a new Circle with the specified center and endpoint, along with
     * its paint properties.
     *
     * @param centre The center point of the circle.
     * @param end The endpoint used to determine the radius.
     * @param pp Paint properties that specify the appearance of the circle.
     */
    public Circle(Point centre, Point end, PaintProperties pp) {
        super(centre, end, pp);
        this.setType("Circle");
    }

    /**
     * Returns an array containing the coordinates and dimensions necessary to
     * paint this circle on the canvas. The method calculates the radius as the
     * Euclidean distance between the center and endpoint, ensuring the shape
     * maintains a circular form.
     *
     * @return A double array where the elements represent:
     *         - The x-coordinate of the top-left corner of the bounding box
     *         - The y-coordinate of the top-left corner of the bounding box
     *         - The width of the bounding box (diameter of the circle)
     *         - The height of the bounding box (equal to the width for a circle)
     */
    @Override
    public double[] getPaintInfo() {
        // Problematic notion of radius and centre!!
        double radius = Math.sqrt(Math.pow(getStart().getX() - getEnd().getX(), 2) +
                        Math.pow(getStart().getY() - getEnd().getY(), 2));
        double x = getStart().getX() - radius;
        double y = getStart().getY() - radius;
        return new double[]{x, y, 2 * radius, 2 * radius};
    }
}
