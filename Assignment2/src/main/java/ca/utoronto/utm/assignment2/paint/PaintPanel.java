package ca.utoronto.utm.assignment2.paint;

import javafx.scene.canvas.Canvas;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

import java.util.Observable;
import java.util.Observer;

public class PaintPanel extends Canvas implements EventHandler<MouseEvent>, Observer {
    private String mode = "Circle";
    private PaintModel model;
    private PropertiesPanel propertiesPanel;
    private CommandManager commandManager;

    private Shape shape;
    private String cursorCoordinate;

    // public fields
    public static Color backgroundColor = Color.WHITE;

    public PaintPanel(PaintModel model, PropertiesPanel propertiesPanel, CommandManager commandManager) {
        super(500, 500);
        this.model = model;
        this.model.addObserver(this);
        this.propertiesPanel = propertiesPanel;
        this.commandManager = commandManager;

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
        // https://docs.oracle.com/javafx/2/events/DraggablePanelsExample.java.html

        EventType<MouseEvent> mouseEventType = (EventType<MouseEvent>) mouseEvent.getEventType();

        // "Circle", "Rectangle", "Square", "Triangle", "Oval", "Squiggle", "Polyline"
        switch (this.mode) {
            case "Circle":
                if (mouseEventType.equals(MouseEvent.MOUSE_PRESSED)) {
                    System.out.println("Started Circle");
                    Point centre = new Point(mouseEvent.getX(), mouseEvent.getY());
                    this.shape = new Circle(centre, 0, propertiesPanel.getPaintProperties());
                } else if (mouseEventType.equals(MouseEvent.MOUSE_DRAGGED)) {
                    // Problematic notion of radius and centre!!
                    double radius =
                            Math.sqrt(Math.pow(this.shape.getStart().x - mouseEvent.getX(), 2) +
                            Math.pow(this.shape.getStart().y - mouseEvent.getY(), 2));
                    Circle c = (Circle)this.shape;
                    c.setRadius(radius);
                    this.model.addShape(this.shape);
                } else if (mouseEventType.equals(MouseEvent.MOUSE_RELEASED)) {
                    Command drawCommand = new DrawCommand(this.model, this.shape);
                    commandManager.executeCommand(drawCommand);
                    cleanCache();
                }
                break;
            case "Rectangle":
                if (mouseEventType.equals(MouseEvent.MOUSE_PRESSED)) {
                    // record Rectangle on MOUSE_PRESSED
                    System.out.println("Started Rectangle");
                    Point start = new Point(mouseEvent.getX(), mouseEvent.getY());
                    this.shape = new Rectangle(start, null, propertiesPanel.getPaintProperties());
                } else if (mouseEventType.equals(MouseEvent.MOUSE_DRAGGED)) {
                    // update Rectangle ending coordinate on MOUSE_DRAGGED
                    Point end = new Point(mouseEvent.getX(), mouseEvent.getY());
                    this.shape.setEnd(end);
                    this.model.addShape(this.shape);
                } else if (mouseEventType.equals(MouseEvent.MOUSE_RELEASED)) {
                    Command drawCommand = new DrawCommand(this.model, this.shape);
                    commandManager.executeCommand(drawCommand);
                    cleanCache();
                }
                break;
            case "Square":
                if (mouseEventType.equals(MouseEvent.MOUSE_PRESSED)) {
                    // record new Square on MOUSE_PRESSED
                    System.out.println("Started Square");
                    Point start = new Point(mouseEvent.getX(), mouseEvent.getY());
                    this.shape = new Square(start, null, propertiesPanel.getPaintProperties());
                } else if (mouseEventType.equals(MouseEvent.MOUSE_DRAGGED)) {
                    // record Square ending coordinate on MOUSE_DRAGGED
                    Point end = new Point(mouseEvent.getX(), mouseEvent.getY());
                    this.shape.setEnd(end);
                    this.model.addShape(this.shape);
                } else if (mouseEventType.equals(MouseEvent.MOUSE_RELEASED)) {
                    cleanCache();
                }
                break;
            case "Oval":
                if (mouseEventType.equals(MouseEvent.MOUSE_PRESSED)) {
                    System.out.println("Started Oval");
                    Point start = new Point(mouseEvent.getX(), mouseEvent.getY());
                    this.shape = new Oval(start, null, propertiesPanel.getPaintProperties());
                } else if (mouseEventType.equals(MouseEvent.MOUSE_DRAGGED)) {
                    Oval o = (Oval)this.shape;
                    o.setEnd(new Point(mouseEvent.getX(), mouseEvent.getY()));
                    this.model.addShape(this.shape);
                } else if (mouseEventType.equals(MouseEvent.MOUSE_RELEASED)) {
                    cleanCache();
                }
                break;
            case "Triangle":
                if (mouseEventType.equals(MouseEvent.MOUSE_PRESSED)) {
                    // Start the triangle when the mouse is first pressed
                    System.out.println("Started Triangle");

                    Point start = new Point(mouseEvent.getX(), mouseEvent.getY());
                    this.shape = new Triangle(start, null, propertiesPanel.getPaintProperties());
                } else if (mouseEventType.equals(MouseEvent.MOUSE_DRAGGED)) {
                    // Update the end point as the user drags the mouse:
                    Point end = new Point(mouseEvent.getX(), mouseEvent.getY());
                    this.shape.setEnd(end);
                    this.model.addShape(this.shape);
                } else if (mouseEventType.equals(MouseEvent.MOUSE_RELEASED)) {
                    // Finish the shape on release:
                    cleanCache();
                }
                break;
            case "Squiggle":
                if (mouseEventType.equals(MouseEvent.MOUSE_PRESSED)) {
                    System.out.println("Started Squiggle");
                    this.shape = new Squiggle(new Point(mouseEvent.getX(), mouseEvent.getY()),
                            propertiesPanel.getPaintProperties());
                    this.model.addShape(this.shape);
                } else if (mouseEventType.equals(MouseEvent.MOUSE_DRAGGED)) {
                    Squiggle sq = (Squiggle)this.shape;
                    sq.addPoint(new Point(mouseEvent.getX(), mouseEvent.getY()));
                } else if (mouseEventType.equals(MouseEvent.MOUSE_RELEASED)) {
                    cleanCache();
                }
                break;
            case "Polyline":
                if (mouseEventType.equals(MouseEvent.MOUSE_PRESSED) && mouseEvent.isPrimaryButtonDown()) {
                    if (this.shape == null) {
                        System.out.println("Started Polyline");
                        this.shape = new Polyline(new Point(mouseEvent.getX(), mouseEvent.getY()),
                                propertiesPanel.getPaintProperties());
                        this.model.addShape(this.shape);
                    } else {
                        Polyline p = (Polyline) this.shape;
                        p.addPoint(new Point(mouseEvent.getX(), mouseEvent.getY()));
                        // TODO: Implement later... Clear the trail:
                    }
                } else if (mouseEventType.equals(MouseEvent.MOUSE_PRESSED) && mouseEvent.isSecondaryButtonDown()) {
                    cleanCache();
                } else if (mouseEventType.equals(MouseEvent.MOUSE_MOVED)) {
                    // Display the shape trail:

                    // TODO: Clear the previous trail (if any)

                    // Display the trail
//                    this.currentMousePosition = new Point(mouseEvent.getX(), mouseEvent.getY());
//                    this.model.addPoint(currentMousePosition);
                }
                break;
            default:
                break;
        }
        this.cursorCoordinate = "X = " + mouseEvent.getX() + ", Y = " + mouseEvent.getY();
        update();
    }

    /**
     * notifies in console last shape creation event has ended
     */
    private void cleanCache() {
        // clean cache on MOUSE_RELEASED
        if (this.shape != null) {
            System.out.println("    ^ Added");
            this.shape = null;
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        // get painter
        GraphicsContext g2d = this.getGraphicsContext2D();
        // draw background
        g2d.setFill(Color.WHITE);
        g2d.fillRect(0,0,this.getWidth(),this.getHeight());
        // draw shapes
        for (Shape s : this.model.getShapes()) {
            s.paint(g2d);
        }
        // draw cursorCoordinate
        g2d.setFill(Color.BLACK);
        g2d.fillText(cursorCoordinate, 3, 13);
    }

    /**
     * Refresh canvas, repaint everything existed
     */
    public void update() {
        update(model, new Object());
    }
}
