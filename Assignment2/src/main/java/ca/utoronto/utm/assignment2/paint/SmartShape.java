package ca.utoronto.utm.assignment2.paint;

import javafx.scene.canvas.GraphicsContext;

import java.util.ArrayList;

public class SmartShape extends Squiggle {
    final private double CLOSE_CONDITION = 10; // distance between first and last point
                                       // must be within this value
    final private double SLOPE_LENIENCE = 3 * Math.PI / 5; // 108 degrees in radians
    final private double VERTEX_DISTANCE = 20; // defines the minimum distance between two vertices

    private boolean isSquiggle;

    public ArrayList<Point> tempPoints;
    public SmartShape(Point point, PaintProperties pp) {
        super(point, pp);
        isSquiggle = true;
        tempPoints = new ArrayList<>();
    }

    /**
     * Gets the vector obtained from a - b
     * @param a First point, can be considered a vector
     * @param b Second point, can be considered a vector
     * @return the vector a - b
     */
    private Point getVector(Point a, Point b) {
        return new Point(a.x - b.x, a.y - b.y);
    }

    private double dist(Point a, Point b) {
        Point v = getVector(a,b);
        return Math.sqrt(v.x * v.x + v.y * v.y);
    }

    private double dist(Point a) {
        return dist(a, new Point(0,0));
    }

    private double getRelativeAngle(Point a, Point b, Point origin) {
        Point aVec = getVector(a,origin);
        Point bVec = getVector(b,origin);
        double normedDotProduct = (aVec.x * bVec.x + aVec.y * bVec.y)/(dist(aVec) * dist(bVec));
        return Math.acos(normedDotProduct);
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
        for(int i = 0; i < numPoints; i++) {
            xArr[i] = points.get(i).x;
            yArr[i] = points.get(i).y;
            // System.out.println(xArr[i] + " " + yArr[i]);
        }
        g2d.setStroke(getBorderColor());
        g2d.setLineWidth(getBorderWidth());
        g2d.strokePolygon(xArr, yArr, numPoints);

        if(isFilled()) g2d.fill();
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
        int currVertexIndex = 0;
        Point currVertex = tempPoints.get(currVertexIndex);
        for (int i = 0; i < tempPoints.size(); i++) {
            if(i == 0) continue;

            int extremePointIndex = currVertexIndex;
            Point extremePoint = tempPoints.get(currVertexIndex);
            double minAngle = Integer.MAX_VALUE;
            for(int j = currVertexIndex + 1; j < i; j++) {
                double currAngle = getRelativeAngle(currVertex, tempPoints.get(i), tempPoints.get(j));
                if(currAngle < minAngle &&
                   dist(tempPoints.get(i), tempPoints.get(j)) > VERTEX_DISTANCE &&
                   dist(tempPoints.get(j), currVertex) > VERTEX_DISTANCE) {
                    extremePointIndex = j;
                    extremePoint = tempPoints.get(j);
                    minAngle = currAngle;
                }
            }

            if(minAngle < SLOPE_LENIENCE) {
                System.out.println("Slope: " + minAngle);
                System.out.println("Coords: " + extremePoint.x + " " + extremePoint.y);
                System.out.println(i);
                vertices.add(extremePoint);
                currVertexIndex = extremePointIndex;
                currVertex = extremePoint;
            }
        }
        if(getRelativeAngle(vertices.getFirst(), vertices.getLast(), tempPoints.getFirst()) < SLOPE_LENIENCE)
            vertices.add(tempPoints.getFirst());

        this.getPoints().clear();
        for(Point p : vertices) {
            super.setEnd(p);
        }

        System.out.println("Vertices: " + vertices.size());
        System.out.println("Points: " + vertices);

        isSquiggle = false;
    }
}
