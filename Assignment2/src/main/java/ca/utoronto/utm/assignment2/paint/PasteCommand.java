package ca.utoronto.utm.assignment2.paint;

public class PasteCommand implements Command {
    private PaintModel model;
    private Shape shape;

    public PasteCommand(PaintModel model, Shape shape) {
        this.model = model;
        this.shape = shape;
    }

    @Override
    public void execute() {
        model.addShape(shape);  // Add the shape to the model
    }

    @Override
    public void undo() {
        model.removeShape(shape);  // Remove the shape from the model
    }
}

