package game.actors.enemies;
import game.capabilities.GenerateRuneCapable;
import game.utils.RandomNumberGenerator;
import game.weapons.GiantDogHead;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * GiantDog class.
 * Represents a GiantDog enemy.
 * GiantDog is a subclass of Canine, and implements DropRune.
 * @author Khor Jia Wynn,Saw Tze Ying,Wong An Hong
 */
public class GiantDog extends Canine implements GenerateRuneCapable {
    /**
     * Constructor.
     */
    public GiantDog() {
        super("GiantDog",'G',693);
        addWeaponToInventory(new GiantDogHead());
        setDropRuneAmount(this);
    }

    /**
     * get the amount of rune the GiantDog drops when defeated.
     * @return new array list the contains the amount of rune the GiantDog drops when defeated.
     */
    @Override
    public List<Integer> getBounds() {
        return new ArrayList<>(Arrays.asList(313, 1808));
    }
}
