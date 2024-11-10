package ca.utoronto.utm.assignment2.paint.shapes;

import ca.utoronto.utm.assignment2.paint.PaintProperties;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public class Highlighter extends Squiggle {
    public Highlighter(Point point, PaintProperties pp, ArrayList<Point> path) {
        super(point, pp, path);
        pp.setStrokeColor(new Color(1, 1, 0.4, 0.3));
        setType("Highlighter");
    }
}
