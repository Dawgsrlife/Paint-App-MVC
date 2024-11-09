package ca.utoronto.utm.assignment2.paint.controlPanels;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.layout.GridPane;

public class ShapeChooserPanel extends GridPane implements EventHandler<ActionEvent> {

    public ShapeChooserPanel() {

        // adding gap between elements
        this.setVgap(5.0);
        this.setPadding(new Insets(10.0));

        int row = 0;
        // Ethan: using buttonLabels as mode breaks the code
        String[] labels= {"●", "▬", "■", "▲", "⬬", "~", "〽", "⁺₊✧", "😎","⬟","T"};
        String[] commands = {"Circle", "Rectangle", "Square", "Triangle", "Oval", "Squiggle", "Polyline", "SmartShape", "PrecisionEraser", "Polygon", "Text"};
        for (int i = 0; i < labels.length; i++) {
            ShapeChooserPanelButton button = new ShapeChooserPanelButton(labels[i] + " " + commands[i], commands[i], false);
            if (labels[i].equals("select")) button.setSelected(true);
            this.add(button, 0, row);
            row++;
            button.setOnAction(this);
        }
    }

    @Override
    public void handle(ActionEvent event) {
        ShapeChooserPanelButton source = (ShapeChooserPanelButton) event.getSource();
        for (Object o: this.getChildren()) {
            ShapeChooserPanelButton button = (ShapeChooserPanelButton) o;
            button.setSelected(button.equals(source));
        }
    }

    public String getMode() {
        String mode = "Select";
        for (Object o : this.getChildren()) {
            ShapeChooserPanelButton button = (ShapeChooserPanelButton) o;
            if (button.isSelected()) mode = button.getCommand();
        }
        return mode;
    }
}


