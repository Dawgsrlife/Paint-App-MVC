package ca.utoronto.utm.assignment2.paint;

import ca.utoronto.utm.assignment2.paint.shapes.Point;
import ca.utoronto.utm.assignment2.paint.shapes.Shape;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Stack;

/**
 * PaintModel is responsible for managing shapes in the paint application.
 * It maintains the stack of shapes, supports undo and redo operations,
 * and handles selection and temporary shape storage while drawing.
 * As an Observable, PaintModel notifies controllers and views of any changes
 */
public class PaintModel extends Observable {
    private final Stack<Shape> shapes = new Stack<>();
    private final Stack<Shape> undoStack = new Stack<>();
    private Shape currentShape;
    private Shape tempShape;
    private Shape clipBoard;
    private Point lastPoint = new Point(0, 0);

    /**
     * Get all steps queued to be painted
     *
     * @return An array list of all the steps
     */
    public ArrayList<Shape> getShapes() {
        ArrayList<Shape> tempShapes = new ArrayList<>(shapes);
        tempShapes.add(tempShape);
        return tempShapes;
    }

    /**
     * Returns the currently selected shape. This is for edit
     * function cut, copy and paste.
     *
     * @return The current shape in the model.
     */
    public Shape getCurrentShape() {
        return currentShape;
    }

    /**
     * Sets the currently selected shape.
     *
     * @param shape The shape to set as current.
     */
    public void setCurrentShape(Shape shape) {
        this.currentShape = shape;
    }

    /**
     * Returns the temporary shape being drawn.
     *
     * @return The temporary shape, if any.
     */
    public Shape getTempShape() {
        return tempShape;
    }

    /**
     * Returns the last point of the user's mouse on canvas.
     *
     * @return The last point.
     */
    public Point getLastPoint() {
        return lastPoint;
    }

    /**
     * Sets the last point for the drawing action.
     *
     * @param lastPoint The point to set as the last point.
     */
    public void setLastPoint(Point lastPoint) {
        this.lastPoint = lastPoint;
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

        update();
    }

    /**
     * Add a Shape instance into steps list
     * @param shape a shape instance
     */
    public void addShape(Shape shape) {
        tempShape = null;
        shapes.add(shape);
        update();
    }

    /**
     * Undoes the last action by moving the top shape from the main stack to the undo stack.
     */
    public void undo() {
        if(shapes.isEmpty()) return;
        undoStack.push(shapes.pop());
        update();
    }

    /**
     * Removes a specific shape from the main stack and adds it to the undo stack.
     *
     * @param shape The shape to remove.
     */
    public void undo(Shape shape) {
        shapes.remove(shape);
        undoStack.push(shape);
        update();
    }

    /**
     * Redo the last undone action by moving the top shape from the undo stack back to the main stack.
     */
    public void redo() {
        if(undoStack.isEmpty()) return;
        shapes.push(undoStack.pop());
        update();
    }

    /**
     * Clears all shapes from the main stack.
     */
    public void clear() {
        shapes.clear();
        update();
    }

    /**
     * Returns the shape under the cursor at a given point, if any.
     *
     * @param p The point to check.
     * @return The shape at the point, or null if none found.
     */
    public Shape getSelected(Point p) {
        for (int j = shapes.size() - 1; j >= 0; j--) {
            if (shapes.get(j).includeCursor(p)) {
                return shapes.get(j);
            }
        }
        return null;
    }

    /**
     * Notifies observers of changes to the model.
     */
    public void update() {
        this.setChanged();
        this.notifyObservers();
    }

    /**
     * Removes a specific shape from the main stack.
     *
     * @param selectedShape The shape to remove.
     */
    public void removeShape(Shape selectedShape) {
        shapes.remove(selectedShape);
        update();
    }

    public Shape getClipBoard() {
        return clipBoard;
    }

    public void setClipBoard(Shape clipBoard) {
        this.clipBoard = clipBoard;
    }
}