package agh.ics.oop.model;

public enum MapDirection {
    NORTH("Polnoc", new Vector2d(0,1)),
    SOUTH("Poludnie",new Vector2d(-1,0)),
    EAST("Wschod",new Vector2d(0,-1)),
    WEST("Zachod",new Vector2d(1,0));
    private final String directionPol;
    private final Vector2d position;

    MapDirection(String directionPol, Vector2d position) {
        this.directionPol = directionPol;
        this.position = position;
    }

    @Override
    public String toString() {
        return  directionPol;
    }

    public MapDirection previous() {
        return switch(this){
            case NORTH -> WEST;
            case WEST -> SOUTH;
            case SOUTH -> EAST;
            case EAST -> NORTH;
        };
    }
    public MapDirection next() {
        return switch(this){
            case NORTH -> EAST;
            case EAST -> SOUTH;
            case SOUTH -> WEST;
            case WEST -> NORTH;
        };
    }
    public Vector2d toUnitVector(){
        return position;
    }

}
