package game.positions;

import edu.monash.fit2099.engine.positions.Location;

/**
 * A class that represents the spawning ground of puddle of water type enemies
 * @author Khor Jia Wynn,Saw Tze Ying,Wong An Hong
 */
public class PuddleOfWater extends LimgraveSpawningGround{

    /**
     * Constructor.
     */
    public PuddleOfWater() {
        super('~');

    }

    /**
     * A method that spawn puddle of water type enemies in the puddle of water if there
     * is no other Actor on the Location
     * @param location the location of this ground
     */
    public void spawn(Location location) {
        EnemyFactory enemyFactory = checkEastWest(location);
        if (enemyFactory!=null){
            location.map().addActor(enemyFactory.createOcean(),location);
        }
    }

    /**
    * Override getEastSpawnChance to 1 since GiantCrayFish have 1 percent chance to be spawn in PuddleOfWater east side.
    * @return spawn chance of east enemy.
    */
    @Override
    public int getEastSpawnChance() {
        return 1;
    }

    /**
     * Override getWestSpawnChance to 2 since GiantCrab have 2 percent chance to be spawn in PuddleOfWater west side.
     * @return spawn chance of west enemy.
     */
    @Override
    public int getWestSpawnChance() {
        return 2;
    }

}
