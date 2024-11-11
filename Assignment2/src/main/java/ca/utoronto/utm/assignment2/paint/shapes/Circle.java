package ca.utoronto.utm.assignment2.paint.shapes;

import ca.utoronto.utm.assignment2.paint.PaintProperties;

/**
 * A class representing a shape on the canvas
 *
 * @author tianji61 / Starter Code
 */
public class Circle extends Oval {

    public Circle(Point centre, Point end, PaintProperties pp) {
        super(centre, end, pp);
        this.setType("Circle");
    }

    @Override
    public double[] getPaintInfo() {
        // Problematic notion of radius and centre!!
        double radius = Math.sqrt(Math.pow(getStart().getX() - getEnd().getX(), 2) +
                        Math.pow(getStart().getY() - getEnd().getY(), 2));
        double x = getStart().getX() - radius;
        double y = getStart().getY() - radius;
        return new double[]{x, y, 2 * radius, 2 * radius};
    }
}
