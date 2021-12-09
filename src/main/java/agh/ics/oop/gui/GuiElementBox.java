package agh.ics.oop.gui;

import agh.ics.oop.Animal;
import agh.ics.oop.IMapElement;
import agh.ics.oop.IWorldMap;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.awt.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class GuiElementBox {
    Image image;
    ImageView imageView;

    public GuiElementBox(IMapElement element) {
        try {
            image = new Image(new FileInputStream(element.imagePath()));
            imageView = new ImageView(image);
            imageView.setFitWidth(20);
            imageView.setFitHeight(20);
            Label label = new Label(element.getPosition().toString());
        } catch (FileNotFoundException ex) {
            System.out.println("file not found :( ");
        }
    }
}

// koniec gdzieś w labie 8