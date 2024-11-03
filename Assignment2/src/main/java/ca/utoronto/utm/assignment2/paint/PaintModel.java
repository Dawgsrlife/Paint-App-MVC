package ca.utoronto.utm.assignment2.paint;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Stack;

public class PaintModel extends Observable {
    private final Stack<Shape> shapes = new Stack<>();
    private Stack<Shape> undoStack = new Stack<>();
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

    public void undo() {
        if(shapes.isEmpty()) return;
        undoStack.push(shapes.pop());
        this.setChanged();
        this.notifyObservers();
    }

    public void redo() {
        if(undoStack.isEmpty()) return;
        shapes.push(undoStack.pop());
        this.setChanged();
        this.notifyObservers();
    }

    /**
     * Get all steps queued to be painted
     * @return An array list of all the steps
     */
    public ArrayList<Shape> getShapes() {
        ArrayList<Shape> tempShapes = new ArrayList<>(shapes);
        tempShapes.add(tempShape);
        return tempShapes;
    }

    public Shape getTempShape() {
        return tempShape;
    }

    /**
     * Add a Shape instance into tempShapes list
     * for buffer indicating when mouse is not
     * released yet
     * @param shape a shape instance
     */
    public void addTempShape(Shape shape) {
        tempShape = shape;

        // refresh the undo stack
        undoStack.clear();

        this.setChanged();
        this.notifyObservers();
    }
}