package game.actors.enemies;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.capabilities.GenerateRuneCapable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * A class that represents a Dog enemy.
 * @see game.actors.enemies.StormveilCastleEnemy
 * @see game.capabilities.GenerateRuneCapable
 * @author Khor Jia Wynn,Saw Tze Ying,Wong An Hong
 */
public class Dog extends StormveilCastleEnemy implements GenerateRuneCapable {
    /**
     * Constructor.
     */
    public Dog() {
        super("Dog", 'a',104);
        setDropRuneAmount(this);
    }

    /**
     * getIntrinsicWeapon of Dog which is Bite
     */
    public IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(101, "bites",93);
    }

    /**
     * get the amount of rune the Dog drops when defeated.
     */
    @Override
    public List<Integer> getBounds() {
        return new ArrayList<>(Arrays.asList(52, 1390));
    }
}
