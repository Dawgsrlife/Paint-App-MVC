package ca.utoronto.utm.assignment2.paint;

import ca.utoronto.utm.assignment2.paint.shapes.Shape;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

import java.util.Observable;
import java.util.Observer;

/**
 * The PaintView class represents the view of the paint application,
 * extending the Canvas class to display the drawing area.
 * It listens to updates from the PaintModel and redraws the canvas whenever the model changes.
 * Additionally, it handles mouse events (e.g., press, release, move, drag, click) through the PaintController.
 * <p>
 * This class is an Observer in the Model-View-Controller (MVC) pattern, allowing it to update the display when
 * the PaintModel is modified. It also serves as the visual component for drawing shapes on the canvas.
 * </p>
 * @author tianji61 / chen2046
 */
public class PaintView extends Canvas implements Observer {
    private final PaintModel model;
    public static final Color BACKGROUND_COLOR = Color.WHITE;  // Package-private
    private final Pane canvasPane;

    /**
     * Constructor for initializing the PaintView.
     * This adds the view (Canvas) to the provided pane and sets up the event handlers for mouse interactions.
     *
     * @param model The PaintModel to observe and retrieve shapes from.
     * @param controller The PaintController to handle mouse events.
     * @param canvasPane The pane where this canvas will be added.
     */
    public PaintView(PaintModel model, PaintController controller, Pane canvasPane) {
        super(700, 700);
        this.model = model;
        this.model.addObserver(this);
        this.canvasPane = canvasPane;

        // Add this Canvas to the Pane
        this.canvasPane.getChildren().add(this);

        this.addEventHandler(MouseEvent.MOUSE_PRESSED, controller);
        this.addEventHandler(MouseEvent.MOUSE_RELEASED, controller);
        this.addEventHandler(MouseEvent.MOUSE_MOVED, controller);
        this.addEventHandler(MouseEvent.MOUSE_CLICKED, controller);
        this.addEventHandler(MouseEvent.MOUSE_DRAGGED, controller);
    }

    /**
     * Updates the view by redrawing the canvas whenever the model changes.
     * This method is called automatically by the Observable pattern when the model is updated.
     * It clears the canvas and redraws all shapes from the model.
     *
     * @param o The observable object (PaintModel).
     * @param arg An additional argument (not used in this implementation).
     */
    @Override
    public void update(Observable o, Object arg) {
        // get painter
        GraphicsContext g2d = this.getGraphicsContext2D();
        // draw background
        g2d.setFill(BACKGROUND_COLOR);
        g2d.fillRect(0,0,this.getWidth(),this.getHeight());
        // draw shapes
        for (Shape s : model.getShapes()) {
            if (s != null) s.paint(g2d);
        }
    }
}
