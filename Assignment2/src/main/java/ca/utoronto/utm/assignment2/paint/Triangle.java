package ca.utoronto.utm.assignment2.paint;

import javafx.scene.canvas.GraphicsContext;

/**
 * A class representing a triangle on the canvas
 *
 * @author tianji61
 */
public class Triangle extends Shape {

    /**
     * Initialize an isosceles Triangle with two given coordinates
     *
     * @param start starting coordinate
     * @param end   ending coordinate
     */
    public Triangle(Point start, Point end, PaintProperties pp) {
        super(start, end, "Triangle", true,
                pp.getFillColor(), pp.getBorderColor(), pp.getBorderWidth());
    }

    /**
     * Paint the triangle
     * @param g2d
     */
    @Override
    void paint(GraphicsContext g2d) {
        // Grab points:
        double[] xPoints = getXCoordinates();
        double[] yPoints = getYCoordinates();

        // Fill the triangle with the border colour:
        g2d.fillPolygon(xPoints, yPoints, 3);

        // If not filled, remove inner fill colour:
        if (!isFilled()) {
            removeFilled(g2d);
        }
    }

    @Override
    void removeFilled(GraphicsContext g2d) {
        g2d.setFill(getColor());
        double width = getBorderWidth();
        double[] xPoints = getXCoordinates(width);
        double[] yPoints = getYCoordinates(width);
        g2d.fillPolygon(xPoints, yPoints, 3);
    }

    @Override
    double[] getPaintInfo() {
        double startX = Math.min(getStart().x, getEnd().x);
        double startY = Math.min(getStart().y, getEnd().y);
        double width = Math.abs(getEnd().x - getStart().x);
        double height = Math.abs(getEnd().y - getStart().y);
        return new double[]{startX, startY, width, height};
    }

    private double[] getXCoordinates() {
        double[] info = getPaintInfo();
        double startX = info[0];
        double width = info[2];
        return new double[] {
                startX + width / 2,  // Top point (middle of the bounding box)
                startX,              // Bottom-left corner
                startX + width       // Bottom-right corner
        };
    }

    private double[] getYCoordinates() {
        double[] info = getPaintInfo();
        double startY = info[1];
        double height = info[3];
        return new double[] {
                startY,           // Top point (top of the bounding box)
                startY + height,  // Bottom-left corner
                startY + height   // Bottom-right corner
        };
    }

    private double[] getXCoordinates(double borderWidth) {
        double[] info = getPaintInfo();
        double startX = info[0] + borderWidth;
        double width = info[2] - 2 * borderWidth;
        return new double[]{
                startX + width / 2, // Top point
                startX,             // Bottom-left point
                startX + width      // Bottom-right point
        };
    }

    private double[] getYCoordinates(double borderWidth) {
        double[] info = getPaintInfo();
        double startY = info[1] + borderWidth;
        double height = info[3] - 2 * borderWidth;
        return new double[]{
                startY,             // Top point
                startY + height,    // Bottom-left point
                startY + height     // Bottom-right point
        };
    }
}
