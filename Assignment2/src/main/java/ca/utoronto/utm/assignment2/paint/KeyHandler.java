package ca.utoronto.utm.assignment2.paint;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class KeyHandler implements EventHandler<KeyEvent> {
    final KeyCode UNDO = KeyCode.Z;
    final KeyCode REDO = KeyCode.Y;

    PaintModel model;

    public KeyHandler(PaintModel model) {
       this.model = model;
    }

    @Override
    public void handle(KeyEvent keyEvent) {
        EventType<KeyEvent> eventType = keyEvent.getEventType();
        if(eventType.equals(KeyEvent.KEY_PRESSED)) {
            if (keyEvent.isControlDown()) {
                if (keyEvent.getCode() == UNDO) {
                    System.out.println("undo");
                    model.undo();
                } else if (keyEvent.getCode() == REDO) {
                    System.out.println("redo");
                    model.redo();
                }
            }
        }
    }
}
