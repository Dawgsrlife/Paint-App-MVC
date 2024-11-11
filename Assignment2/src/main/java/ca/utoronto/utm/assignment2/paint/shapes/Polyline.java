package ca.utoronto.utm.assignment2.paint.shapes;

import ca.utoronto.utm.assignment2.paint.PaintProperties;

import java.util.ArrayList;

public class Polyline extends Squiggle {
    private boolean started;

    public Polyline(Point point, PaintProperties pp, ArrayList<Point> path) {
        super(point, pp, path);
        setType("Polyline");

        this.setFinalized(false);
    }

    public boolean hasStarted() {
        return started;
    }

    public void setStarted(boolean started) {
        this.started = started;
    }
}