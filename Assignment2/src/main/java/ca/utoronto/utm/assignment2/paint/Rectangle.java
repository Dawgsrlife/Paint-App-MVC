package ca.utoronto.utm.assignment2.paint;

import java.util.Objects;

/**
 * A class representing a rectangle on the canvas
 *
 * @author tianji61
 */
public class Rectangle {

    // two corner coordinates for Rectangle indexing and definition
    private Point start;
    private Point end;

    /**
     * Initialize a Rectangle with two given coordinates
     *
     * @param start starting coordinate
     * @param end   ending coordinate
     */
    public Rectangle(Point start, Point end) {
        this.start = start;
        this.end = end;
    }

    public Point getEnd() {
        return end;
    }

    public void setEnd(Point end) {
        this.end = end;
    }

    public Point getStart() {
        return start;
    }

    public void setStart(Point start) {
        this.start = start;
    }

    /**
     * Get g2d printing arguments
     *
     * @return a list of [upperLeft x, upperLeft y, with, height] of this Rectangle instance
     */
    public double[] getPrintDetails() {
        double startX = Math.min(start.x, end.x);
        double startY = Math.min(start.y, end.y);
        double width = Math.abs(end.x - start.x);
        double height = Math.abs(end.y - start.y);
        return new double[]{startX, startY, width, height};
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rectangle rectangle = (Rectangle) o;
        return Objects.equals(start, rectangle.start) && Objects.equals(end, rectangle.end);
    }

    @Override
    public int hashCode() {
        return Objects.hash(start, end);
    }
}
