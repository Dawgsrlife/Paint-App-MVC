package ca.utoronto.utm.assignment2.paint;

import java.util.ArrayList;
import java.util.Observable;

public class PaintModel extends Observable {
    private final ArrayList<Point> points = new ArrayList<>();
    private final ArrayList<Integer> lineBreaks = new ArrayList<>();
    private final ArrayList<Circle> circles = new ArrayList<>();
    private final ArrayList<Rectangle> rectangles = new ArrayList<>();
    private final ArrayList<Square> squares = new ArrayList<>();


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

    /**
     * Add a new square instance in model, notifying observers.
     * @param s is the Square instance to be added
     */
    public void addSquare(Square s) {
        this.squares.add(s);
        this.setChanged();
        this.notifyObservers();
    }

    /**
     * Retrieves all Square instances that have been added to the model.
     * @return an ArrayList containing all the Square instances.
     **/
    public ArrayList<Square> getSquares() {
        return squares;
    }
}