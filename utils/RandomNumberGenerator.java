package game.utils;

import java.util.Random;

/**
 * A random number generator
 * Created by:
 * @author Adrian Kristanto
 * Modified by: Khor Jia Wynn,Saw Tze Ying,Wong An Hong
 *
 */
public class RandomNumberGenerator {

    /**
     * Use a single Random object to optimize performance
     */
    static Random random = new Random();

    /**
     * Returns an integer between 0(inclusive) and the bound(exclusive) if bound  > 0, 0 otherwise
     * @param bound limit
     * @return 0 or an integer within the bound
     */
    public static int getRandomInt(int bound) {
        return bound > 0 ? random.nextInt(bound) : 0;
    }

    /**
     * Returns a random integer within the specified range, inclusive of both limits
     * @param lowerBound lower limit
     * @param upperBound upper limit
     * @return A random integer within the specified range
     */
    public static int getRandomInt(int lowerBound, int upperBound) {
        int range = upperBound - lowerBound + 1;
        return random.nextInt(range) + lowerBound;
    }

    /**
     * Returns success or failure based on the chance(bound) provided, e.g. spawn chance, hit rate etc
     * @param chance The success rate
     * @return true if success, false if failure
     */
    public static boolean getSuccessOrFailure(int chance){
        return (random.nextInt(100) + 1) <= chance;
    }
}
