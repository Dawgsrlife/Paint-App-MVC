package ca.utoronto.utm.assignment2.paint.shapes;

import ca.utoronto.utm.assignment2.paint.PaintProperties;

public class Square extends Rectangle {

    public Square(Point start, Point end, PaintProperties pp) {
        super(start, end, pp);
        setType("Square");
    }

    @Override
    protected double[] getPaintInfo() {
        // using Math.min to get a better fx, without instant jumps when cursor switching quadrant
        double width = Math.sqrt(Math.pow(getStart().getX() - getEnd().getY(), 2) +
                Math.pow(getStart().getY() - getEnd().getY(), 2));
        // determine if a displacement on x / y is needed
        int deltaX = getEnd().getX() > getStart().getX() ? 0 : -1;
        int deltaY = getEnd().getY() > getStart().getY() ? 0 : -1;
        return new double[]{getStart().getX() + deltaX * width, getStart().getY() + deltaY * width, width, width};
    }
}
