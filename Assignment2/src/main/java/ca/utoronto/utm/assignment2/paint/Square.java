package ca.utoronto.utm.assignment2.paint;

public class Square extends Rectangle {

    public Square(Point start, Point end) {
        super(start, end);
    }

    public double[] getPrintDetails() {
        // using Math.min to get a better fx, without instant jumps when cursor switching quadrant
        double width = Math.min(Math.abs(getEnd().x - getStart().x), Math.abs(getEnd().y - getStart().y));
        Point start = this.getPrintStartPoint(getStart(), getEnd(), width);
        return new double[]{start.x, start.y, width};
    }

    private Point getPrintStartPoint(Point start, Point end, double width) {
        if (end.x > start.x & end.y > start.y) {
            return start;
        } else if (end.x > start.x & end.y < start.y) {
            return new Point(start.x, start.y - width);
        } else if (end.x < start.x & end.y > start.y) {
            return new Point(start.x - width, start.y);
        }
        return new Point(start.x - width, start.y - width);
    }
}
