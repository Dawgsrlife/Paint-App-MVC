package ca.utoronto.utm.assignment2.paint;

import java.util.ArrayList;
import java.util.Observable;

public class PaintModel extends Observable {
    private final ArrayList<Shape> shapes = new ArrayList<>();
    private Shape tempShape;

    /**
     * Add a Shape instance into steps list
     * @param shape a shape instance
     */
    public void addShape(Shape shape) {
        tempShape = null;
        shapes.add(shape);
        this.setChanged();
        this.notifyObservers();
    }

    /*
    future undo functionality
    public void removeShape(Shape shape) {
        shapes.remove(shape);
        System.out.println("Shape removed: " + shape + ", Remaining shapes: " + shapes.size());
        this.setChanged();
        this.notifyObservers();
    }
    */

    /**
     * Get all steps queued to be painted
     * @return An array list of all the steps
     */
    public ArrayList<Shape> getShapes() {
        ArrayList<Shape> tempShapes = new ArrayList<>(shapes);
        tempShapes.add(tempShape);
        return tempShapes;
    }

    /**
     * Add a Shape instance into tempShapes list
     * for buffer indicating when mouse is not
     * released yet
     * @param shape a shape instance
     */
    public void addTempShape(Shape shape) {
        tempShape = shape;
        this.setChanged();
        this.notifyObservers();
    }
}