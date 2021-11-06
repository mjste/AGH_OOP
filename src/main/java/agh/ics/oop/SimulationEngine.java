package agh.ics.oop;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SimulationEngine implements IEngine{
    private List<MoveDirection> moveDirections;
    private List<Vector2d> positions;
    private IWorldMap map;
    private List<Animal> animalList;

    public SimulationEngine(MoveDirection[] moveDirections, IWorldMap iWorldMap, Vector2d[] positions){
        this.moveDirections = Arrays.asList(moveDirections);
        this.map = iWorldMap;
        this.positions = Arrays.asList(positions);
        this.animalList = new ArrayList<>();

        for (Vector2d position : positions){
            Animal animal = new Animal(map, position);
            if (map.place(animal))
            {
                animalList.add(animal);
            }
        }
    }

    @Override
    public void run() {
        int n = moveDirections.size();
        int m = animalList.size();

        System.out.println(map.toString());
        for (int i = 0; i < n; i++){
            animalList.get(i % m).move(moveDirections.get(i % n));
            System.out.println(map.toString());
        }

    }
}
