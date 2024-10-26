package ca.utoronto.utm.assignment2.paint;

public class Square extends Rectangle {

    public Square(Point start, Point end, PaintProperties pp) {
        super(start, end, pp);
        setType("Square");
    }

    @Override
    protected double[] getPaintInfo() {
        // using Math.min to get a better fx, without instant jumps when cursor switching quadrant
        double width = Math.sqrt(Math.pow(getStart().x - getEnd().x, 2) +
                Math.pow(getStart().y - getEnd().y, 2));
        // determine if a displacement on x / y is needed
        int deltaX = getEnd().x > getStart().x ? 0 : -1;
        int deltaY = getEnd().y > getStart().y ? 0 : -1;
        return new double[]{getStart().x + deltaX * width, getStart().y + deltaY * width, width, width};
    }
}
