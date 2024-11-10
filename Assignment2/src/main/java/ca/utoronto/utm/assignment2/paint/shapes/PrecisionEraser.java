package ca.utoronto.utm.assignment2.paint.shapes;

import ca.utoronto.utm.assignment2.paint.PaintProperties;
import ca.utoronto.utm.assignment2.paint.PaintView;

import java.util.ArrayList;

public class PrecisionEraser extends Squiggle {

    public PrecisionEraser(Point point, PaintProperties pp, ArrayList<Point> path) {
        super(point, pp, path);
        pp.setStrokeColor(PaintView.BACKGROUND_COLOR);
        setType("PrecisionEraser");
    }
}
