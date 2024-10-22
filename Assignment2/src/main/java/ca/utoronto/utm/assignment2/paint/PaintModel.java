package ca.utoronto.utm.assignment2.paint;

import java.util.ArrayList;
import java.util.Observable;

public class PaintModel extends Observable {
    private final ArrayList<Point> points = new ArrayList<>();
    private final ArrayList<Integer> lineBreaks = new ArrayList<>();

    private final ArrayList<Shape> shapes = new ArrayList<>();

    /**
     * Add a Shape instance into shapes list
     * @param shape a shape instance
     */
    public void addShape(Shape shape) {
        shapes.add(shape);
        this.setChanged();
        this.notifyObservers();
    }

    /**
     * Get all shapes queued to be painted
     * @return An array list of all the shapes
     */
    public ArrayList<Shape> getShapes() {
        return shapes;
    }

    public void addPoint(Point p) {
        this.points.add(p);
        this.setChanged();
        this.notifyObservers();
    }

    public ArrayList<Point> getPoints() {
        return points;
    }

    public void addLineBreak() {
        this.lineBreaks.add(points.size() - 1);
    }

    public ArrayList<Integer> getLineBreaks() {
        return lineBreaks;
    }
}