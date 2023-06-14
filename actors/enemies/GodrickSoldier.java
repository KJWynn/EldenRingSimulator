package game.actors.enemies;
import game.capabilities.GenerateRuneCapable;
import game.weapons.Club;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * A class that represents a Godrick Soldier enemy.
 * @see game.actors.enemies.StormveilCastleEnemy
 * @see game.capabilities.GenerateRuneCapable
 * @see game.weapons.Club
 * @author Khor Jia Wynn,Saw Tze Ying,Wong An Hong
 */
public class GodrickSoldier extends StormveilCastleEnemy implements GenerateRuneCapable {
    /**
     * Constructor.
     */
    public GodrickSoldier() {
        super("GodrickSoldier",'p',198);
        addItemToInventory(new Club());
        setDropRuneAmount(this);
    }

    /**
     * get the range of the amount of rune the Godrick Soldier drops when defeated.
     * @return new array list the contains the range of the amount of rune the Godrick Soldier drops when defeated.
     */
    @Override
    public List<Integer> getBounds() {
        return new ArrayList<>(Arrays.asList(38, 70));
    }
}
