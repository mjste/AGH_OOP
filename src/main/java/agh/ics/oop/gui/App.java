package agh.ics.oop.gui;

import agh.ics.oop.*;
import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;

import java.util.LinkedList;
import java.util.List;

public class App extends Application {
    IWorldMap map;

    @Override
    public void start(Stage primaryStage) throws Exception {
        GridPane grid = new GridPane();
        Vector2d v0 = map.getBoundaries()[0];
        Vector2d v1 = map.getBoundaries()[1];
        int cellSize = 30;
        int cellWidth = cellSize;
        int cellHeight = cellSize;

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
//            int j = y-v0.y+1;
            int j = v1.y-y+1;
//            int y = v1.y-j+1;
            Label label = new Label(Integer.toString(y));
            grid.add(label, 0, j, 1, 1);
            grid.getRowConstraints().add(new RowConstraints(cellHeight));
            GridPane.setHalignment(label, HPos.CENTER);
            GridPane.setValignment(label, VPos.CENTER);
        }

//        for (int x = v0.x; x <= v1.x; x++) {
//            for (int y = 0; y < 10; y++) {
//                int i = x+1;
//                int j = y+1;
//                Label label = new Label("~");
//                grid.add(label, i, j, 1, 1);
//            }
//        }

        for (int i = v0.x; i <= v1.x; i++) {
            for (int j = v0.y; j <= v1.y; j++) {
                int x = i-v0.x+1;
                int y = v1.y-j+1;
                String str;
                if (map.objectAt(new Vector2d(i, j)) != null) {
                    str = map.objectAt(new Vector2d(i, j)).toString();
                } else {
                    str = "";
                }
                Label label = new Label(str);
                grid.add(label, x, y, 1, 1);
                GridPane.setHalignment(label, HPos.CENTER);
                GridPane.setValignment(label, VPos.CENTER);
            }
        }


        grid.setGridLinesVisible(true);
        Scene scene = new Scene(grid, 400, 400);

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
            IEngine engine = new SimulationEngine(directions, map, positions);
            engine.run();
        } catch (IllegalArgumentException ex) {
            System.out.println(ex.getMessage());
        }
    }
}