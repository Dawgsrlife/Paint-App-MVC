package ca.utoronto.utm.assignment2.paint.shapes;

import ca.utoronto.utm.assignment2.paint.PaintProperties;

public class Square extends Polygon {

    public Square(Point start, Point end, PaintProperties pp) {
        super(start, end, pp);
        pp.setVertices(4);
        setType("Square");
    }

    /**
     * An override to make sure vertices property change would not reflect to square,
     * which is a special instance of polygon.
     * @param properties paint details
     */
    public void setProperties(PaintProperties properties) {
        properties.setVertices(4);
        super.setProperties(properties);
    }
}
