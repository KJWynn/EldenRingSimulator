package game.roles;
import game.weapons.Uchigatana;
/**
 * A class that represents the Samurai role of the player
 * @author Khor Jia Wynn, Saw Tze Ying , Wong An Hong
 * @version 1.0
 * @see Role
 */
public class Samurai extends Role{

    /**
     * Constructor of Samurai. Samurai weapon is Uchigatana and starting Hit Point is 455.
     */
    public Samurai() {
        super("Samurai", new Uchigatana(),455);
    }
}
