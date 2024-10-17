package ca.utoronto.utm.assignment2.paint;

public class Square extends Rectangle {
    private Point start;
    private Point end;

    /**
     * Initialize a Rectangle with two given coordinates
     *
     * @param start starting coordinate
     * @param end   ending coordinate
     */
    public Square(Point start, Point end) {
        super(start, end);
        this.start = start;
        this.end = end;
    }

    @Override
    public Point getEnd() {
        Point start = getStart();
        Point end = this.end;

        // Make height equal to width for square(take minimum value)
        double width = Math.abs(end.x - start.x);
        double height = Math.abs(end.y - start.y);
        double side = Math.min(width, height);
        // Update ending coordinate
        if (end.x > start.x) {
            end.x = start.x + side;
        }else{end.x = start.x - side;}
        if (end.y > start.y) {
            end.y = start.y + side;
        }else{end.y = start.y - side;}

        super.setEnd(end);
        return end;
    }

    @Override
    public void setEnd(Point end) {
        this.end = end;
    }

    @Override
    public void setStart(Point start) {
        super.setStart(start);
    }

    @Override
    public Point getStart() {
        return super.getStart();
    }

    @Override
    public double[] getPrintDetails() {
        double startX = Math.min(start.x, end.x);
        double startY = Math.min(start.y, end.y);
        double side = Math.abs(end.x - start.x);
        return new double[]{startX, startY, side};
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Square)) return false;
        return super.equals(o);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }


}
