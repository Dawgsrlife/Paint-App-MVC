package ca.utoronto.utm.assignment2.paint;

import javafx.scene.canvas.Canvas;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class PaintPanel extends Canvas implements EventHandler<MouseEvent>, Observer {
    private String mode = "Circle";
    private PaintModel model;

    public Circle circle; // This is VERY UGLY, should somehow fix this!!
    private Rectangle rectangle;
    private Square square;
    private String cursorCoordinate;

    public PaintPanel(PaintModel model) {
        super(300, 300);
        this.model = model;
        this.model.addObserver(this);

        this.addEventHandler(MouseEvent.MOUSE_PRESSED, this);
        this.addEventHandler(MouseEvent.MOUSE_RELEASED, this);
        this.addEventHandler(MouseEvent.MOUSE_MOVED, this);
        this.addEventHandler(MouseEvent.MOUSE_CLICKED, this);
        this.addEventHandler(MouseEvent.MOUSE_DRAGGED, this);
    }

    /**
     * Controller aspect of this
     */
    public void setMode(String mode) {
        this.mode = mode;
        System.out.println(this.mode);
    }

    @Override
    public void handle(MouseEvent mouseEvent) {
        // Later when we learn about inner classes...
        // https://docs.oracle.com/javafx/2/events/DraggablePanelsExample.java.htm

        EventType<MouseEvent> mouseEventType = (EventType<MouseEvent>) mouseEvent.getEventType();

        // "Circle", "Rectangle", "Square", "Squiggle", "Polyline"
        switch (this.mode) {
            case "Circle":
                if (mouseEventType.equals(MouseEvent.MOUSE_PRESSED)) {
                    System.out.println("Started Circle");
                    Point centre = new Point(mouseEvent.getX(), mouseEvent.getY());
                    this.circle = new Circle(centre, 0);
                } else if (mouseEventType.equals(MouseEvent.MOUSE_DRAGGED)) {
                    // Problematic notion of radius and centre!!
                    double radius =
                            Math.sqrt(Math.pow(this.circle.getCentre().x - mouseEvent.getX(), 2) +
                            Math.pow(this.circle.getCentre().y - mouseEvent.getY(), 2));
                    this.circle.setRadius(radius);
                    this.model.addCircle(this.circle);
                } else if (mouseEventType.equals(MouseEvent.MOUSE_RELEASED)) {
                    if (this.circle != null) {
                        System.out.println("Added Circle");
                        this.circle = null;
                    }
                }

                break;
            case "Rectangle":
                if (mouseEventType.equals(MouseEvent.MOUSE_PRESSED)) {
                    // record Rectangle on MOUSE_PRESSED
                    System.out.println("Started Rectangle");
                    Point start = new Point(mouseEvent.getX(), mouseEvent.getY());
                    this.rectangle = new Rectangle(start, null);
                } else if (mouseEventType.equals(MouseEvent.MOUSE_DRAGGED)) {
                    // update Rectangle ending coordinate on MOUSE_DRAGGED
                    Point end = new Point(mouseEvent.getX(), mouseEvent.getY());
                    this.rectangle.setEnd(end);
                    this.model.addRectangle(this.rectangle);
                } else if (mouseEventType.equals(MouseEvent.MOUSE_RELEASED)) {
                    // clean cache on MOUSE_RELEASED
                    if (this.rectangle != null) {
                        System.out.println("Added Rectangle");
                        this.rectangle = null;
                    }
                }
                break;
            case "Square":
                if (mouseEventType.equals(MouseEvent.MOUSE_PRESSED)) {
                    // record new Square on MOUSE_PRESSED
                    System.out.println("Started Square");
                    Point start = new Point(mouseEvent.getX(), mouseEvent.getY());
                    this.square = new Square(start, null);
                } else if (mouseEventType.equals(MouseEvent.MOUSE_DRAGGED)) {
                    // record Square ending coordinate on MOUSE_DRAGGED
                    Point end = new Point(mouseEvent.getX(), mouseEvent.getY());
                    this.square.setEnd(end);
                    this.model.addSquare(this.square);
                } else if (mouseEventType.equals(MouseEvent.MOUSE_RELEASED)) {
                    // clean cache on MOUSE_RELEASED
                    if (this.square != null) {
                        System.out.println("Added Square");
                        this.square = null;
                    }
                }
                break;
            case "Squiggle":
                if (mouseEventType.equals(MouseEvent.MOUSE_PRESSED)) {
                    this.model.addPoint(new Point(mouseEvent.getX(), mouseEvent.getY()));
                }
                if (mouseEventType.equals(MouseEvent.MOUSE_DRAGGED)) {
                    this.model.addPoint(new Point(mouseEvent.getX(), mouseEvent.getY()));
                    // System.out.println(this.model.getPoints());
                } else if (mouseEventType.equals(MouseEvent.MOUSE_RELEASED)) {
                    this.model.addLineBreak();
                }
                break;
            case "Polyline":
                break;
            default:
                break;
        }
        this.cursorCoordinate = "X = " + mouseEvent.getX() + ", Y = " + mouseEvent.getY();
    }

    @Override
    public void update(Observable o, Object arg) {

        GraphicsContext g2d = this.getGraphicsContext2D();
        g2d.clearRect(0, 0, this.getWidth(), this.getHeight());
        // Draw Lines
        ArrayList<Point> points = this.model.getPoints();

        ArrayList<Integer> lineBreaks = this.model.getLineBreaks();

        g2d.setFill(Color.RED);

        int j = 0;
        for (int i = 0; i < points.size() - 1; i++) {
            if (j < lineBreaks.size() && i == lineBreaks.get(j)) {
//                            System.out.println(lineBreaks.get(j) + " " +
//                                    points.get(i).x + " " + points.get(i).y + " " +
//                                    points.get(i+1).x + " " + points.get(i+1).y);
                j++;
                continue;
            }
            Point p1 = points.get(i);
            Point p2 = points.get(i + 1);
            g2d.strokeLine(p1.x, p1.y, p2.x, p2.y);
        }

        // Draw Circles
        g2d.setFill(Color.LIGHTGREEN);
        for (Circle c : this.model.getCircles()) {
            double x = c.getCentre().x - c.getRadius();
            double y = c.getCentre().y - c.getRadius();
            double radius = c.getRadius();
            g2d.fillOval(x, y, radius * 2, radius * 2);
        }

        // draw Rectangles
        g2d.setFill(Color.LIGHTBLUE);
        for (Rectangle r : this.model.getRectangles()) {
            double[] details = r.getPrintDetails();
            g2d.fillRect(details[0], details[1], details[2], details[3]);
        }

        // draw Squares
        g2d.setFill(Color.LIGHTPINK);
        for (Square s : this.model.getSquares()) {
            double[] details = s.getPrintDetails();
            g2d.fillRect(details[0], details[1], details[2], details[2]);
        }

        // draw cursorCoordinate
        g2d.setFill(Color.BLACK);
        g2d.fillText(cursorCoordinate, 3, 13);
    }
}
