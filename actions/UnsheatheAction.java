package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.utils.RandomNumberGenerator;

/**
 * A class that represents an action to unsheathe a weapon and attack a target with it.
 * @author Khor Jia Wynn,Saw Tze Ying,Wong An Hong
 */
public class UnsheatheAction extends Action {
    /** The weapon used in the attack. */
    private WeaponItem weapon;
    /** The target to be attacked. */
    private Actor target;
    /** The direction of the attack. */
    private String direction;

    /**
     * The hit rate of this special attack
     */
    private static final int HIT_RATE = 60;

    /**

     Constructor for the UnsheatheAction class.
     @param weapon The weapon to be unsheathed and used for the attack.
     @param target The target to be attacked.
     @param direction The direction in which the attack is performed.
     */

    public UnsheatheAction(WeaponItem weapon, Actor target, String direction){
        this.weapon = weapon;
        this.target = target;
        this.direction = direction;
    }
    /**
     Executes the UnsheatheAction by performing the UnsheatheAction attack and returning the result.
     @param actor The actor performing the UnsheatheAction.
     @param map The GameMap where the action is being performed.
     @return A String containing the result of UnsheatheAction attack action.
     */

    @Override
    public String execute(Actor actor, GameMap map) {
        String result = "";
        if (!RandomNumberGenerator.getSuccessOrFailure(HIT_RATE)) {
            result += actor + " misses " + target + ".";
        } else {
            int damage = 2 * weapon.damage();
            result += actor + " " + weapon.verb() + " " + target + " for " + damage + " damage.";
            target.hurt(damage);
            if (!target.isConscious()) {
                result += new DeathAction(actor).execute(target, map);
            }
        }
        return result;
    }

    /**
     * Describes which target the actor is attacking with which weapon
     *
     * @param actor The actor performing the action.
     * @return a description used for the menu UI
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " unsheathes " + weapon + " on " + target + " at " + direction;
    }
}
