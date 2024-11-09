package ca.utoronto.utm.assignment2.paint;

import java.util.ArrayList;

public class PaintStrategy {
    /**
     * this method returns a corresponding shape instance to mode
     * @param mode what mode is activated currently
     * @param start starting point
     * @param end ending point
     * @param pp paint properties
     * @param path stroke path
     * @return an instance of a shape instance
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
            case "PrecisionEraser" -> new PrecisionEraser(start, pp, path);
            case "Polygon" -> new Polygon(start, end, pp);
            case "Text" -> new Text(start, end, pp);
            default -> throw new IllegalArgumentException("Unknown mode: " + mode);
        };
    }

}
