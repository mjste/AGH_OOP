package agh.ics.oop.gui;

import agh.ics.oop.*;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class App extends Application implements IPositionChangeObserver {
    private IWorldMap map;

//    GridPane grid;

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Simulation");

        System.out.println("in start "+Thread.currentThread().getName());
        GridPane layoutGrid = new GridPane();
        GridPane grid = new GridPane();

        layoutGrid.add(grid, 1, 0, 1, 1);
        VBox vBox = new VBox();

        layoutGrid.getColumnConstraints().add(new ColumnConstraints(300));
        layoutGrid.getColumnConstraints().add(new ColumnConstraints(500));
        Button startButton = new Button();
        startButton.setText("Start Button");
        vBox.getChildren().add(startButton);

        layoutGrid.add(vBox, 0, 0, 1, 1);

        int cellSize = 40;
        int cellWidth = cellSize;
        int cellHeight = cellSize;

        refreshGrid(grid, cellWidth, cellHeight);

        Scene scene = new Scene(layoutGrid, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    @Override
    public void init() {
        System.out.println("inside app.init"+Thread.currentThread().getName());
        try {
            String[] args = getParameters().getRaw().toArray(new String[0]);
            MoveDirection[] directions = new OptionsParser().parse(args);
            map = new GrassField(10);
            Vector2d[] positions = { new Vector2d(2, 2), new Vector2d(3,4)};
            SimulationEngine engine = new SimulationEngine(directions, map, positions);
            Thread engineThread = new Thread(engine);
            engineThread.start();
        } catch (IllegalArgumentException ex) {
            System.out.println("App.init() failed: "+ex.getMessage());
        }

    }

    private void refreshGrid(GridPane grid, int cellWidth, int cellHeight) {
        Vector2d v0 = map.getBoundaries()[0];
        Vector2d v1 = map.getBoundaries()[1];
        grid.getChildren().clear();
        makeGridCoordinates(grid, cellWidth, cellHeight, v0, v1);
        addWorldElements(grid, v0, v1);
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
                    grid.add(vbox, x, y, 1, 1);
                    GridPane.setHalignment(vbox, HPos.CENTER);
                    GridPane.setValignment(vbox, VPos.CENTER);
                }
            }
        }
    }

    @Override
    public void positionChanged(Vector2d oldPosition, Vector2d newPosition) {

    }
}
