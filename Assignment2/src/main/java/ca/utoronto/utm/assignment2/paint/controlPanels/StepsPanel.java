package ca.utoronto.utm.assignment2.paint.controlPanels;

import ca.utoronto.utm.assignment2.paint.PaintModel;
import ca.utoronto.utm.assignment2.paint.Shape;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;


/**
 * This is a class defining StepsPanel
 *
 * @author tianji61 / Who ever is going to pick this branch
 */
public class StepsPanel extends GridPane implements EventHandler<MouseEvent> {

    private final PaintModel model;

    // css properties for status indicating
    private final String ACTIVE = "-fx-background-color: rgba(244, 244, 244);" +
            "-fx-border-color: rgba(0, 0, 0, 0.2);" +
            "-fx-border-style: none none solid none;";

    private final String DEACTIVETED = "-fx-background-color: rgba(233, 233, 233);" +
            "-fx-border-color: rgba(0, 0, 0, 0.2);" +
            "-fx-border-style: none none solid none;";

    public StepsPanel(PaintModel paintModel) {
        this.model = paintModel;
        setMinWidth(170);
        setPrefHeight(100);
        setOnMouseEntered(this);
    }

    @Override
    public void handle(MouseEvent event) {
        if (event.getEventType() == MouseEvent.MOUSE_ENTERED) {
            int i = 0;
            getChildren().clear();
            for (Shape shape : this.model.getShapes()) {
                String buttonString = "Add " + shape.toString().split("@")[0].split("\\.")[5];
                Button button = new Button(buttonString);
                button.setMinWidth(150);
                button.setStyle(ACTIVE);
                this.add(button, 0, i);
                i ++;
            }
        }
    }
}
