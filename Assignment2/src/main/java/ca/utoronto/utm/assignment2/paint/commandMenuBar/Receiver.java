package ca.utoronto.utm.assignment2.paint.commandMenuBar;

import ca.utoronto.utm.assignment2.paint.PaintModel;

public class Receiver {
    public static void execute(PaintModel model, String cmd) {
        Command c = null;
        switch (cmd) {
            case "Copy":
                break;
            case "Cut":
                break;
            case "Exit":
                c = new CommandExit();
                break;
            case "New":
                break;
            case "Open":
                break;
            case "Paste":
                break;
            case "Redo":
                c = new CommandRedo();
                break;
            case "Save":
                break;
            case "Undo":
                c = new CommandUndo();
                break;
            default:
                break;
        }
        if (c != null) c.execute(model);
    }
}
