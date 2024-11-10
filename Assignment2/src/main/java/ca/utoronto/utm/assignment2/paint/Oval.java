package ca.utoronto.utm.assignment2.paint;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.Ellipse;

public class Oval extends Shape {

    public Oval(Point start, Point end, PaintProperties pp) {
        super(start, end, "Oval", pp);
    }

    @Override
    void paint(GraphicsContext g2d) {
        if (getProperties().getStrokeThickness() != 0.0) {
            g2d.setStroke(getProperties().getStrokeColor());
            g2d.setLineWidth(getProperties().getStrokeThickness());
            double[] info = getPaintInfo();
            g2d.strokeOval(info[0], info[1], info[2], info[3]);
        }
        if (getProperties().isFilled()) {
            fill(g2d);
        }
    }

    @Override
    protected void fill(GraphicsContext g2d) {
        g2d.setFill(getProperties().getFillColor());
        double[] info = getPaintInfo();
        double width = getProperties().getStrokeThickness() / 2;
        g2d.fillOval(info[0] + width, info[1] + width,
                info[2] - width * 2, info[3] - width * 2);
    }

    @Override
    protected double[] getPaintInfo() {
        double startX = Math.min(getStart().x, getEnd().x);
        double startY = Math.min(getStart().y, getEnd().y);
        double width = Math.abs(getEnd().x - getStart().x);
        double height = Math.abs(getEnd().y - getStart().y);
        return new double[]{startX, startY, width, height};
    }

    @Override
    boolean includeCursor(Point p) {
        double[] info = getPaintInfo();
        Ellipse outer = new Ellipse(info[0] + (info[2] / 2), info[1] + (info[3] / 2),
                info[2] / 2 + getProperties().getStrokeThickness() / 2,
                info[3] / 2 + getProperties().getStrokeThickness() / 2);
        if (getProperties().isFilled()) {
            return outer.contains(p.x, p.y);
        }
        Ellipse inner = new Ellipse(info[0] + (info[2] / 2), info[1] + (info[3] / 2),
                info[2] / 2 - getProperties().getStrokeThickness() / 2,
                info[3] / 2 - getProperties().getStrokeThickness() / 2);
        return outer.contains(p.x, p.y) & !inner.contains(p.x, p.y);
    }
}
