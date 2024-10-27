package agh.ics.oop;

import agh.ics.oop.model.MoveDirection;

public class World {
    public static void run(String[] args) {
        var moveDirections= OptionsParser.convertToMoveDirections(args);
        final String ANIMAL_GO = "Zwierzak idzie ";
        final String ANIMAL_TURN = "Zwierzak skreca ";
        for (var move : moveDirections) {
            switch(move){
                case FORWARD-> System.out.println(ANIMAL_GO + "do przodu");
                case BACKWARD-> System.out.println(ANIMAL_GO + "do tylu");
                case RIGHT-> System.out.println(ANIMAL_TURN + "w prawo");
                case LEFT-> System.out.println(ANIMAL_TURN + "w lewo");
            };
        }
        //System.out.println(String.join(", ",args));
    }
    public static void main(String[] args) {
//        System.out.println("System wystartowal");
//        run(args);
//        System.out.println("System zakonczyl dzialanie");

        Vector2d position1 = new Vector2d(1,2);
        System.out.println(position1);
        Vector2d position2 = new Vector2d(-2,1);
        System.out.println(position2);
        System.out.println(position1.add(position2));
    }
}
