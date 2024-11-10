package ca.utoronto.utm.assignment2.paint.commandMenuBar;

import ca.utoronto.utm.assignment2.paint.*;
import ca.utoronto.utm.assignment2.paint.shapes.Point;
import ca.utoronto.utm.assignment2.paint.shapes.Shape;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;

import java.util.ArrayList;

public class CommandCopy extends MenuItem implements Command {

    public CommandCopy() {
        super("copy");
    }

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
            model.setCurrentShape(newCopy);
            model.addShape(newCopy);
        }
    }
}
