package ca.utoronto.utm.assignment2.paint;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Polygon extends Shape {

    public Polygon(Point start, Point end, PaintProperties pp) {
        super(start, end, "Polygon", pp);
    }

    @Override
    void paint(GraphicsContext g2d) {
        double centerX = getStart().x;
        double centerY = getStart().y;
        double r = Math.sqrt(Math.pow(getStart().x - getEnd().x, 2) +
                Math.pow(getStart().y - getEnd().y, 2));

        double theta = Math.atan((getStart().y - getEnd().y) / (getStart().x - getEnd().x));

        double x1 = centerX + r * Math.cos(theta + 0);
        double y1 = centerY + r * Math.sin(theta + 0);

        double x2 = centerX + r * Math.cos(theta + Math.PI * 2 /5);
        double y2 = centerY + r * Math.sin(theta + Math.PI * 2 /5);

        double x3 = centerX + r * Math.cos(theta + Math.PI * 4 /5);
        double y3 = centerY + r * Math.sin(theta + Math.PI * 4 /5);

        double x4 = centerX + r * Math.cos(theta + Math.PI * 6 /5);
        double y4 = centerY + r * Math.sin(theta + Math.PI * 6 /5);

        double x5 = centerX + r * Math.cos(theta + Math.PI * 8 /5);
        double y5 = centerY + r * Math.sin(theta + Math.PI * 8 /5);
        double[] xPoints = new double[]{x1, x2, x3, x4, x5};
        double[] yPoints = new double[]{y1, y2, y3, y4, y5};
        g2d.setFill(Color.BLACK);
        g2d.fillPolygon(xPoints, yPoints, 5);
    }

    @Override
    protected void fill(GraphicsContext g2d) {
    }

    @Override
    public double[] getPaintInfo() {
        for (int i = 0; i < getProperties().getVertices(); i++) {

        }
        return new double[]{getStart().x, getStart().y};
    }

    @Override
    boolean includeCursor(Point p) {
        return false;
    }
}
