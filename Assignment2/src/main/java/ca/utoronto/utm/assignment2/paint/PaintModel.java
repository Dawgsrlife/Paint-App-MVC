package ca.utoronto.utm.assignment2.paint;

import java.util.ArrayList;
import java.util.Observable;

public class PaintModel extends Observable {
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

    /* future undo functionality
    public void removeShape(Shape shape) {
        shapes.remove(shape);
        System.out.println("Shape removed: " + shape + ", Remaining shapes: " + shapes.size());
        this.setChanged();
        this.notifyObservers();
    }
     */

    /**
     * Get all shapes queued to be painted
     * @return An array list of all the shapes
     */
    public ArrayList<Shape> getShapes() {
        return shapes;
    }
}