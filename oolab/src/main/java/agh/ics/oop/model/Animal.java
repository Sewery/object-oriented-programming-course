package agh.ics.oop.model;

import java.util.Objects;

public class Animal{

    private MapDirection orientation;
    private Vector2d currPosition;

    public Animal(Vector2d currPosition) {
        this.orientation = MapDirection.NORTH;
        this.currPosition = currPosition;

    }

    public Animal() {
        this(new Vector2d(2, 2));
    }


    @Override
    public String toString() {
        return orientation.toString();
    }

    public boolean isAt(Vector2d position) {
        return this.currPosition.equals(position);
    }

    public void move(MoveDirection movement, MoveValidator moveValidator) {
        switch (movement) {
            case RIGHT:
                orientation = orientation.next();
                break;
            case LEFT:
                orientation = orientation.previous();
                break;
            case FORWARD: {
                Vector2d vec = currPosition.add(orientation.toUnitVector());
                if (moveValidator.canMoveTo(vec)) {
                    currPosition = vec;
                }
                break;
            }
            case BACKWARD: {
                Vector2d vec = currPosition.subtract(orientation.toUnitVector());
                if (moveValidator.canMoveTo(vec)) {
                    currPosition = vec;
                }
                break;
            }

        }
    }

    public MapDirection getOrientation() {
        return orientation;
    }

    public Vector2d getCurrPosition() {
        return currPosition;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Animal animal = (Animal) o;
        return getOrientation() == animal.getOrientation() && Objects.equals(getCurrPosition(), animal.getCurrPosition());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getOrientation(), getCurrPosition());
    }
}
