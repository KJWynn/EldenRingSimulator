package game.positions;

import edu.monash.fit2099.engine.positions.Location;
import game.actors.enemies.GodrickSoldier;

/**
 * A class that represents the spawning ground of GodrickSoldier enemies
 * @see SpawningGround
 * @see game.actors.enemies.GodrickSoldier
 * @see Location
 * @author Khor Jia Wynn,Saw Tze Ying,Wong An Hong
 */

public class Barrack extends SpawningGround {

    /**
     * Constructor.
     */
    public Barrack() {
        super('B');
    }

    /**
     * A method that spawn canine type enemies in the gust of wind if there is no other Actor
     * on the location
     * @param location the location of this ground
     */
    @Override
    public void spawn(Location location) {
        if (!location.containsAnActor()){
            location.addActor(new GodrickSoldier());
        }
    }


}