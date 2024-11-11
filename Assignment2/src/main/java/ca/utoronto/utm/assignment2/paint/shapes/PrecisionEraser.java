package ca.utoronto.utm.assignment2.paint.shapes;

import ca.utoronto.utm.assignment2.paint.PaintProperties;
import ca.utoronto.utm.assignment2.paint.PaintView;

import java.util.ArrayList;

/**
 * The PrecisionEraser class represents a special type of squiggle used as an eraser tool
 * for precise drawing. It inherits from the {@link Squiggle} class and uses the background
 * color as the stroke color to erase portions of the canvas. The path of the eraser is
 * represented as a series of connected points.
 * <p>
 * The PrecisionEraser is used for detailed and controlled erasing within a drawing area.
 * </p>
 * @author mengale1
 */
public class PrecisionEraser extends Squiggle {

    /**
     * Constructs a PrecisionEraser object with the given starting point, paint properties,
     * and a list of points representing the path of the eraser.
     * The stroke color is set to the background color for erasing functionality.
     *
     * @param point The starting point of the eraser.
     * @param pp The paint properties associated with the eraser.
     * @param path An {@link ArrayList} of points representing the path of the eraser.
     */
    public PrecisionEraser(Point point, PaintProperties pp, ArrayList<Point> path) {
        super(point, pp, path);
        pp.setStrokeColor(PaintView.BACKGROUND_COLOR);  // Set stroke colour to background colour for erasing
        setType("PrecisionEraser");
    }
}
