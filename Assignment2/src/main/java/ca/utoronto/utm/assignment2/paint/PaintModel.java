package ca.utoronto.utm.assignment2.paint;

import java.util.ArrayList;
import java.util.Observable;

public class PaintModel extends Observable {
    private final ArrayList<Shape> shapes = new ArrayList<>();
    private final ArrayList<Shape> tempShapes = new ArrayList<>();

    /**
     * Add a Shape instance into steps list
     * @param shape a shape instance
     */
    public void addShape(Shape shape) {
        tempShapes.clear();
        shapes.add(shape);
        this.setChanged();
        this.notifyObservers();
    }

    /**
     * Get all steps queued to be painted
     * @return An array list of all the steps
     */
    public ArrayList<Shape> getShapes() {
        ArrayList<Shape> tempShapes = new ArrayList<>();
        tempShapes.addAll(shapes);
        tempShapes.addAll(this.tempShapes);
        return tempShapes;
    }

    /**
     * Add a Shape instance into tempShapes list
     * for buffer indicating when  mouse is not
     * released yet
     * @param shape a shape instance
     */
    public void addTempShape(Shape shape) {
        tempShapes.add(shape);
        this.setChanged();
        this.notifyObservers();
    }
}