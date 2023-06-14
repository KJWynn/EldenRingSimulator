package game.positions;

import game.actors.enemies.Canine;
import game.actors.enemies.Ocean;
import game.actors.enemies.Skeletal;

/**
 * An interface that will be implemented by the EastEnemyFactory and WestEnemyFactory.
 * This interface will be used to create enemies in the east and west side of the map.
 * Either east or west side of the map will need to have each of the types of enemies.
 * @author Khor Jia Wynn,Saw Tze Ying,Wong An Hong
 */
public interface EnemyFactory {

    /**
     * Creates a Canine enemy depending on direction
     * @return A Canine enemy
     */
    Canine createCanine();

    /**
     * Creates an Ocean enemy depending on direction
     * @return An Ocean enemy
     */
    Ocean createOcean();

    /**
     * Creates a Skeletal enemy depending on direction
     * @return A Skeletal enemy
     */
    Skeletal createSkeleton();

}
