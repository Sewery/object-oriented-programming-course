package agh.ics.oop;

import agh.ics.oop.model.Animal;
import agh.ics.oop.model.MoveDirection;
import agh.ics.oop.model.Vector2d;

import java.util.ArrayList;
import java.util.List;

public class Simulation {

    private List<Animal> animals;
    private final List<MoveDirection> movements;

    public Simulation(List<Vector2d> positions, List<MoveDirection> movements) {
        this.movements = movements;
        animals = new ArrayList<>();
        for (var pos : positions) {
            animals.add(new Animal(pos));
        }
    }

    public void run() {
        int indexCurrentAnimal = 0;
        for (var moveDirection : movements) {
            var currentAnimal = animals.get(indexCurrentAnimal);
            currentAnimal.move(moveDirection);
            System.out.println("Zwierze %d : %s".formatted(indexCurrentAnimal, currentAnimal));
            indexCurrentAnimal++;
            indexCurrentAnimal %= animals.size();
        }

    }
    public List<Animal> getAnimals(){
        return animals;
    }

}
