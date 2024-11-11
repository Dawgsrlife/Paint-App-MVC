package ca.utoronto.utm.assignment2.paint.commandMenuBar;

import ca.utoronto.utm.assignment2.paint.*;
import ca.utoronto.utm.assignment2.paint.shapes.Point;
import ca.utoronto.utm.assignment2.paint.shapes.Shape;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;

import java.util.ArrayList;

/**
 * The CommandCopy class implements the Command interface to handle
 * copying the currently selected shape in the PaintModel. This class
 * creates a copy of the selected shape and adds it as a new shape
 * within the model, allowing for easy duplication of shapes.
 */
public class CommandCopy extends MenuItem implements Command {

    /**
     * Initializes a new CommandCopy MenuItem with the label "copy".
     */
    public CommandCopy() {
        super("copy");
    }

    /**
     * Executes the copy command. If a shape is currently selected in the model,
     * this method creates a copy of the shape's properties, coordinates,
     * and any associated points, then adds the new shape to the model.
     * This method utilizes a factory method in PaintStrategy to create
     * the appropriate shape type, ensuring it creates a new instance of the correct
     * type in the application.
     *
     * @param model      The PaintModel.
     * @param scene      The JavaFX Scene.
     * @param controller The PaintController.
     */
    @Override
    public void execute(PaintModel model, Scene scene, PaintController controller) {
        if (model.getCurrentShape() != null) {
            Shape oldShape = model.getCurrentShape();
            Point start = new Point(oldShape.getStart().getX(), oldShape.getStart().getY());
            Point end = new Point(oldShape.getEnd().getX(), oldShape.getEnd().getY());
            ArrayList<Point> points = new ArrayList<>();
            for (Point point : oldShape.getPoints()) {
                points.add(new Point(point.getX(), point.getY()));
            }
            PaintProperties newProperties = new PaintProperties(
                    oldShape.getProperties().isFilled(),
                    oldShape.getProperties().getFillColor(),
                    oldShape.getProperties().getStrokeColor(),
                    oldShape.getProperties().getStrokeThickness(),
                    oldShape.getProperties().getVertices());
            Shape newCopy = PaintStrategy.getPaintStrategy(
                    model.getCurrentShape().getType(),
                    start, end,
                    newProperties, points);
            model.setClipBoard(newCopy);
        }
    }
}
