package agh.ics.oop.model;

public class ConsoleMapDisplay implements MapChangeListener {
    private int mapChangedCount;

    @Override
    public void mapChanged(WorldMap worldMap, String message) {
        System.out.println("-----------------");
        System.out.println(message);
        System.out.println(worldMap.toString());
        System.out.println("Number of changes: " + mapChangedCount);
        mapChangedCount++;
    }

}
