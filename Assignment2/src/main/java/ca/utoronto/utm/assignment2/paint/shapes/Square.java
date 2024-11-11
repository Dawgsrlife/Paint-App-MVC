package ca.utoronto.utm.assignment2.paint.shapes;

import ca.utoronto.utm.assignment2.paint.PaintProperties;

/**
 * The Square class represents a square shape, a special case of a polygon.
 * It extends the Polygon class and ensures that the shape always has 4 vertices,
 * regardless of any changes made to the properties of the square.
 * The square is defined by its start and end points and inherits paint properties
 * from its parent class.
 *
 * @author tianji61
 */
public class Square extends Polygon {

    /**
     * Constructor for creating a new square shape. This initializes the square
     * with the given start and end points, and applies the paint properties.
     * The number of vertices is set to 4, as squares have 4 vertices.
     *
     * @param start The starting point of the square.
     * @param end The ending point of the square.
     * @param pp The paint properties for the square.
     */
    public Square(Point start, Point end, PaintProperties pp) {
        super(start, end, pp);
        pp.setVertices(4);
        setType("Square");
    }

    /**
     * Overrides the setProperties method to ensure that the number of vertices
     * for a square is always set to 4, even if the paint properties are updated.
     * This ensures that any attempt to modify the number of vertices in a square
     * does not affect its inherent 4-vertex structure.
     *
     * @param properties The paint properties to apply to the square.
     */
    public void setProperties(PaintProperties properties) {
        properties.setVertices(4);
        super.setProperties(properties);
    }
}
