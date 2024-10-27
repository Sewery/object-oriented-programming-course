package agh.ics.oop;

public enum MapDirection {
    NORTH("Polnoc"),
    SOUTH("Poludnie"),
    EAST("Wschood"),
    WEST("Zachod");
    private final String directionPol;
    MapDirection(String directionPol) {
        this.directionPol = directionPol;
    }

    @Override
    public String toString() {
        return  directionPol;
    }

    public static MapDirection previous(MapDirection direction) {
        return switch(direction){
            case NORTH -> WEST;
            case WEST -> SOUTH;
            case SOUTH -> EAST;
            case EAST -> NORTH;
        };
    }
    public static MapDirection next(MapDirection direction) {
        return switch(direction){
            case NORTH -> EAST;
            case EAST -> SOUTH;
            case SOUTH -> WEST;
            case WEST -> NORTH;
        };
    }
    public static Vector2d toUnitVector(MapDirection direction){
        return switch (direction){
            case NORTH -> new Vector2d(0,1);
            case EAST -> new Vector2d(-1,0);
            case SOUTH -> new Vector2d(0,-1);
            case WEST -> new Vector2d(1,0);
        };
    }

}
