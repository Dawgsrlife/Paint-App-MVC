# CSC207 Paint Application

## Overview

This project is a **JavaFX-based paint application** developed as part of the **CSC207: Software Design** course at the **University of Toronto Mississauga**. The application follows **Model-View-Controller (MVC) architecture** and incorporates **design patterns** such as **Command, Strategy, and Factory** to create an extensible and maintainable codebase.

## Features

- **Basic Drawing Tools:** Supports circles, rectangles, triangles, squares, polylines, and freehand squiggles.
- **Custom Shapes:** Polygon and oval shape-drawing functionalities.
- **Editing & Transformation:** Ability to resize, reposition, and erase shapes.
- **Undo/Redo Support:** Implemented using the **Command Pattern** for efficient action management.
- **Color & Stroke Customization:** Users can adjust **line thickness, fill style, and color selection** dynamically.
- **Smart Shape Detection:** Experimental feature that converts freehand squiggles into predefined geometric shapes.
- **Text Tool:** Allows users to insert and customize text within the canvas.
- **File Management:** Open, save, and create new projects using a custom `.a2s` file format.
- **User Interface Improvements:** Enhanced UI/UX for better usability, including a modernized layout and tool selection panel.

## Technologies Used

- **Language:** Java 22
- **UI Framework:** JavaFX 22
- **Version Control:** Git
- **Design Patterns:** MVC, Command, Strategy, Factory
- **Development Tools:** IntelliJ IDEA, GitHub

---

## Contributors & Roles

### **Jiaye Tian (tianji61)**
- Developed core **GUI improvements** and **property panels**.
- Fixed various **bugs related to shape rendering and event handling**.
- Improved **command menu functionalities** and handled multiple user stories.

### **Ethan Hua (huaethan)**
- Implemented **Smart Shape Snapping** and **Shape Recognition** features.
- Refactored the **architecture behind drawing mode selection**.
- Contributed to **undo/redo functionality** and **pull request management**.

### **Alexander He Meng (mengale1)**
- Developed **Triangle drawing functionality** and **Eraser Tool**.
- Integrated **stroke customization** and **polyline improvements**.
- Managed **pull requests and product backlog updates**.

### **Jiaqi Chen (chen2046)**
- Designed and implemented **text tool** and **command-based undo/redo system**.
- Worked on **UI/UX improvements** and **menubar enhancements**.
- Fixed **shape selection and drawing issues**.

---

## Project Structure

```fix
Assignment2/
│── .idea/
│── .mvn/
│── scrum/
│   │── architecture/
│   │   │── architecture.txt
│   │   │── Assignment2Current.uml
│   │   │── Assignment2Original.uml
│   │── finalArchitecture/
│   │   │── aNoteOnGit.txt
│   │   │── dailyScrumMeeting1.txt
│   │   │── dailyScrumMeeting2.txt
│   │   │── dailyScrumMeeting3.txt
│   │   │── dailyScrumMeeting4.txt
│   │   │── extraFeatures.txt
│   │   │── guiPrototype.png
│   │   │── planningNotes.txt
│   │   │── productBacklog.txt
│   │   │── pullRequests.txt
│   │   │── sprintBacklog1.txt
│   │   │── sprintBacklog2.txt
│   │   │── sprintBacklog3.txt
│   │   │── sprintBacklog4.txt
│   │   │── writing_feedback_s1.txt
│── src/
│   │── main/
│   │   │── java/
│   │   │   │── ca.utoronto.utm.assignment2/
│   │   │   │   │── paint/
│   │   │   │   │   │── commandMenuBar/
│   │   │   │   │   │   │── Command.java
│   │   │   │   │   │   │── CommandCopy.java
│   │   │   │   │   │   │── CommandCut.java
│   │   │   │   │   │   │── CommandExit.java
│   │   │   │   │   │   │── CommandMenuBar.java
│   │   │   │   │   │   │── CommandNew.java
│   │   │   │   │   │   │── CommandOpen.java
│   │   │   │   │   │   │── CommandPaste.java
│   │   │   │   │   │   │── CommandRedo.java
│   │   │   │   │   │   │── CommandSave.java
│   │   │   │   │   │   │── CommandUndo.java
│   │   │   │   │   │── controlPanels/
│   │   │   │   │   │   │── PropertiesPanel.java
│   │   │   │   │   │   │── ShapeChooserPanel.java
│   │   │   │   │   │   │── ShapeChooserPanelButton.java
│   │   │   │   │   │── Circle.java
│   │   │   │   │   │── Oval.java
│   │   │   │   │   │── Paint.java
│   │   │   │   │   │── PaintController.java
│   │   │   │   │   │── PaintModel.java
│   │   │   │   │   │── PaintProperties.java
│   │   │   │   │   │── PaintStrategy.java
│   │   │   │   │   │── PaintView.java
│   │   │   │   │   │── Point.java
│   │   │   │   │   │── Polygon.java
│   │   │   │   │   │── Polyline.java
│   │   │   │   │   │── PrecisionEraser.java
│   │   │   │   │   │── Rectangle.java
│   │   │   │   │   │── Shape.java
│   │   │   │   │   │── Square.java
│   │   │   │   │   │── Squiggle.java
│   │   │   │   │   │── Text.java
│   │   │   │   │   │── Triangle.java
```

---

## Lessons Learned

- **Agile Development & Scrum:** Effective **team collaboration** using **daily stand-ups, sprint backlogs, and pull request workflows**.
- **Design Patterns:** Applying **Command, Strategy, and MVC architecture** to structure maintainable code.
- **Version Control:** Managing a shared **GitHub repository**, handling **merge conflicts** and **branching strategies**.

---

## Future Improvements

- **Cloud Storage Integration:** Enable users to save projects online.
- **Advanced Editing Features:** Implement **layering, gradients, and shape alignment tools**.
- **More AI Features:** Improve **smart shape recognition** with ML-based predictions.

---

## License

This project was developed for educational purposes as part of **CSC207 Software Design** at the **University of Toronto Mississauga**.
