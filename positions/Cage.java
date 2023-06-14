package game.positions;

import edu.monash.fit2099.engine.positions.Location;
import game.actors.enemies.Dog;
import game.actors.enemies.GodrickSoldier;

/**
 * A class that represents the spawning ground of Dogs enemies
 * @author Khor Jia Wynn,Saw Tze Ying,Wong An Hong
 */
public class Cage extends SpawningGround {

    /**
     * Constructor.
     */
    public Cage() {
        super('<');
    }

    /**
     * A method that spawn Dog type enemies in the gust of wind if there is no other Actor
     * on the location
     * @param location the location of this ground
     */
    public void spawn(Location location) {
        if (!location.containsAnActor()){
            location.addActor(new Dog());
        }
    }


}