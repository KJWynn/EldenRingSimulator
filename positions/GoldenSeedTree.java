package game.positions;

import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.Location;
import game.items.GoldenSeed;
import game.utils.RandomNumberGenerator;

import java.util.ArrayList;
import java.util.List;

/**
 * A class that represents the GoldenSeedTree spawning ground.
 * It can spawn GoldenSeed items on its surrounding
 * @see SpawningGround
 * @author Khor Jia Wynn,Saw Tze Ying,Wong An Hong
 */
public class GoldenSeedTree extends SpawningGround{

    /**
     * The spawn chance of this tree, can be modified here if needed
     */
    public static final int SPAWN_CHANCE = 100;

    /**
     * Constructor.
     */
    public GoldenSeedTree() {
        super('V');
    }

    /**
     * Implements spawn method from parent SpawningGround, it will spawn a GoldenSeed on a random exit
     * @param location The location of this ground
     */
    @Override
    public void spawn(Location location) {
        if (RandomNumberGenerator.getSuccessOrFailure(SPAWN_CHANCE)) {
            List<Exit> exits = new ArrayList<>(location.getExits());
            int randomExit = RandomNumberGenerator.getRandomInt(exits.size());
            location.getExits().get(randomExit).getDestination().addItem(new GoldenSeed());
        }


    }
}
