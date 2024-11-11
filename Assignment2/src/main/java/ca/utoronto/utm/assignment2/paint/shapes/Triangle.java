package ca.utoronto.utm.assignment2.paint.shapes;

import ca.utoronto.utm.assignment2.paint.PaintProperties;

/**
 * A class representing a triangle on the canvas
 *
 * @author mengale1
 */
public class Triangle extends Polygon {
    public Triangle(Point start, Point end, PaintProperties pp) {
        super(start, end, pp);
        pp.setVertices(3);
        setType("Triangle");
    }

    /**
     * Overrides the setProperties method to ensure that the number of vertices
     * for a triangle is always set to 3, even if the paint properties are updated.
     * This ensures that any attempt to modify the number of vertices in a triangle
     * does not affect its inherent 3-vertex structure.
     *
     * @param properties The paint properties to apply to the triangle.
     */
    @Override
    public void setProperties(PaintProperties properties) {
        properties.setVertices(3);
        super.setProperties(properties);
    }
}
