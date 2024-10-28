package agh.ics.oop.model;

public enum MapDirection {
    NORTH("Polnoc"),
    SOUTH("Poludnie"),
    EAST("Wschod"),
    WEST("Zachod");
    private final String directionPol;
    MapDirection(String directionPol) {
        this.directionPol = directionPol;
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
        return switch (this){
            case NORTH -> new Vector2d(0,1);
            case EAST -> new Vector2d(-1,0);
            case SOUTH -> new Vector2d(0,-1);
            case WEST -> new Vector2d(1,0);
        };
    }

}
