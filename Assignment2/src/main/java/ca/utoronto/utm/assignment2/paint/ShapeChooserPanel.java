package ca.utoronto.utm.assignment2.paint;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

import java.util.*;

public class ShapeChooserPanel extends GridPane implements EventHandler<ActionEvent> {
    // Ethan: using buttonLabels as mode breaks the code
    final private String[] buttonLabels = {"●", "▬", "■", "~", "〽"};
    final private String[] buttonNames = {"Circle", "Rectangle", "Square", "Squiggle", "Polyline"};

    private View view;

    // css properties for button highlighting
    private final String SELECTED = "-fx-background-color: rgba(255, 255, 0, 0.2);" +
            "-fx-border-color: rgba(0, 0, 0, 0.2);" +
            "-fx-border-radius: 3px;";

    private final String UNSELECTED = "-fx-background-color: rgba(225, 225, 225, 1);" +
            "-fx-border-color: rgba(0, 0, 0, 0.2);" +
            "-fx-border-radius: 3px;";

    public ShapeChooserPanel(View view) {

        this.view = view;


        // adding gap between elements
        this.setVgap(5.0);

        int row = 0;
        for (String label : buttonLabels) {
            Button button = new Button(label);
            button.setMinWidth(100);
            button.setStyle(UNSELECTED);
            this.add(button, 0, row);
            row++;
            button.setOnAction(this);
        }
    }

    /**
     * Returns the index of the object in the array, or -1 if it is not in the array
     *
     * @param arr  The array to be searched
     * @param item The item to be found
     * @return The index of the item in the array, or -1 if it is not in the array
     */
    private int indexOf(Object[] arr, Object item) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i].equals(item)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public void handle(ActionEvent event) {
        // TODO: temporary fix to buttonLabels issue, this should be reimplemented
        // TODO: at a later date
        Button source = (Button) event.getSource();
        int buttonNameIndex = indexOf(buttonLabels, source.getText());
        String command = buttonNames[buttonNameIndex];
        view.setMode(command);
        System.out.println(command);

        // highlight button when selected
        for (Object o : this.getChildren()) {
            Button b = (Button) o;
            if (b.getText().equals(source.getText())) {
                b.setStyle(SELECTED);
            } else {
                b.setStyle(UNSELECTED);
            }
        }
    }
}


