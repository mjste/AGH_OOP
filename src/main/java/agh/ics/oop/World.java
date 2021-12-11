package agh.ics.oop;


import agh.ics.oop.gui.App;
import javafx.application.Application;

public class World {
    public static void main(String args[]) {
        System.out.println(Thread.currentThread().getName());

        Application.launch(App.class, args);
    }
}
