package game.actors.enemies;
import game.capabilities.GenerateRuneCapable;
import game.utils.RandomNumberGenerator;
import game.weapons.GiantCrayFishPincer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * GiantCrayFish class.
 * Represents a GiantCrayFish enemy.
 * GiantCrayFish is a subclass of Ocean, and implements DropRune.
 * @see game.capabilities.GenerateRuneCapable
 * @see game.actors.enemies.Ocean
 * @author Khor Jia Wynn,Saw Tze Ying,Wong An Hong
 */

public class GiantCrayFish extends Ocean implements GenerateRuneCapable {

    /**
     * Constructor.
     */
    public GiantCrayFish() {
        super("GiantCrayFish", 'R', 4803);
        addWeaponToInventory(new GiantCrayFishPincer());
        setDropRuneAmount(this);
    }

    /**
     * get the amount of rune the GiantCrayFish drops when defeated.
     * @return new array list the contains the amount of rune the GiantCrayFish drops when defeated.
     */
    @Override
    public List<Integer> getBounds() {
        return new ArrayList<>(Arrays.asList(500, 2374));
    }
}
