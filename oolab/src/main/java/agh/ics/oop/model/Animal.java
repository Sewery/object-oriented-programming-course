package agh.ics.oop.model;

import java.util.Objects;

public class Animal implements WorldElement{

    private MapDirection orientation;
    private Vector2d position;

    public Animal(Vector2d position) {
        this.orientation = MapDirection.NORTH;
        this.position = position;

    }

    public Animal() {
        this(new Vector2d(2, 2));
    }


    @Override
    public String toString() {
        return orientation.toString();
    }

    public boolean isAt(Vector2d position) {
        return this.position.equals(position);
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
                Vector2d supposedPosition = position.add(orientation.toUnitVector());
                if (moveValidator.canMoveTo(supposedPosition)) {
                    position = supposedPosition;
                }
                break;
            }
            case BACKWARD: {
                Vector2d supposedPosition = position.subtract(orientation.toUnitVector());
                if (moveValidator.canMoveTo(supposedPosition)) {
                    position = supposedPosition;
                }
                break;
            }

        }
    }

    public MapDirection getOrientation() {
        return orientation;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Animal animal = (Animal) o;
        return getOrientation() == animal.getOrientation() && Objects.equals(getPosition(), animal.getPosition());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getOrientation(), getPosition());
    }

    @Override
    public Vector2d getPosition() {
        return position;
    }
}
