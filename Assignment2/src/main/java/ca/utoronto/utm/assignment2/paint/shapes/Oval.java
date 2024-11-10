package ca.utoronto.utm.assignment2.paint.shapes;

import ca.utoronto.utm.assignment2.paint.PaintProperties;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.Ellipse;

public class Oval extends Shape {

    public Oval(Point start, Point end, PaintProperties pp) {
        super(start, end, "Oval", pp);
    }

    @Override
    public void paint(GraphicsContext g2d) {
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
        double startX = Math.min(getStart().getX(), getEnd().getX());
        double startY = Math.min(getStart().getY(), getEnd().getY());
        double width = Math.abs(getEnd().getX() - getStart().getX());
        double height = Math.abs(getEnd().getY() - getStart().getY());
        return new double[]{startX, startY, width, height};
    }

    @Override
    public boolean includeCursor(Point p) {
        double[] info = getPaintInfo();
        Ellipse outer = new Ellipse(info[0] + (info[2] / 2), info[1] + (info[3] / 2),
                info[2] / 2 + getProperties().getStrokeThickness() / 2,
                info[3] / 2 + getProperties().getStrokeThickness() / 2);
        if (getProperties().isFilled()) {
            return outer.contains(p.getX(), p.getY());
        }
        Ellipse inner = new Ellipse(info[0] + (info[2] / 2), info[1] + (info[3] / 2),
                info[2] / 2 - getProperties().getStrokeThickness() / 2,
                info[3] / 2 - getProperties().getStrokeThickness() / 2);
        return outer.contains(p.getX(), p.getY()) & !inner.contains(p.getX(), p.getY());
    }

    @Override
    public void move(double dx, double dy) {
        getStart().setX(getStart().getX() + dx);
        getStart().setY(getStart().getY() + dy);
        getEnd().setX(getEnd().getX() + dx);
        getEnd().setY(getEnd().getY() + dy);
    }
}
