package ca.utoronto.utm.assignment2.paint;

import java.util.ArrayList;

public class Polyline extends Squiggle {
    private boolean isFinalized;

    public Polyline(Point point, PaintProperties pp, ArrayList<Point> path) {
        super(point, pp, path);
        setType("Polyline");
        isFinalized = false;
    }
}
