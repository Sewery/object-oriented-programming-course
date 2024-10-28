package agh.ics.oop.model;

public class Animal {
    private MapDirection orientation;
    private Vector2d position;

    private final Vector2d LEFT_LOWER_BORDER = new Vector2d(0,0);
    private final Vector2d RIGHT_UPPER_BORDER = new Vector2d(4,4);

    public Animal() {
        this.orientation = MapDirection.NORTH;
        this.position = new Vector2d(2, 2);
    }

    public Animal(Vector2d position) {
        this.orientation = MapDirection.NORTH;
        this.position = position;
    }

    @Override
    public String toString() {
        return orientation.toString() + " " + position.toString();
    }

    boolean isAt(Vector2d position) {
        return this.position.equals(position);
    }

    public void move(MoveDirection movement) {
        switch (movement) {
            case RIGHT:
                orientation = orientation.next();
            case LEFT:
                orientation = orientation.previous();
            case FORWARD:
            {
                Vector2d v1 = position.add(orientation.toUnitVector());
                if( v1.precedes(RIGHT_UPPER_BORDER) && v1.follows(LEFT_LOWER_BORDER)){
                    position = v1;
                }
            }
            case BACKWARD:
            {
                Vector2d v1 = position.add(orientation.toUnitVector().opposite());
                if( v1.precedes(RIGHT_UPPER_BORDER) && v1.follows(LEFT_LOWER_BORDER)){
                    position = v1;
                }
            }

        }
    }
}
