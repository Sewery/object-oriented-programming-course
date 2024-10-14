package agh.ics.oop;

import agh.ics.oop.model.MoveDirection;

public class World {
    public static void run(String[] args) {
        var moveDirections= OptionsParser.convertToMoveDirections(args);
        for (var move : moveDirections) {
            switch(move){
                case FORWARD-> System.out.println("zwierzak idzie do przodu");
                case BACKWARD-> System.out.println("zwierzak idzie do tylu");
                case RIGHT-> System.out.println("zwierzak skreca w prawo");
                case LEFT-> System.out.println("zwierzak skreca w lewo");
            };
        }
        System.out.println(String.join(", ",args));
    }
    public static void main(String[] args) {
        System.out.println("system wystartowal");
        run(args);
        System.out.println("system zakonczyl dzialanie");


    }
}
