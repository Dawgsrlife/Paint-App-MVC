package ca.utoronto.utm.assignment2.paint.controlPanels;

import ca.utoronto.utm.assignment2.paint.PaintProperties;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.util.ArrayList;

/**
 * This is a class defining PropertiesPanel
 *
 * @author tianji61 / mengale1
 */
public class PropertiesPanel extends GridPane implements EventHandler<MouseEvent> {
    private final ArrayList<Slider> sliders = new ArrayList<>();
    private final ArrayList<Text> texts = new ArrayList<>();
    private Slider eraserSizeSlider;
    private Text eraserSizeText;
    private boolean isEraserMode = false;  // Track whether eraser mode is active

    public PropertiesPanel() {
        // adding gap between elements
        this.setVgap(5.0);
        this.setPadding(new Insets(10.0));
        // templates
        int[] columns = new int[]{2, 3, 4, 6, 7, 8, 10};
        String[] textTemplate = new String[]{"R : ", "G : ", "B : ",
                "R : ", "G : ", "B : ",
                "px : "};

        this.add(new Text("Fill Color"), 0, 1);
        this.add(new Text("Border Color"), 0, 5);
        this.add(new Text("Border Width"), 0, 9);
        for (int i = 0; i < columns.length; i++) {
            Slider slider = new Slider();
            slider.setMax(255);
            slider.setMaxWidth(100);
            slider.setOnMouseDragged(this);
            Text text = new Text();
            text.setText(textTemplate[i] + (int)slider.getValue());
            this.add(slider, 0, columns[i]);
            this.add(text, 1, columns[i]);
            this.sliders.add(slider);
            this.texts.add(text);
        }
        this.sliders.get(6).setMax(100);
    }

    @Override
    public void handle(MouseEvent mouseEvent) {
        if (isEraserMode && mouseEvent.getSource() == eraserSizeSlider) {
            eraserSizeText.setText("Eraser Size: " + (int) eraserSizeSlider.getValue());
        } else {
            int index = 0;
            for (Slider slider : sliders) {
                if (mouseEvent.getSource() == slider) {
                    texts.get(index).setText(texts.get(index).getText().substring(0, 4) + (int) slider.getValue());
                }
                index++;
            }
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
        return new PaintProperties(fillColor, borderColor, (sliders.get(6).getValue()));
    }
}
