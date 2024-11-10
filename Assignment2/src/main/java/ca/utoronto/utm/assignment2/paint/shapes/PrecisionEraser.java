package ca.utoronto.utm.assignment2.paint.shapes;

import ca.utoronto.utm.assignment2.paint.PaintProperties;
import ca.utoronto.utm.assignment2.paint.PaintView;
import javafx.scene.canvas.GraphicsContext;

import java.util.ArrayList;

public class PrecisionEraser extends Squiggle {
    private double eraserSize = 10.0;

    public PrecisionEraser(Point point, PaintProperties pp, ArrayList<Point> path) {
        super(point, pp, path);
        setType("PrecisionEraser");
    }

    @Override
    public void paint(GraphicsContext g2d) {
        g2d.setStroke(PaintView.BACKGROUND_COLOR);

        for (int i = 0; i < getPoints().size() - 1; i++) {
            Point p1 = getPoints().get(i);
            Point p2 = getPoints().get(i + 1);
            g2d.strokeLine(p1.getX(), p1.getY(), p2.getX(), p2.getY());
        }
    }

    public void setEraserSize(double eraserSize) {
        this.eraserSize = eraserSize;
    }
}
