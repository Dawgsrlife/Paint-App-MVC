package ca.utoronto.utm.assignment2.paint.controlPanels;

import ca.utoronto.utm.assignment2.paint.PaintProperties;
import ca.utoronto.utm.assignment2.paint.Point;
import ca.utoronto.utm.assignment2.paint.Shape;
import ca.utoronto.utm.assignment2.paint.commandMenuBar.CommandCut;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;

/**
 * This is a class defining PropertiesPanel
 *
 * @author tianji61 / mengale1
 */
public class PropertiesPanel extends GridPane implements EventHandler<MouseEvent> {

    private final CheckBox fill = new CheckBox("");
    private final ArrayList<Slider> sliders = new ArrayList<>();
    private final ArrayList<Label> labels = new ArrayList<>();
    private final ArrayList<TextField> coordinates = new ArrayList<>();
    private final ArrayList<Rectangle> rectangles = new ArrayList<>();
    private Shape currentShape;

    public PropertiesPanel() {
        // Background, padding styling, and gaps
        this.setPrefWidth(200);
        this.setVgap(5.0);
        this.setHgap(15);
        this.setPadding(new Insets(10.0));
        getStyleClass().add("properties-panel");

        // Cursor info display
        coordinates.add(new TextField("0 | 0"));
        coordinates.getLast().setAlignment(Pos.CENTER);
        coordinates.getLast().setEditable(false);
        this.add(coordinates.getLast(), 0, 0);
        setColumnSpan(coordinates.getLast(), 3);

        // Templates for labels and slider positions
        int[] columns = new int[]{3, 4, 5, 6, 8, 9, 10, 11, 13, 15};
        String[] textTemplate = new String[]{"R : ", "G : ", "B : ", "A : ",
                "R : ", "G : ", "B : ", "A : ",
                "px : ", "# : "};

        // Layout setup
        Label fillLabel = new Label("Fill");
        this.add(fillLabel, 0, 1);
        this.add(fill, 2, 1);

        this.add(new Label("Fill Color"), 0, 2);
        rectangles.add(new Rectangle(20, 20, Color.BLACK));
        this.add(rectangles.getLast(), 2, 2);

        this.add(new Label("Border Color"), 0, 7);
        rectangles.add(new Rectangle(20, 20, Color.BLACK));
        this.add(rectangles.getLast(), 2, 7);

        this.add(new Label("Thickness"), 0, 12);

        this.add(new Label("Vertices"), 0, 14);

        // Initialize sliders and their labels
        for (int i = 0; i < columns.length; i++) {
            Slider slider = new Slider();
            slider.setMaxWidth(130);
            slider.setMax(255);
            slider.setBlockIncrement(1);
            slider.setMajorTickUnit(1);
            slider.setMinorTickCount(0);
            slider.setSnapToTicks(true);
            slider.setOnMouseDragged(this);
            slider.setOnMousePressed(this);
            setColumnSpan(slider, 2);

            Label label = new Label(textTemplate[i] + (int)slider.getValue());

            this.add(slider, 0, columns[i]);
            this.add(label, 2, columns[i]);

            sliders.add(slider);
            labels.add(label);
        }

        // Config sliders CSS
        sliders.get(0).getStyleClass().add("slider-red");
        sliders.get(4).getStyleClass().add("slider-red");
        sliders.get(1).getStyleClass().add("slider-green");
        sliders.get(5).getStyleClass().add("slider-green");
        sliders.get(2).getStyleClass().add("slider-blue");
        sliders.get(6).getStyleClass().add("slider-blue");
        sliders.get(3).getStyleClass().add("slider-alpha");
        sliders.get(7).getStyleClass().add("slider-alpha");

        // Configure alpha slider
        sliders.get(3).setValue(255);
        sliders.get(7).setValue(255);

        // Configure thickness slider
        sliders.get(8).setMax(20);
        sliders.get(8).setValue(5);

        // Configure polygon vertices
        sliders.get(9).setMin(4);
        sliders.get(9).setMax(20);


        textTemplate = new String[]{"X : ", "Y :", "Width : ", "Height : "};
        for (int i = 16; i < 20; i++) {
            this.add(new Label(textTemplate[i - 16]), 0, i);
            coordinates.add(new TextField(""));
            coordinates.getLast().setAlignment(Pos.CENTER);
            coordinates.getLast().setDisable(true);
            this.add(coordinates.getLast(), 1, i);
            setColumnSpan(coordinates.getLast(), 2);
        }

        updateVisualizer();
    }

