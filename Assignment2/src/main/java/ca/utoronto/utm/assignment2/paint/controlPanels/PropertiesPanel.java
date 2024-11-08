package ca.utoronto.utm.assignment2.paint.controlPanels;

import ca.utoronto.utm.assignment2.paint.PaintProperties;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.util.ArrayList;

/**
 * This is a class defining PropertiesPanel
 *
 * @author tianji61 / mengale1
 */
public class PropertiesPanel extends GridPane implements EventHandler<MouseEvent> {

    private final CheckBox fill = new CheckBox("");
    private final ArrayList<Slider> sliders = new ArrayList<>();
    private final ArrayList<Text> texts = new ArrayList<>();
    private final Rectangle fillColorPreview = new Rectangle(20, 20, Color.BLACK);
    private final Rectangle borderColorPreview = new Rectangle(20, 20, Color.BLACK);

    public PropertiesPanel() {
        // Background, padding styling, and gaps
        this.setPrefWidth(185);
        this.setStyle("-fx-background-color: #f0f0f0; -fx-border-color: #cccccc; -fx-border-width: 2px;");
        this.setVgap(5.0);
        this.setHgap(15);
        this.setPadding(new Insets(10.0));

        // Templates for labels and slider positions
        int[] columns = new int[]{3, 4, 5, 7, 8, 9, 11, 12};
        String[] textTemplate = new String[]{"R : ", "G : ", "B : ",
                "R : ", "G : ", "B : ",
                "px : ", "# : "};

        // Layout setup
        Text fillLabel = new Text("Fill");
        fillLabel.setFont(Font.font("Arial", 14));
        this.add(fillLabel, 0, 1);
        this.add(fill, 1, 1);

        this.add(new Text("Fill Color"), 0, 2);
        this.add(fillColorPreview, 1, 2);

        this.add(new Text("Border Color"), 0, 6);
        this.add(borderColorPreview, 1, 6);

        this.add(new Text("Thickness"), 0, 10);

        // Initialize sliders and their labels
        for (int i = 0; i < columns.length; i++) {
            Slider slider = new Slider();
            slider.setMaxWidth(100);
            slider.setMax(255);
            slider.setOnMouseDragged(this);
            slider.setOnMousePressed(this);

            Text text = new Text(textTemplate[i] + (int)slider.getValue());
            text.setFont(Font.font("Arial", 12));
            text.setFill(Color.GRAY);

            this.add(slider, 0, columns[i]);
            this.add(text, 1, columns[i]);

            sliders.add(slider);
            texts.add(text);
        }

        // Configure thickness slider
        sliders.get(6).setMax(20);
        sliders.get(6).setValue(1);
        texts.get(6).setText("px : 1");

        // Slider styling
        for (Slider slider : sliders) {
            slider.setStyle("-fx-accent: #0078D7; -fx-control-inner-background: #cccccc;");
        }
    }

    @Override
    public void handle(MouseEvent mouseEvent) {
        int index = 0;
        for (Slider slider : sliders) {
            if (mouseEvent.getSource() == slider) {
                // Update the corresponding text
                texts.get(index).setText(texts.get(index).getText().split(":")[0] + ": " + (int)slider.getValue());
            }

            // Update preview colours for fill and border
            if (index < 3) {
                fillColorPreview.setFill(Color.rgb(
                        (int) sliders.get(0).getValue(),
                        (int) sliders.get(1).getValue(),
                        (int) sliders.get(2).getValue()
                ));
            } else if (index < 6) {
                borderColorPreview.setFill(Color.rgb(
                        (int) sliders.get(3).getValue(),
                        (int) sliders.get(4).getValue(),
                        (int) sliders.get(5).getValue()
                ));
            }
            index ++;
        }
    }

    public PaintProperties getPaintProperties() {

        Color fillColor = Color.rgb(
                (int)sliders.get(0).getValue(),
                (int)sliders.get(1).getValue(),
                (int)sliders.get(2).getValue());
        Color borderColor = Color.rgb(
                (int)sliders.get(3).getValue(),
                (int)sliders.get(4).getValue(),
                (int)sliders.get(5).getValue());
        double borderWidth = sliders.get(6).getValue();
        int vertices = (int)sliders.get(7).getValue();
        return new PaintProperties(fill.isSelected(), fillColor, borderColor, borderWidth, vertices);
    }
}
