package ca.utoronto.utm.assignment2.paint;

import javafx.scene.canvas.GraphicsContext;

public class Oval extends Shape {

    public Oval(Point start, Point end, PaintProperties pp) {
        super(start, end, "Oval", false,
                pp.getFillColor(), pp.getBorderColor(), pp.getBorderWidth());
    }

    @Override
    void paint(GraphicsContext g2d) {
        g2d.setFill(getBorderColor());
        double[] info = getPaintInfo();
        g2d.fillOval(info[0], info[1], info[2], info[3]);
        if (!isFilled()) {
            removeFilled(g2d);
        }
    }

    @Override
    protected void removeFilled(GraphicsContext g2d) {
        g2d.setFill(getColor());
        double[] info = getPaintInfo();
        double width = getBorderWidth();
        g2d.fillOval(info[0] + width, info[1] + width,
                info[2] - width * 2, info[3] - width * 2);
    }

    @Override
    protected double[] getPaintInfo() {
        double startX = Math.min(getStart().x, getEnd().x);
        double startY = Math.min(getStart().y, getEnd().y);
        double width = Math.abs(getEnd().x - getStart().x);
        double height = Math.abs(getEnd().y - getStart().y);
        return new double[]{startX, startY, width, height};
    }
}
