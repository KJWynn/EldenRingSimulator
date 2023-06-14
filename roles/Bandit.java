package game.roles;

import game.weapons.GreatKnife;
/**
 * A class that represents the Bandit role of the player
 * @author Khor Jia Wynn, Saw Tze Ying , Wong An Hong
 * @version 1.0
 */
public class Bandit extends Role{

    /**
     * Constructor of Bandit. Bandit weapon is GreatKnife and starting Hit Point is 414.
     */
    public Bandit() {
        super("Bandit", new GreatKnife(),414);
    }
}
