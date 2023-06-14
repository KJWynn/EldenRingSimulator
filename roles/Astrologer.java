package game.roles;
import game.weapons.GreatKnife;

/**
 * A class that represents the Astrologer role of the player
 * @author Khor Jia Wynn, Saw Tze Ying , Wong An Hong
 * @version 1.0
 */
public class Astrologer extends Role{
    /**
     * Constructor of Astrologer. Astrologer weapon is GreatKnife and starting Hit Point is 396.
     */
    public Astrologer() {
        super("Astrologer",new GreatKnife(),396);
    }
}