package ca.utoronto.utm.assignment2.paint.shapes;

import ca.utoronto.utm.assignment2.paint.PaintProperties;
import javafx.scene.paint.Color;

import java.util.ArrayList;

/**
 * A class representing a highlighter tool used for drawing translucent squiggles on the canvas.
 * <p>
 * The Highlighter extends the Squiggle class with a default translucent yellow color to mimic the
 * effect of a physical highlighter. This is achieved by adjusting the alpha value of the color
 * to give it a semi-transparent look.
 * </p>
 *
 * @see Squiggle
 * @see PaintProperties
 * @author tianji61
 */
public class Highlighter extends Squiggle {
    /**
     * Constructs a new Highlighter instance with the specified starting point, paint properties,
     * and path of points.
     * <p>
     * The stroke color of the paint properties is set to a translucent yellow to visually distinguish
     * it as a highlighter.
     * </p>
     *
     * @param point The starting point of the highlighter path.
     * @param pp The PaintProperties object containing stroke and fill settings.
     * @param path An ArrayList of Points representing the path of the highlighter stroke.
     */
    public Highlighter(Point point, PaintProperties pp, ArrayList<Point> path) {
        super(point, pp, path);
        pp.setStrokeColor(new Color(1, 1, 0.4, 0.3));
        setType("Highlighter");
    }
}
