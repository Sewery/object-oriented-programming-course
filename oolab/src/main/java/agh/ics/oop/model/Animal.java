package agh.ics.oop.model;

public class Animal {

    private MapDirection orientation;
    private Vector2d position;

    private static final Vector2d LEFT_LOWER_BORDER = new Vector2d(0,0);
    private static final Vector2d RIGHT_UPPER_BORDER = new Vector2d(4,4);

    public Animal(Vector2d position) {
        this.orientation = MapDirection.NORTH;
        if( position.precedes(RIGHT_UPPER_BORDER) && position.follows(LEFT_LOWER_BORDER)) {
            this.position = position;
        }else{
            throw new IllegalArgumentException("Animal position is invalid");
        }
    }

    public Animal() {
        this(new Vector2d(2, 2));
    }


    @Override
    public String toString() {
        return orientation + " " + position;
    }

    public boolean isAt(Vector2d position) {
        return this.position.equals(position);
    }

    public void move(MoveDirection movement) {
        switch (movement) {
            case RIGHT:
                orientation = orientation.next();
                break;
            case LEFT:
                orientation = orientation.previous();
                break;
            case FORWARD:
            {
                Vector2d vec = position.add(orientation.toUnitVector());
                if( vec.precedes(RIGHT_UPPER_BORDER) && vec.follows(LEFT_LOWER_BORDER)){
                    position = vec;
                }
                break;
            }
            case BACKWARD:
            {
                Vector2d vec = position.subtract(orientation.toUnitVector());
                if( vec.precedes(RIGHT_UPPER_BORDER) && vec.follows(LEFT_LOWER_BORDER)){
                    position = vec;
                }
                break;
            }

        }
    }
    public MapDirection getOrientation() {
        return orientation;
    }
    public Vector2d getPosition() {
        return position;
    }

}
