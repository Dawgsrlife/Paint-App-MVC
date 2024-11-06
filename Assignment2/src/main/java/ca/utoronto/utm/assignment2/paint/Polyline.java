package ca.utoronto.utm.assignment2.paint;

import java.util.ArrayList;

public class Polyline extends Squiggle {

    public Polyline(Point point, PaintProperties pp, ArrayList<Point> path) {
        super(point, pp, path);
        setType("Polyline");
    }
}
