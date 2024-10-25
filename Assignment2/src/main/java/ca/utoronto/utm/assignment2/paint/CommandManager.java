package ca.utoronto.utm.assignment2.paint;

import java.util.Stack;

public class CommandManager {
    private Stack<Command> undoStack = new Stack<>();
    private Stack<Command> redoStack = new Stack<>();
    private View view;


    public CommandManager(View view) {this.view = view; }
    public void executeCommand(Command command) {
        command.execute();
        undoStack.push(command);
        redoStack.clear();  // Clear redo stack after a new command is executed
        System.out.println("Executed command. Undo stack size: " + undoStack.size());
    }

    public void redo() {
        if (!redoStack.isEmpty()) {
            Command command = redoStack.pop();  // Get the last undone command
            command.execute();                  // Re-execute the command
            undoStack.push(command);            // Move it back to undoStack
        } else {
            System.out.println("Redo stack is empty!");
        }
    }


    public void undo() {
        if (!undoStack.isEmpty()) {
            Command command = undoStack.pop();  // Get the last command from undoStack
            command.undo();                     // Undo the command
            redoStack.push(command);            // Move the undone command to redoStack
        } else {
            System.out.println("Undo stack is empty!");
        }
    }


}