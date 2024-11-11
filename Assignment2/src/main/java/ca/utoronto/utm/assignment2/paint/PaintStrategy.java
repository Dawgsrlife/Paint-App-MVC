package ca.utoronto.utm.assignment2.paint;

import ca.utoronto.utm.assignment2.paint.shapes.*;

import java.util.ArrayList;

/**
 * The PaintStrategy class provides a method to select and return an appropriate shape
 * based on the currently activated mode in the paint application.
 * <p>
 * The strategy pattern is used to instantiate different shapes based on user selection,
 * enabling the drawing of shapes such as circles, rectangles, polygons, text, and more.
 * This class encapsulates the logic for selecting the correct shape class and passing
 * the necessary parameters such as starting point, ending point, paint properties, and stroke path.
 * </p>
 * @author tianji61
 */
public class PaintStrategy {

    /**
     * This method returns a corresponding shape instance based on the currently activated mode.
     *
     * @param mode The current drawing mode (e.g., "Circle", "Rectangle").
     * @param start The starting point of the shape to be drawn.
     * @param end The ending point of the shape to be drawn (used in shapes like rectangles and ovals).
     * @param pp The paint properties for the shape (e.g., fill color, stroke color).
     * @param path The stroke path for shapes that require a path (e.g., squiggles, polylines).
     * @return An instance of the corresponding shape based on the mode.
     * @throws IllegalArgumentException If an unknown mode is provided.
     */
    public static Shape getPaintStrategy(String mode, Point start, Point end, PaintProperties pp, ArrayList<Point> path) {
        return switch (mode) {
            case "Circle" -> new Circle(start, end, pp);
            case "Rectangle" -> new Rectangle(start, end, pp);
            case "Square" -> new Square(start, end, pp);
            case "Oval" -> new Oval(start, end, pp);
            case "Triangle" -> new Triangle(start, end, pp);
            case "Squiggle" -> new Squiggle(start, pp, path);
            case "Polyline" -> new Polyline(start, pp, path);
            case "SmartShape" -> new SmartShape(start, pp, path);
            case "PrecisionEraser" -> new PrecisionEraser(start, pp, path);
            case "Polygon" -> new Polygon(start, end, pp);
            case "Text" -> new Text(start, end, pp);
            case "HighLighter" -> new Highlighter(start, pp, path);
            default -> throw new IllegalArgumentException("Unknown mode: " + mode);
        };
    }

}
