package agh.ics.oop.model;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class RandomVector2dGenerator {
    public static Set<Vector2d> generateUniqueList(
            int size
    ) {
        Random randGenerator = new Random();
        double maxPosition =Math.sqrt(size*10);
        randGenerator.nextInt();
        Set<Vector2d> uniqueSet = new HashSet<Vector2d>();
        int counter = 0;
        while(counter < size) {
            int x = randGenerator.nextInt((int)maxPosition);
            int y = randGenerator.nextInt((int)maxPosition);
            uniqueSet.add(new Vector2d(x,y));
            counter++;

        }
        return uniqueSet;
    }
}