    @Override
    public void handle(MouseEvent mouseEvent) {
        updateVisualizer();
    }

    public void setMouseCoords(Point p) {
        this.coordinates.getFirst().setText((int)p.getX() + " | " + (int)p.getY());
    }

    private void updateVisualizer() {
        for (int i = 0; i < sliders.size(); i++) {
            labels.get(i).setText(labels.get(i).getText().split(":")[0] + ": " + (int)sliders.get(i).getValue());
        }
        // Update preview colours for fill and border
        rectangles.get(0).setFill(Color.rgb(
                (int) sliders.get(0).getValue(),
                (int) sliders.get(1).getValue(),
                (int) sliders.get(2).getValue(),
                sliders.get(3).getValue() / 255));
        rectangles.get(1).setFill(Color.rgb(
                (int) sliders.get(4).getValue(),
                (int) sliders.get(5).getValue(),
                (int) sliders.get(6).getValue(),
                sliders.get(7).getValue() / 255));
    }

    public PaintProperties getPaintProperties() {
        Color fillColor = Color.rgb(
                (int)sliders.get(0).getValue(),
                (int)sliders.get(1).getValue(),
                (int)sliders.get(2).getValue(),
                sliders.get(3).getValue() / 255);
        Color borderColor = Color.rgb(
                (int)sliders.get(4).getValue(),
                (int)sliders.get(5).getValue(),
                (int)sliders.get(6).getValue(),
                sliders.get(7).getValue() / 255);
        double borderWidth = sliders.get(8).getValue();
        int vertices = (int)sliders.get(9).getValue();
        return new PaintProperties(fill.isSelected(), fillColor, borderColor, borderWidth, vertices);
    }

    public void loadPaintProperties(Shape s) {
        PaintProperties pp = s.getProperties();
        // load fill type
        fill.setSelected(pp.isFilled());
        // load fill RGB color parameter
        String hexCode = pp.getFillColor().toString().substring(2,10);
        int red = Integer.valueOf(hexCode.substring(0, 2), 16);
        int green = Integer.valueOf(hexCode.substring(2, 4), 16);
        int blue = Integer.valueOf(hexCode.substring(4, 6), 16);
        int alpha = Integer.valueOf(hexCode.substring(6, 8), 16);
        sliders.get(0).setValue(red);
        sliders.get(1).setValue(green);
        sliders.get(2).setValue(blue);
        sliders.get(3).setValue(alpha);
        // load stroke RGB color parameter
        hexCode = pp.getStrokeColor().toString().substring(2,10);
        red = Integer.valueOf(hexCode.substring(0, 2), 16);
        green = Integer.valueOf(hexCode.substring(2, 4), 16);
        blue = Integer.valueOf(hexCode.substring(4, 6), 16);
        sliders.get(4).setValue(red);
        sliders.get(5).setValue(green);
        sliders.get(6).setValue(blue);
        sliders.get(7).setValue(alpha);
        // load stroke thickness
        sliders.get(8).setValue((int)pp.getStrokeThickness());
        // load vertices count
        sliders.get(9).setValue(pp.getVertices());
        coordinates.get(1).setText((int)s.getStart().getX() + "");
        coordinates.get(1).setDisable(false);
        coordinates.get(2).setText((int)s.getStart().getY() + "");
        coordinates.get(2).setDisable(false);
        coordinates.get(3).setText((int)s.getEnd().getX() - s.getStart().getX() + "");
        coordinates.get(3).setDisable(false);
        coordinates.get(4).setText((int)s.getEnd().getY() - s.getStart().getY() + "");
        coordinates.get(4).setDisable(false);
        updateVisualizer();

        this.currentShape = s;
    }

    public Shape getCurrentlyLoadedShape(String command) {
        return this.currentShape;
    }

    public void clearCLS() {
        currentShape = null;
    }

    public void cutSelectedShape() {
        if (currentShape != null) {
            // Remove the current shape from the model (assuming model reference is available)
            //Zmodel.removeShape(currentShape);

            // Clear the current shape after cutting
            clearCLS();
        }
    }
}
