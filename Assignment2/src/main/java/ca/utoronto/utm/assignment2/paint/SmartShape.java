package ca.utoronto.utm.assignment2.paint;

import javafx.scene.canvas.GraphicsContext;

import java.util.ArrayList;

public class SmartShape extends Squiggle {
    final private double CLOSE_CONDITION = 10; // distance between first and last point
                                       // must be within this value
    final private double SLOPE_LENIENCE = 1.5; // the difference between the slopes to be considered a vertex
    final private double VERTEX_DISTANCE = 10; // defines the minimum distance between two vertices

    private boolean isSquiggle;

    public ArrayList<Point> tempPoints;
    public SmartShape(Point point, PaintProperties pp) {
        super(point, pp);
        isSquiggle = true;
        tempPoints = new ArrayList<>();
    }

    private double dist(Point a, Point b) {
        return Math.sqrt((a.y - b.y)*(a.y - b.y) + (a.x - b.x)*(a.x - b.x));
    }

    private double getSlope(Point a, Point b) {
        return (b.y - a.y)/(b.x - a.x);
    }

    @Override
    public void setEnd(Point point) {
        tempPoints.add(point);
        super.setEnd(point);
    }

    @Override
    public void paint(GraphicsContext g2d) {
        if(isSquiggle) {
            super.paint(g2d);
            return;
        }
        ArrayList<Point> points = this.getPoints();
        int numPoints = points.size();
        double[] xArr = new double[numPoints];
        double[] yArr = new double[numPoints];
        // System.out.println(numPoints);
        for(int i = 0; i < numPoints; i++) {
            xArr[i] = points.get(i).x;
            yArr[i] = points.get(i).y;
            // System.out.println(xArr[i] + " " + yArr[i]);
        }
        if(isFilled()) g2d.fillPolygon(xArr, yArr, numPoints);
        else g2d.fillPolygon(xArr, yArr, numPoints);
    }

    /**
     * Finalizes the shape by interpolating a polygon using
     * tempPoints
     *
     * The algorithm works as follows:
     * - Check if the last point and first point are close enough
     *   to each other. If not, this shape remains a squiggle
     * - If the condition above is true, then the squiggle should
     *   form a closed shape
     * - Iterate through the points, keeping track of the
     */
    @Override
    public void finalizeShape() {
        if(dist(tempPoints.getFirst(), tempPoints.getLast()) > CLOSE_CONDITION) return;
        ArrayList<Point> vertices = new ArrayList<>();
        Point currVertex = tempPoints.getFirst();
        for (int i = 0; i < tempPoints.size(); i++) {
            if(i == 0) continue;

            Point extremePoint = currVertex;
            double maxSlopeDiff = 0;
            for(int j = 1; j < i; j++) {
                double m0 = getSlope(currVertex, tempPoints.get(j));
                double m1 = getSlope(tempPoints.get(j), tempPoints.get(i));
                System.out.println(m0 + " " + m1);
                double currSlopeDiff = Math.abs(m0 - m1);
                if(currSlopeDiff > maxSlopeDiff) {
                    extremePoint = tempPoints.get(j);
                    maxSlopeDiff = currSlopeDiff;
                }
            }
            // System.out.println(extremePoint.x + " " + extremePoint.y);
            // System.out.println(maxSlopeDiff);
            if(maxSlopeDiff > SLOPE_LENIENCE &&
               dist(tempPoints.get(i), extremePoint) > VERTEX_DISTANCE &&
               dist(extremePoint, currVertex) > VERTEX_DISTANCE) {
                vertices.add(extremePoint);
                currVertex = extremePoint;
            }
        }
        if(Math.abs(
           getSlope(tempPoints.getLast(), tempPoints.getFirst()) -
           getSlope(tempPoints.getFirst(), vertices.getFirst())
        ) > SLOPE_LENIENCE) vertices.add(tempPoints.getFirst());

        this.getPoints().clear();
        for(Point p : vertices) {
            super.setEnd(p);
        }

        isSquiggle = false;
    }
}
