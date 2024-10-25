package ca.utoronto.utm.assignment2.paint;


import javafx.application.Application;
import javafx.stage.Stage;

public class Paint extends Application {
    PaintModel model; // Model
    View view; // View + Controller
    // commandManager;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {

        this.model = new PaintModel();
        // View + Controller
        this.view = new View(model, stage);

        //this.commandManager = new CommandManager(view);

        // listen to window size change events and change canvas size correspondingly
        PaintPanel panel = view.getPaintPanel();
        // width change handler
        stage.widthProperty().addListener((obs, oldWidth, newWidth) -> {
            panel.setWidth(newWidth.doubleValue());
            panel.update();
        });
        // height change handler
        stage.heightProperty().addListener((obs, oldHeight, newHeight) -> {
            panel.setHeight(newHeight.doubleValue());
            panel.update();
        });
    }
}
