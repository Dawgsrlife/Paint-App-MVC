package ca.utoronto.utm.assignment2.paint.controlPanels;

import ca.utoronto.utm.assignment2.paint.PaintModel;
import ca.utoronto.utm.assignment2.paint.Shape;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

import java.util.ArrayList;

/**
 * This is a class defining EditingPanel
 *
 * @author tianji61 / Who ever is going to pick this branch
 */
public class EditingPanel extends GridPane implements EventHandler<MouseEvent> {
    private final TextField[] mouseCoords = new TextField[2];

    private final TextField[] shapeCoords = new TextField[4];
    private final CheckBox fill = new CheckBox("");
    private final ArrayList<Slider> sliders = new ArrayList<>();
    private final ArrayList<Text> texts = new ArrayList<>();

    public EditingPanel() {
        this.setVgap(5.0);
        this.setHgap(5.0);
        this.setPadding(new Insets(10.0));
        this.add(new Text("Cursor x: "), 0, 0);
        mouseCoords[0] = new TextField("0");
        mouseCoords[0].setEditable(false);
        mouseCoords[0].setMaxWidth(50);
        mouseCoords[0].setAlignment(Pos.CENTER);
        this.add(mouseCoords[0], 1, 0);
        this.add(new Text("Cursor y: "), 2, 0);
        mouseCoords[1] = new TextField("0");
        mouseCoords[1].setEditable(false);
        mouseCoords[1].setMaxWidth(50);
        mouseCoords[1].setAlignment(Pos.CENTER);
        this.add(mouseCoords[1], 3, 0);


        this.add(new Text("Starting x: "), 0, 2);
        shapeCoords[0] = new TextField("0");
        shapeCoords[0].setMaxWidth(50);
        shapeCoords[0].setAlignment(Pos.CENTER);
        this.add(shapeCoords[0], 1, 2);
        this.add(new Text("Starting y: "), 2, 2);
        shapeCoords[1] = new TextField("1");
        shapeCoords[1].setMaxWidth(50);
        shapeCoords[1].setAlignment(Pos.CENTER);
        this.add(shapeCoords[1], 3, 2);

        this.add(new Text("Width: "), 0, 3);
        shapeCoords[2] = new TextField("2");
        shapeCoords[2].setMaxWidth(50);
        shapeCoords[2].setAlignment(Pos.CENTER);
        this.add(shapeCoords[2], 1, 3);
        this.add(new Text("Height: "), 2, 3);
        shapeCoords[3] = new TextField("3");
        shapeCoords[3].setMaxWidth(50);
        shapeCoords[3].setAlignment(Pos.CENTER);
        this.add(shapeCoords[3], 3, 3);

        int[] columns = new int[]{6, 7, 8, 10, 11, 12, 14};
        String[] textTemplate = new String[]{"R : ", "G : ", "B : ",
                "R : ", "G : ", "B : ",
                "px : "};

        this.add(new Text("Fill"), 0, 4);
        this.add(fill, 1, 4);
        this.add(new Text("Fill Color"), 0, 5);
        this.add(new Text("Border Color"), 0, 9);
        this.add(new Text("Thickness"), 0, 13);
        for (int i = 0; i < columns.length; i++) {
            Slider slider = new Slider();
            slider.setMaxWidth(100);
            slider.setOnMouseDragged(this);
            slider.setOnMousePressed(this);
            Text text = new Text();
            text.setText(textTemplate[i] + (int)slider.getValue());
            this.add(slider, 0, columns[i]);
            setColumnSpan(slider, 2);
            this.add(text, 2, columns[i]);
            this.sliders.add(slider);
            this.texts.add(text);
        }
        this.sliders.get(6).setMax(20);
    }

    @Override
    public void handle(MouseEvent mouseEvent) {
        int index = 0;
        for (Slider slider : sliders) {
            if (mouseEvent.getSource() == slider) {
                texts.get(index).setText(texts.get(index).getText().substring(0, 4) + (int)slider.getValue());
            }
            index ++;
        }
    }

    public void setMouseCoords(MouseEvent mouseEvent) {
        this.mouseCoords[0].setText(String.valueOf((int)mouseEvent.getX()));
        this.mouseCoords[1].setText(String.valueOf((int)mouseEvent.getY()));
    }

    public void setSelectedShapeDetails(Shape shape, PaintModel model) {
        shapeCoords[0].setText(shape.getStart().getX()+"");
        shapeCoords[1].setText(shape.getStart().getY()+"");
        shapeCoords[2].setText(Math.abs(shape.getStart().getX() - shape.getEnd().getX())+"");
        shapeCoords[3].setText(Math.abs(shape.getStart().getY() - shape.getEnd().getY())+"");
    }
}
