package ca.utoronto.utm.assignment2.paint;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

import java.util.Observable;
import java.util.Observer;

public class PaintView extends Canvas implements Observer {
    private final PaintModel model;
    public static final Color BACKGROUND_COLOR = Color.WHITE;  // Package-private

    public PaintView(PaintModel model, PaintController controller) {
        super(500, 500);
        this.model = model;
        this.model.addObserver(this);

        this.addEventHandler(MouseEvent.MOUSE_PRESSED, controller);
        this.addEventHandler(MouseEvent.MOUSE_RELEASED, controller);
        this.addEventHandler(MouseEvent.MOUSE_MOVED, controller);
        this.addEventHandler(MouseEvent.MOUSE_CLICKED, controller);
        this.addEventHandler(MouseEvent.MOUSE_DRAGGED, controller);
    }

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
