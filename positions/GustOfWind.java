package game.positions;

import edu.monash.fit2099.engine.positions.Location;

/**
 * A class that represents the spawning ground of canine type enemies
 * @author Khor Jia Wynn,Saw Tze Ying,Wong An Hong
 */
public class GustOfWind extends LimgraveSpawningGround {

    /**
     * Constructor.
     */
    public GustOfWind() {
        super('&');
    }

    /**
     * A method that spawn canine type enemies in the gust of wind if there is no other Actor
     * on the location
     * @param location the location of this ground
     */
    @Override
    public void spawn(Location location) {
        EnemyFactory enemyFactory = checkEastWest(location);
        if (enemyFactory!=null){
            location.map().addActor(enemyFactory.createCanine(),location);
        }
    }

    /**
     * Override getEastSpawnChance to 4 since GiantDog have 2 percent chance to be spawn in GustOfWind east side.
     * @return spawn chance of east enemy.
     */
    @Override
    public int getEastSpawnChance() {
        return 4;
    }

    /**
     * Override getWestSpawnChance to 33 since LoneWolf have 33 percent chance to be spawn in GustOfWind west side.
     * @return spawn chance of west enemy.
     */
    @Override
    public int getWestSpawnChance() {
        return 33;
    }


}