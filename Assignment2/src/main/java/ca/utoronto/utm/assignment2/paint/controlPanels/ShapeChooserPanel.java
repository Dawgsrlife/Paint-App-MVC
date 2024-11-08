package ca.utoronto.utm.assignment2.paint.controlPanels;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.layout.GridPane;

public class ShapeChooserPanel extends GridPane implements EventHandler<ActionEvent> {

    public ShapeChooserPanel() {

        // adding gap between elements
        this.setVgap(5.0);
        this.setPadding(new Insets(10.0));
        getStyleClass().add("shape-chooser-panel");

        int row = 0;
        // Ethan: using buttonLabels as mode breaks the code
        String[] svgs= {
                "M468-240q-96-5-162-74t-66-166q0-100 70-170t170-70q97 0 166 66t74 162l-84-25q-13-54-56-88.5T480-640q-66 0-113 47t-47 113q0 57 34.5 100t88.5 56l25 84ZM821-60 650-231 600-80 480-480l400 120-151 50 171 171-79 79Z",
                "M480-80q-83 0-156-31.5T197-197q-54-54-85.5-127T80-480q0-83 31.5-156T197-763q54-54 127-85.5T480-880q83 0 156 31.5T763-763q54 54 85.5 127T880-480q0 83-31.5 156T763-197q-54 54-127 85.5T480-80Zm0-80q134 0 227-93t93-227q0-134-93-227t-227-93q-134 0-227 93t-93 227q0 134 93 227t227 93Zm0-320Z",
                "M28.857 18c-0.8 5.65-5.227 10.125-10.857 10.997v1.003h-4v-1.003c-5.63-0.871-10.057-5.347-10.858-10.997h-1.142v-4h1.188c0.924-5.493 5.293-9.81 10.812-10.664v-1.336h4v1.336c5.52 0.854 9.887 5.171 10.812 10.664h1.188v4h-1.143zM15 29h2v-2h-2v2zM3 15v2h2v-2h-2zM17 3h-2v2h2v-2zM18 4.852v1.148h-4v-1.148c-4.69 0.824-8.401 4.483-9.292 9.148h1.292v4h-1.346c0.775 4.822 4.545 8.638 9.346 9.481v-1.481h4v1.481c4.801-0.844 8.571-4.659 9.346-9.481h-1.346v-4h1.292c-0.892-4.665-4.602-8.324-9.292-9.148zM29 15h-2v2h2v-2z",
                "M120-120v-720h720v720H120Zm80-80h560v-560H200v560Zm0 0v-560 560Z",
                "M260.021,65.636c4.143,0,7.5-3.358,7.5-7.5v-50c0-4.142-3.357-7.5-7.5-7.5h-50 c-4.143,0-7.5,3.358-7.5,7.5v17.5H65v-17.5c0-4.142-3.357-7.5-7.5-7.5h-50c-4.143,0-7.5,3.358-7.5,7.5v50c0,4.142,3.357,7.5,7.5,7.5 H25v136.25H7.5c-4.143,0-7.5,3.358-7.5,7.5v50c0,4.142,3.357,7.5,7.5,7.5h50c4.143,0,7.5-3.358,7.5-7.5v-17.5h137.521v17.5 c0,4.142,3.357,7.5,7.5,7.5h50c4.143,0,7.5-3.358,7.5-7.5v-50c0-4.142-3.357-7.5-7.5-7.5h-17.5V65.636H260.021z M217.521,15.636h35 v35h-35V15.636z M15,15.636h35v35H15V15.636z M50,251.886H15v-35h35V251.886z M252.521,251.886h-35v-35h35V251.886z M227.521,201.886h-17.5c-4.143,0-7.5,3.358-7.5,7.5v17.5H65v-17.5c0-4.142-3.357-7.5-7.5-7.5H40V65.636h17.5 c4.143,0,7.5-3.358,7.5-7.5v-17.5h137.521v17.5c0,4.142,3.357,7.5,7.5,7.5h17.5V201.886z",
                "M12.0001 3.75317L21.5509 20.2501H2.44922L12.0001 3.75317ZM5.05089 18.7501H18.9492L12.0001 6.74697L5.05089 18.7501Z",
                "M21.59,9.17l-9-6.54a1,1,0,0,0-1.18,0l-9,6.54a1,1,0,0,0-.36,1.12L5.49,20.87a1,1,0,0,0,1,.69H17.56a1,1,0,0,0,1-.69L22,10.29A1,1,0,0,0,21.59,9.17ZM16.84,19.56H7.16l-3-9.2L12,4.68l7.82,5.68Z",
                "M554-120q-54 0-91-37t-37-89q0-76 61.5-137.5T641-460q-3-36-18-54.5T582-533q-30 0-65 25t-83 82q-78 93-114.5 121T241-277q-51 0-86-38t-35-92q0-54 23.5-110.5T223-653q19-26 28-44t9-29q0-7-2.5-10.5T250-740q-10 0-25 12.5T190-689l-70-71q32-39 65-59.5t65-20.5q46 0 78 32t32 80q0 29-15 64t-50 84q-38 54-56.5 95T220-413q0 17 5.5 26.5T241-377q10 0 17.5-5.5T286-409q13-14 31-34.5t44-50.5q63-75 114-107t107-32q67 0 110 45t49 123h99v100h-99q-8 112-58.5 178.5T554-120Zm2-100q32 0 54-36.5T640-358q-46 11-80 43.5T526-250q0 14 8 22t22 8Z",
                "M600-80v-100L320-320H120v-240h172l108-124v-196h240v240H468L360-516v126l240 120v-50h240v240H600ZM480-720h80v-80h-80v80ZM200-400h80v-80h-80v80Zm480 240h80v-80h-80v80ZM520-760ZM240-440Zm480 240Z",
                "M690-240h190v80H610l80-80Zm-500 80-85-85q-23-23-23.5-57t22.5-58l440-456q23-24 56.5-24t56.5 23l199 199q23 23 23 57t-23 57L520-160H190Zm296-80 314-322-198-198-442 456 64 64h262Zm-6-240Z"
        };
        String[] commands = {"select", "Circle", "Oval", "Square", "Rectangle", "Triangle", "Polygon", "Squiggle", "Polyline", "PrecisionEraser"};
        for (int i = 0; i < commands.length; i++) {
            ShapeChooserPanelButton button = new ShapeChooserPanelButton(svgs[i], commands[i]);
            if (commands[i].equals("select")) button.setSelected(true);
            this.add(button, 0, row);
            row++;
            button.setOnAction(this);
        }
    }

    @Override
    public void handle(ActionEvent event) {
        ShapeChooserPanelButton source = (ShapeChooserPanelButton) event.getSource();
        for (Object o: this.getChildren()) {
            ShapeChooserPanelButton button = (ShapeChooserPanelButton) o;
            button.setSelected(button.equals(source));
        }
    }

    public String getMode() {
        String mode = "Select";
        for (Object o : this.getChildren()) {
            ShapeChooserPanelButton button = (ShapeChooserPanelButton) o;
            if (button.isSelected()) mode = button.getCommand();
        }
        return mode;
    }
}


