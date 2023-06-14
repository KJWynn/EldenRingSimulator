package game.positions;

import edu.monash.fit2099.engine.positions.Location;

/**
 * A Graveyard class that represents the spawning ground of skeletal type enemies
 * @author Khor Jia Wynn,Saw Tze Ying,Wong An Hong
 */
public class Graveyard extends LimgraveSpawningGround{
    /**
     * Constructor.
     */
    public Graveyard() {
        super('n');
    }
    /**
     * A method that spawn skeletal type enemies in the graveyard if there is no no other Actor
     * on the location
     * @param location the location of this ground
     */
    public void spawn(Location location) {
        EnemyFactory enemyFactory = checkEastWest(location);
        if (enemyFactory!=null){
            location.map().addActor(enemyFactory.createSkeleton(),location);
        }
    }

    /**
     * Override getEastSpawnChance to 27 since HeavySkeletalSwordsman have 27 percent chance to be spawn in Graveyard east side.
     * @return spawn chance of east enemy.
     */
    @Override
    public int getEastSpawnChance() {
        return 27;
    }

    /**
     * Override getWestSpawnChance to 27 since HeavySkeletalSwordsman have 27 percent chance to be spawn in Graveyard west side.
     * @return spawn chance of west enemy.
     */
    @Override
    public int getWestSpawnChance() {
        return 27;
    }

}
