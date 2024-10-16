package ca.utoronto.utm.assignment2.paint;

import java.util.ArrayList;
import java.util.Observable;

public class PaintModel extends Observable {
    private final ArrayList<Point> points = new ArrayList<>();
    private final ArrayList<Integer> lineBreaks = new ArrayList<>();
    private final ArrayList<Circle> circles = new ArrayList<>();
    private final ArrayList<Rectangle> rectangles = new ArrayList<>();


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

    public void addCircle(Circle c) {
        this.circles.add(c);
        this.setChanged();
        this.notifyObservers();
    }

    public ArrayList<Circle> getCircles() {
        return circles;
    }

    /**
     * Add a Rectangle into model list
     *
     * @param r a Rectangle instance
     */
    public void addRectangle(Rectangle r) {
        this.rectangles.add(r);
        this.setChanged();
        this.notifyObservers();
    }

    /**
     * Get all added Rectangle instances
     *
     * @return an ArrayList of added Rectangle instances
     */
    public ArrayList<Rectangle> getRectangles() {
        return rectangles;
    }
}
