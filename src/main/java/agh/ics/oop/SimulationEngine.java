package agh.ics.oop;

import java.util.ArrayList;
import java.util.List;

public class SimulationEngine implements IEngine{
    private List<MoveDirection> moveDirections;
    private IWorldMap iWorldMap;
    private List<Animal> animals;

    public SimulationEngine(MoveDirection[] md, IWorldMap iwm, Vector2d[] positions){
        for (MoveDirection movdir: md){
            moveDirections.add(movdir);
        }
        this.iWorldMap = iwm;
        this.animals = new ArrayList<Animal>();

        for (Vector2d animal_position : positions){
            Animal animal = new Animal(iWorldMap, animal_position);
            this.animals.add(animal);
            this.iWorldMap.place(animal);
        }

    }


    @Override
    public void run() {
        for (int i = 0; i < moveDirections.size(); i++){
            int n = animals.size();
            animals.get(i % n).move(moveDirections.get(i));
        }
    }
}
