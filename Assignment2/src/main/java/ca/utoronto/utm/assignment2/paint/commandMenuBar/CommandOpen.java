package ca.utoronto.utm.assignment2.paint.commandMenuBar;

import ca.utoronto.utm.assignment2.paint.*;
import ca.utoronto.utm.assignment2.paint.shapes.Point;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.MenuItem;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;

public class CommandOpen extends MenuItem implements Command {
    public CommandOpen() {
        super("Open");
    }
    @Override
    public void execute(PaintModel model, Scene scene, PaintController controller) {
        FileChooser fc = new FileChooser();
        fc.setTitle("Open canvas");
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("A2S", "*.a2s"));
        File file = fc.showOpenDialog(new Stage());
        try (BufferedReader br = new BufferedReader(new FileReader(file))){
            Alert alert = new Alert(Alert.AlertType.WARNING,
                    "Are you sure you want to open this canvas? \n" +
                            "Any unsaved changes will be permanently lost.",
                    ButtonType.YES, ButtonType.NO);
            alert.showAndWait();
            if (alert.getResult() == ButtonType.NO) {
                return;
            }
            model.clear();
            while (br.ready()) {
                String[] details = br.readLine().split(",");
                Point start = new Point(Double.parseDouble(details[0]), Double.parseDouble(details[1]));
                Point end = new Point(Double.parseDouble(details[2]), Double.parseDouble(details[3]));
                String type = details[4];
                PaintProperties pp = new PaintProperties(
                        Boolean.parseBoolean(details[5]), Color.web(details[6]),
                        Color.web(details[7]), Double.parseDouble(details[8]), Integer.parseInt(details[9]));
                ArrayList<Point> path = new ArrayList<>();
                if (details.length >= 11) {
                    for (int i = 10; i < details.length - 1; i += 2) {
                        path.add(new Point(Double.parseDouble(details[i]), Double.parseDouble(details[i + 1])));
                    }
                }
                model.addShape(PaintStrategy.getPaintStrategy(type, start, end, pp, path));
            }
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.WARNING,
                    "Something went wrong while opening canvas!",
                    ButtonType.OK);
            alert.showAndWait();
        }
    }
}
