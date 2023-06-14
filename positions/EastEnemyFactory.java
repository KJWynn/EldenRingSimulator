package game.positions;

import game.actors.enemies.*;

/**
 * A class that will spawn east side enemies in the east side of the map.
 * @author Khor Jia Wynn,Saw Tze Ying,Wong An Hong
 */
public class EastEnemyFactory implements EnemyFactory{
    /**
     * Creates an east side Canine type enemy.
     *
     * @return a GiantDog enemy
     */
    @Override
    public Canine createCanine() {
        return new GiantDog();
    }

    /**
     * Creates an east side Ocean type enemy.
     * @return a GiantCrayFish enemy
     */
    @Override
    public Ocean createOcean() {
        return new GiantCrayFish();
    }

    /**
     * Creates an east side Skeletal type enemy.
     * @return a SkeletalBandit enemy
     */
    @Override
    public Skeletal createSkeleton() {
        return new SkeletalBandit();
    }

}
