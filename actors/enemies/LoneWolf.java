package game.actors.enemies;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.capabilities.GenerateRuneCapable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * BEHOLD, DOG!
 * Created by:
 * @author Adrian Kristanto
 * Modified by:
 * Khor Jia Wynn,Saw Tze Ying and Wong An Hong
 * LoneWolf class that represents a LoneWolf enemy.
 * LoneWolf is a subclass of Canine, and it implements DropRune.
 * @author Khor Jia Wynn,Saw Tze Ying,Wong An Hong
 */

public class LoneWolf extends Canine implements GenerateRuneCapable {

    /**
     * Constructor for the LoneWolf class.
     */
    public LoneWolf() {

        super("Lone Wolf", 'h', 102);
        setDropRuneAmount(this);
    }


    /**
     * getIntrinsicWeapon of Lone Wolf which is Fang
     */
    public IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(97, "bites", 95);
    }

    /**
     * get the amount of rune the LoneWolf drops when defeated.
     * @return new array list the contains the amount of rune the LoneWolf drops when defeated.
     */
    @Override
    public List<Integer> getBounds() {
        return new ArrayList<>(Arrays.asList(55, 1470));
    }
}
