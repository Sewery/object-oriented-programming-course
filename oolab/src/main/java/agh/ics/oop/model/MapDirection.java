package agh.ics.oop.model;

public enum MapDirection {
    NORTH("Polnoc", new Vector2d(0,1),"N"),
    SOUTH("Poludnie",new Vector2d(0,-1),"S"),
    EAST("Wschod",new Vector2d(1,0),"E"),
    WEST("Zachod",new Vector2d(-1,0),"W");
    private final String directionPol;
    private final Vector2d position;
    private final String shortName;
    MapDirection(String directionPol, Vector2d position, String shortName) {
        this.directionPol = directionPol;
        this.position = position;
        this.shortName = shortName;
    }

    @Override
    public String toString() {
        return  shortName;
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
