package agh.ics.oop.model;

import agh.ics.oop.model.util.Boundary;

public class RectangularMap extends AbstractWorldMap implements WorldMap {
    private final Boundary boundary;
    public RectangularMap(int width, int height) {
        Vector2d lowerLeftBorder = new Vector2d(0, 0);
        Vector2d upperRightBorder = new Vector2d(width-1, height-1);
        this.boundary = new Boundary(lowerLeftBorder, upperRightBorder);
    }

    @Override
    public Boundary getCurrentBounds() {
        return boundary;
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        return !isOccupied(position)
                && position.precedes(boundary.rightUpperCorner())
                && position.follows(boundary.leftLowerCorner());
    }

}
