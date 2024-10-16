package ca.utoronto.utm.assignment2.paint;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

public class ShapeChooserPanel extends GridPane implements EventHandler<ActionEvent> {

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

                String[] buttonLabels = { "●", "▬", "■", "~", "〽" };

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

        @Override
        public void handle(ActionEvent event) {
                String command = ((Button) event.getSource()).getText();
                view.setMode(command);
                System.out.println(command);

                // highlight button when selected
                for (Object o : this.getChildren()) {
                        Button b = (Button) o;
                        if (b.getText().equals(command)) {
                                b.setStyle(SELECTED);
                        } else {
                                b.setStyle(UNSELECTED);
                        }
                }
        }
}


