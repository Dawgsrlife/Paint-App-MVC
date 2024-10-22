package ca.utoronto.utm.assignment2.paint;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

/**
 * This is a class defining PropertiesPanel
 *
 * @author tianji61 / Who ever is going to pick this branch
 */
public class PropertiesPanel extends GridPane implements EventHandler<ActionEvent> {

    private View view;

    public PropertiesPanel(View view) {
        this.view = view;
        // adding gap between elements
        this.setVgap(5.0);
        this.setPadding(new Insets(10.0));
        Text text = new Text();
        text.setText("Color");
        this.add(text, 0, 0);
    }

    @Override
    public void handle(ActionEvent actionEvent) {

    }
}
