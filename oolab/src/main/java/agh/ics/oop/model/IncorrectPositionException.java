package agh.ics.oop.model;

public class IncorrectPositionException extends Exception {
    public IncorrectPositionException(Vector2d position) {
        super("Position (%d,%d) is not correct".formatted(position.getX(), position.getY()));
    }
}
