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
}
