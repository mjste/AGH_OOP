package agh.ics.oop.gui;

import agh.ics.oop.Animal;
import agh.ics.oop.IMapElement;
import agh.ics.oop.IWorldMap;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class GuiElementBox {
    private Image image;
    private ImageView imageView;
    private VBox vBox;

    public GuiElementBox(IMapElement element) {
        try {
            image = new Image(new FileInputStream(element.imagePath()));
            imageView = new ImageView(image);
            imageView.setFitWidth(20);
            imageView.setFitHeight(20);
            Label label = new Label(element.getPosition().toString());

            vBox = new VBox();
            vBox.getChildren().add(imageView);
            vBox.getChildren().add(label);
            vBox.setAlignment(Pos.CENTER);
        } catch (FileNotFoundException ex) {
            System.out.println("file not found :( ");
        }
    }

    public VBox getVBox() {
        return vBox;
    }
}

// koniec gdzieś w labie 8