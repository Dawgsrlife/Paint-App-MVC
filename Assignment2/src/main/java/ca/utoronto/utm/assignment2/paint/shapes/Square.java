package ca.utoronto.utm.assignment2.paint.shapes;

import ca.utoronto.utm.assignment2.paint.PaintProperties;

public class Square extends Polygon {

    public Square(Point start, Point end, PaintProperties pp) {
        super(start, end, pp);
        pp.setVertices(4);
        setType("Square");
    }

    public void setProperties(PaintProperties properties) {
        properties.setVertices(4);
        super.setProperties(properties);
    }
}
