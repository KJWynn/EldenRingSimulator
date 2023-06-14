package game.roles;
import game.weapons.Club;

/**
 * A class that represents the Wretch role of the player
 * @author Khor Jia Wynn, Saw Tze Ying , Wong An Hong
 * @version 1.0
 * @see Role
 */
public class Wretch extends Role{
    /**
     * Constructor of Wretch. Wretch weapon is Club and starting Hit Point is 414.
     */
    public Wretch() {
        super("Wretch", new Club(),414);
    }
}
