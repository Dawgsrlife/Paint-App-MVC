package ca.utoronto.utm.assignment2.paint;

public class Point{
    protected double x, y; // Available to our package

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return x + "," + y;
    }
}
