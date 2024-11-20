package agh.ics.oop.model;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class RandomPositionsGenerator {

    public static final Long BASIC_SEED = 6576458L;

    public static Set<Vector2d> generateUniqueSet(
            int size,
            int maxElementSize
    ) {
        return generateUniqueSetFromRandom(new Random(BASIC_SEED), maxElementSize, size);
    }
    public static Set<Vector2d> generateUniqueSet(
            int size,
            int maxElementSize,
            Long seed
    ) {
        return generateUniqueSetFromRandom(new Random(seed), maxElementSize, size);

    }
    private static Set<Vector2d> generateUniqueSetFromRandom(
            Random randGenerator,
            int maxElementSize,
            int size
    ){
        randGenerator.nextInt();
        Set<Vector2d> uniqueSet = new HashSet<>();
        while(uniqueSet.size() < size) {
            int x = randGenerator.nextInt(maxElementSize);
            int y = randGenerator.nextInt(maxElementSize);
            uniqueSet.add(new Vector2d(x,y));

        }
        return uniqueSet;
    }
}

