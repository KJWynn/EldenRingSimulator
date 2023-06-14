package game.positions;

import game.actors.enemies.*;

/**
 * A class that represents the spawning ground of west enemies
 * @author Khor Jia Wynn,Saw Tze Ying,Wong An Hong
 */
public class WestEnemyFactory implements EnemyFactory{
    /**
     * Creates a west side Canine type enemy.
     *
     * @return a LoneWolf enemy
     */
    @Override
    public Canine createCanine() {
        return new LoneWolf();
    }

    /**
     * Creates a west side Ocean type enemy.
     * @return a GiantCrab enemy
     */
    @Override
    public Ocean createOcean() {
        return new GiantCrab();
    }

    /**
     * Creates a west side Skeletal type enemy.
     * @return a HeavySkeletalSwordsman enemy
     */
    @Override
    public Skeletal createSkeleton() {
        return new HeavySkeletalSwordsman();
    }
}
