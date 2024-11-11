package ca.utoronto.utm.assignment2.paint;

import ca.utoronto.utm.assignment2.paint.shapes.Point;
import ca.utoronto.utm.assignment2.paint.shapes.Shape;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Stack;

/**
 * PaintModel is responsible for managing shapes in the paint application.
 * <p>
 * This class maintains the stack of shapes drawn on the canvas, supporting undo and redo operations,
 * as well as handling temporary shape storage while drawing. It allows for selection of shapes,
 * manipulation of shapes via cut, copy, and paste, and notifies observers of any changes.
 * </p>
 * As an Observable, PaintModel notifies the controller and view when changes are made to the model,
 * ensuring synchronization between the model and the UI.
 *
 * @see Shape
 * @see Point
 * @author tianji61 / huaethan
 */
public class PaintModel extends Observable {
    private final Stack<Shape> shapes = new Stack<>();
    private final Stack<Shape> undoStack = new Stack<>();
    private Shape currentShape;
    private Shape tempShape;
    private Shape clipBoard;
    private Point lastPoint = new Point(0, 0);

    /**
     * Get all shapes queued to be painted, including any temporary shape being drawn.
     *
     * @return An array list of all the shapes.
     */
    public ArrayList<Shape> getShapes() {
        ArrayList<Shape> tempShapes = new ArrayList<>(shapes);
        tempShapes.add(tempShape);
        return tempShapes;
    }

    /**
     * Returns the currently selected shape. This shape is used for edit functions such as cut, copy, and paste.
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
     * Returns the temporary shape being drawn by the user.
     *
     * @return The temporary shape, if any.
     */
    public Shape getTempShape() {
        return tempShape;
    }

    /**
     * Returns the last point recorded from the user's mouse on the canvas.
     *
     * @return The last point.
     */
    public Point getLastPoint() {
        return lastPoint;
    }

    /**
     * Sets the last point recorded from the user's mouse on the canvas.
     *
     * @param lastPoint The point to set as the last point.
     */
    public void setLastPoint(Point lastPoint) {
        this.lastPoint = lastPoint;
    }

    /**
     * Adds a shape to the temporary shape list, indicating that the shape is being drawn but has not been released.
     * It also clears the undo stack.
     *
     * @param shape The shape instance to add as the temporary shape.
     */
    public void addTempShape(Shape shape) {
        tempShape = shape;

        // refresh the undo stack
        undoStack.clear();

        update();
    }

    /**
     * Adds a shape to the main shape list once it has been finalized.
     *
     * @param shape The shape to add to the main list of shapes.
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
     * Redoes the last undone action by moving the top shape from the undo stack back to the main stack.
     */
    public void redo() {
        if(undoStack.isEmpty()) return;
        shapes.push(undoStack.pop());
        update();
    }

    /**
     * Clears all shapes from the main shape stack.
     */
    public void clear() {
        shapes.clear();
        update();
    }

    /**
     * Returns the shape under the cursor at a given point, if any.
     *
     * @param p The point to check.
     * @return The shape at the point, or null if no shape is found.
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

    /**
     * Gets the shape currently stored in the clipboard for cut/copy/paste operations.
     *
     * @return The shape in the clipboard.
     */
    public Shape getClipBoard() {
        return clipBoard;
    }

    /**
     * Sets the shape to be stored in the clipboard.
     *
     * @param clipBoard The shape to store in the clipboard.
     */
    public void setClipBoard(Shape clipBoard) {
        this.clipBoard = clipBoard;
    }
}
