package ca.utoronto.utm.assignment2.paint.controlPanels;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

/**
 * This is a class defining EditingPanel
 *
 * @author tianji61 / Who ever is going to pick this branch
 */
public class EditingPanel extends GridPane{
    TextField[] mouseCoords = new TextField[2];

    public EditingPanel() {
        this.setVgap(5.0);
        this.setPadding(new Insets(10.0));
        this.add(new Text("x-coordinate: "), 0, 0);
        mouseCoords[0] = new TextField("0");
        mouseCoords[0].setEditable(false);
        mouseCoords[0].setMaxWidth(50);
        mouseCoords[0].setAlignment(Pos.CENTER);
        this.add(mouseCoords[0], 1, 0);
        this.add(new Text("y-coordinate: "), 0, 1);
        mouseCoords[1] = new TextField("0");
        mouseCoords[1].setEditable(false);
        this.add(mouseCoords[1], 1, 1);
        mouseCoords[1].setMaxWidth(50);
        mouseCoords[1].setAlignment(Pos.CENTER);
    }

    public void setMouseCoords(MouseEvent mouseEvent) {
        this.mouseCoords[0].setText(String.valueOf((int)mouseEvent.getX()));
        this.mouseCoords[1].setText(String.valueOf((int)mouseEvent.getY()));
    }
}
