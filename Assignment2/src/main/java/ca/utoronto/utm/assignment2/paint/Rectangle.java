package ca.utoronto.utm.assignment2.paint;

import javafx.scene.canvas.GraphicsContext;

/**
 * A class representing a rectangle on the canvas
 *
 * @author tianji61
 */
public class Rectangle extends Shape {

    /**
     * Initialize a Rectangle with two given coordinates
     *
     * @param start starting coordinate
     * @param end   ending coordinate
     */
    public Rectangle(Point start, Point end, PaintProperties pp) {
        super(start, end, "Rectangle", false,
                pp.getFillColor(), pp.getBorderColor(), pp.getBorderWidth());
    }

    @Override
    void paint(GraphicsContext g2d) {
        g2d.setFill(getBorderColor());
        double[] info = getPaintInfo();
        g2d.fillRect(info[0], info[1], info[2], info[3]);
        if (!isFilled()) {
            removeFilled(g2d);
        }
    }

    @Override
    void removeFilled(GraphicsContext g2d) {
        g2d.setFill(getColor());
        double[] info = getPaintInfo();
        double width = getBorderWidth();
        g2d.fillRect(info[0] + width, info[1] + width,
                info[2] - width * 2, info[3] - width * 2);
    }

    @Override
    double[] getPaintInfo() {
        double startX = Math.min(getStart().x, getEnd().x);
        double startY = Math.min(getStart().y, getEnd().y);
        double width = Math.abs(getEnd().x - getStart().x);
        double height = Math.abs(getEnd().y - getStart().y);
        return new double[]{startX, startY, width, height};
    }
}
