package ca.utoronto.utm.assignment2.paint;


import ca.utoronto.utm.assignment2.paint.controlPanels.PropertiesPanel;
import ca.utoronto.utm.assignment2.paint.controlPanels.ShapeChooserPanel;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Paint extends Application {
    PaintModel model; // Model
    PaintView view; // View
    PaintController controller; // Controller
    MyMunuBar menuBar; // Control
    ShapeChooserPanel shapeChooserPanel; // Control
    PropertiesPanel propertiesPanel; // Control
    // commandManager;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {

        this.model = new PaintModel();
        menuBar = new MyMunuBar();
        shapeChooserPanel = new ShapeChooserPanel();
        propertiesPanel = new PropertiesPanel();
        this.controller = new PaintController(model, shapeChooserPanel, propertiesPanel);
        this.view = new PaintView(model, controller);



        BorderPane root = new BorderPane();
        root.setTop(menuBar.createMenuBar());
        root.setCenter(view);
        BorderPane left = new BorderPane();
        left.setTop(shapeChooserPanel);
        left.setCenter(propertiesPanel);
        root.setLeft(left);
        //BorderPane right = new BorderPane();
        //right.setTop(this.stepsPanel);
        //right.setCenter(new EditingPanel(this));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Paint");
        stage.show();


        //this.commandManager = new CommandManager(view);

        // listen to window size change events and change canvas size correspondingly
        // width change handler
        stage.widthProperty().addListener((obs, oldWidth, newWidth) -> {
            view.setWidth(newWidth.doubleValue());
            view.update();
        });
        // height change handler
        stage.heightProperty().addListener((obs, oldHeight, newHeight) -> {
            view.setHeight(newHeight.doubleValue());
            view.update();
        });
    }
}
