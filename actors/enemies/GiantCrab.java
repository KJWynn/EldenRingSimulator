package game.actors.enemies;

import game.capabilities.GenerateRuneCapable;
import game.weapons.GiantCrabPincer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * GiantCrab class.
 * Represents a GiantCrab enemy.
 * GiantCrab is a subclass of Ocean, and implements DropRune.
 * @see game.actors.enemies.Ocean
 * @see game.capabilities.GenerateRuneCapable
 * @author Khor Jia Wynn,Saw Tze Ying,Wong An Hong
 */

public class GiantCrab extends Ocean implements GenerateRuneCapable {
    /**
     * Constructor.
     */
    public GiantCrab() {

        super("Giant crab", 'C',407);
        addWeaponToInventory(new GiantCrabPincer());
        setDropRuneAmount(this);
    }

    /**
     * get the amount of rune the GiantCrab drops when defeated.
     * @return new array list the contains the amount of rune the GiantCrab drops when defeated.
     */
    @Override
    public List<Integer> getBounds() {
        return new ArrayList<>(Arrays.asList(318, 4961));
    }
}
