package agh.ics.oop.model.util;

import agh.ics.oop.model.MapChangeListener;
import agh.ics.oop.model.WorldMap;

import java.util.concurrent.atomic.AtomicInteger;

public class ConsoleMapDisplay implements MapChangeListener {
    private final AtomicInteger mapChangedCount = new AtomicInteger(0);

    @Override
    public void mapChanged(WorldMap worldMap, String message) {
        synchronized (System.out) {
            System.out.println("-----------------");
            System.out.println(message);
            System.out.println(worldMap.toString());
            System.out.println("Map id: "+worldMap.getId());
            System.out.println("Number of changes: " +  mapChangedCount.addAndGet(1));
            System.out.println("-----------------");
        }

    }

}
