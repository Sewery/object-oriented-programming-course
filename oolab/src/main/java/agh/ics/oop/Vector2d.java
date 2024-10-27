package agh.ics.oop;

import static java.lang.Math.max;
import static java.lang.Math.min;

public class Vector2d {
    private final int x;
    private final int y;

    public Vector2d(int x, int y) {
        this.x = x;
        this.y = y;
    }
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
    @Override
    public String toString() {
        return "(" + x + "," + y + ")";
    }
    boolean precedes(Vector2d other){
        return x <= other.x && y <= other.y;
    }
    boolean follows(Vector2d other){
        return x >= other.x && y >= other.y;
    }
    Vector2d add(Vector2d other){
        return new Vector2d(x+other.getX(), y+other.getY());
    }
    Vector2d subtract(Vector2d other){
        return new Vector2d(x-other.getX(), y-other.getY());
    }
    Vector2d upperRight(Vector2d other){
        return new Vector2d(max(x,other.getX()), max(y,other.getY()));
    }
    Vector2d lowerLeft(Vector2d other){
        return new Vector2d(min(x,other.getX()), min(y,other.getY()));
    }
    Vector2d opposite(){
        return new Vector2d(-x, -y);
    }
    @Override
    public boolean equals(Object other){
        if(other instanceof Vector2d v){
            return x == v.getX() && y == v.getY();
        }
        return false;
    }
}
