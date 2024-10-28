package agh.ics.oop;

import agh.ics.oop.model.Animal;
import agh.ics.oop.model.MoveDirection;

public class World {
    private static void run(String[] userInput) {
        var moveDirections= OptionsParser.convertToMoveDirections(userInput);
        for (var move : moveDirections) {
            switch(move){
                case FORWARD-> System.out.println("Zwierzak idzie do przodu");
                case BACKWARD-> System.out.println("Zwierzak idzie do tylu");
                case RIGHT-> System.out.println("Zwierzak skreca w prawo");
                case LEFT-> System.out.println("Zwierzak skreca w lewo");
            };
        }
        //System.out.println(String.join(", ",args));
    }
    public static void main(String[] args) {

        Animal animal = new Animal();
        System.out.println(animal);
//        System.out.println("System wystartowal");
//        run(args);
//        System.out.println("System zakonczyl dzialanie");

//        Vector2d position1 = new Vector2d(1,2);
//        System.out.println(position1);
//        Vector2d position2 = new Vector2d(-2,1);
//        System.out.println(position2);
//        System.out.println(position1.add(position2));
    }
}
