package ca.utoronto.utm.assignment2.paint;

import javafx.scene.canvas.GraphicsContext;

import java.util.ArrayList;

public class Polygon extends Shape {

    public Polygon(Point start, Point end, PaintProperties pp) {
        super(start, end, "Polygon", pp);
    }

    @Override
    void paint(GraphicsContext g2d) {
        if (getProperties().getStrokeThickness() != 0.0) {
            g2d.setStroke(getProperties().getStrokeColor());
            g2d.setLineWidth(getProperties().getStrokeThickness());
            ArrayList<double[]> info = getPaintInfo(0);
            g2d.strokePolygon(info.get(0), info.get(1), getProperties().getVertices());
        }
        if (getProperties().isFilled()) {
            fill(g2d);
        }
    }

    @Override
    protected void fill(GraphicsContext g2d) {
        g2d.setFill(getProperties().getFillColor());
        ArrayList<double[]> info = getPaintInfo(getProperties().getStrokeThickness() / 2);
        g2d.fillPolygon(info.get(0), info.get(1), getProperties().getVertices());
    }

    @Override
    public double[] getPaintInfo() {
        double radius = Math.sqrt(Math.pow(getStart().x - getEnd().x, 2) +
                Math.pow(getStart().y - getEnd().y, 2));
        double theta = Math.atan((getStart().y - getEnd().y) / (getStart().x - getEnd().x));
        return new double[]{getStart().x, getStart().y, radius, theta};
    }

    public ArrayList<double[]> getPaintInfo(double border) {
        ArrayList<double[]> result = new ArrayList<>();
        result.add(new double[getProperties().getVertices()]);
        result.add(new double[getProperties().getVertices()]);
        double[] info = getPaintInfo();
        for (int i = 0; i < getProperties().getVertices(); i++) {
            result.get(0)[i] = info[0]+ (info[2] - border) * Math.cos(info[3] + (Math.PI * 2) * i / getProperties().getVertices());
            result.get(1)[i] = info[1] + (info[2] - border) * Math.sin(info[3] + (Math.PI * 2) * i / getProperties().getVertices());
        }
        return result;
    }

    @Override
    boolean includeCursor(Point p) {
        javafx.scene.shape.Polygon outer = getFxPolygon(- getProperties().getStrokeThickness() / 2);
        if (getProperties().isFilled()) {
            return outer.contains(p.x, p.y);
        }
        javafx.scene.shape.Polygon inner = getFxPolygon(getProperties().getStrokeThickness() / 2);
        return outer.contains(p.x, p.y) & !inner.contains(p.x, p.y);
    }

    private javafx.scene.shape.Polygon getFxPolygon(double border) {
        ArrayList<double[]> info = getPaintInfo(border);
        double[] vertices = new double[info.get(0).length * 2];
        int count = 0;
        for (int i = 0; i < vertices.length; i += 2) {
            vertices[i] = info.get(0)[count];
            vertices[i + 1] = info.get(1)[count];
            count++;
        }
        return new javafx.scene.shape.Polygon(vertices);
    }
}
