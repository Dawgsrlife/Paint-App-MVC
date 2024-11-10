package ca.utoronto.utm.assignment2.paint;

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
            g2d.strokeLine(p1.x, p1.y, p2.x, p2.y);
        }
    }

    public void setEraserSize(double eraserSize) {
        this.eraserSize = eraserSize;
    }
}
