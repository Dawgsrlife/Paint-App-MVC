package ca.utoronto.utm.assignment2.paint;

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
        double radius = Math.sqrt(Math.pow(getStart().x - getEnd().x, 2) +
                        Math.pow(getStart().y - getEnd().y, 2));
        double x = getStart().x - radius;
        double y = getStart().y - radius;
        return new double[]{x, y, 2 * radius, 2 * radius};
    }
}
