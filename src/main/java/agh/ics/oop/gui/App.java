package agh.ics.oop.gui;

import agh.ics.oop.*;
import agh.ics.oop.enums.MoveDirection;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class App extends Application implements IPositionChangeObserver {
    private IWorldMap map;
    private GridPane grid;
    private SimulationEngine engine;
    private Thread engineThread;
    private TextField textField;

    @Override
    public void start(Stage primaryStage) throws Exception {
//        engineThread = new Thread(engine);
//        engineThread.start();

        primaryStage.setTitle("Simulation");

        HBox hBox = new HBox();
        VBox vBox = new VBox(7);
        vBox.setMinWidth(200);

        textField = new TextField("f b r l f f r r f f f f f f f f f");
        Button submitButton = new Button("Submit");
        HBox textInputHBox = new HBox(5,textField, submitButton);
        Button startButton = new Button("Start");

        textField.setMaxWidth(100);

        vBox.setAlignment(Pos.CENTER);
        textInputHBox.setAlignment(Pos.CENTER);
        vBox.getChildren().add(textInputHBox);
        vBox.getChildren().add(startButton);

        startButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                startEngine();
            }
        });

        submitButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                submitActions();
            }
        });

        grid = new GridPane();

        hBox.getChildren().add(vBox);
        hBox.getChildren().add(grid);


        Scene scene = new Scene(hBox, 800, 600);
        refreshGrid();

        primaryStage.setScene(scene);
        primaryStage.show();

    }

    @Override
    public void init() {
        try {
            String[] args = getParameters().getRaw().toArray(new String[0]);
            MoveDirection[] directions = new OptionsParser().parse(args);
            map = new GrassField(10);
            Vector2d[] positions = { new Vector2d(2, 2), new Vector2d(3,4)};
            System.out.println("test");
            engine = new SimulationEngine(directions, map, positions);
            engine.addObserver(this);
        } catch (IllegalArgumentException ex) {
            System.out.println("App.init() failed: "+ex.getMessage());
        }
    }

    private void startEngine() {
        if (engineThread == null) {
            engineThread = new Thread(engine);
            engineThread.start();
        } else {
            if (!engineThread.isAlive()) {
                engineThread = new Thread(engine);
                engineThread.start();
            }
        }
    }

    private void submitActions() {
        String text = textField.getText();
        if (text != null) {
            MoveDirection[] moveDirections = new OptionsParser().parse(text.split(" "));
            engine.setMoveDirections(moveDirections);
        }
    }

    private void refreshGrid() {
        int cellSize = 40;
        int cellWidth = cellSize;
        int cellHeight = cellSize;
        Vector2d v0 = map.getBoundaries()[0];
        Vector2d v1 = map.getBoundaries()[1];
        grid.getChildren().clear();
        grid.getColumnConstraints().clear();
        grid.getRowConstraints().clear();
        makeGridCoordinates(grid, cellWidth, cellHeight, v0, v1);
        addWorldElements(grid, v0, v1);
        grid.setGridLinesVisible(false);
        grid.setGridLinesVisible(true);
    }

    private void makeGridCoordinates(GridPane grid, int cellWidth, int cellHeight, Vector2d v0, Vector2d v1) {
        Label originLabel = new Label("y/x");
        grid.add(originLabel, 0, 0, 1, 1);
        GridPane.setHalignment(originLabel, HPos.CENTER);
        GridPane.setValignment(originLabel, VPos.CENTER);
        grid.getColumnConstraints().add(new ColumnConstraints(cellWidth));
        grid.getRowConstraints().add(new RowConstraints(cellHeight));
        for (int x = v0.x; x <= v1.x; x++) {
            int i = x-v0.x+1;
            Label label = new Label(Integer.toString(x));
            grid.add(label, i, 0, 1, 1);
            grid.getColumnConstraints().add(new ColumnConstraints(cellWidth));
            GridPane.setHalignment(label, HPos.CENTER);
            GridPane.setValignment(label, VPos.CENTER);
        }
        for (int y = v0.y; y <= v1.y; y++) {
            int j = v1.y-y+1;
            Label label = new Label(Integer.toString(y));
            grid.add(label, 0, j, 1, 1);
            grid.getRowConstraints().add(new RowConstraints(cellHeight));
            GridPane.setHalignment(label, HPos.CENTER);
            GridPane.setValignment(label, VPos.CENTER);
        }
    }

    private void addWorldElements(GridPane grid, Vector2d v0, Vector2d v1) {
        for (int i = v0.x; i <= v1.x; i++) {
            for (int j = v0.y; j <= v1.y; j++) {
                int x = i-v0.x+1;
                int y = v1.y-j+1;
                Object obj = map.objectAt(new Vector2d(i, j));
                if (obj != null) {
                    VBox vbox = new GuiElementBox((IMapElement) obj).getVBox();
                    grid.add(vbox, x, y);
                    GridPane.setHalignment(vbox, HPos.CENTER);
                    GridPane.setValignment(vbox, VPos.CENTER);
                }
            }
        }
    }

    @Override
    public void positionChanged(Vector2d oldPosition, Vector2d newPosition) {
        Platform.runLater(() -> {if (grid != null)
            refreshGrid();
            System.out.println(grid.isGridLinesVisible());});
    }
}
