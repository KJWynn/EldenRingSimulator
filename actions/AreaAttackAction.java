package game.actions;
import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.Weapon;
import game.utils.RandomNumberGenerator;
import java.util.LinkedHashMap;

/**
 * An Action to attack another Actor.
 * Created by:
 * @author Adrian Kristanto
 * Modified by:Khor Jia Wynn,Saw Tze Ying,Wong An Hong
 *
 */

public class AreaAttackAction extends Action {
    /**
     * The weapon used to attack enemies or player
     */
    private Weapon weapon;
    /**
     * A mapping of exits 8 directions to enemies or player
     */
    private LinkedHashMap<Exit,Actor> exitToActorMap = new LinkedHashMap<>();

    /**
     * Constructor for the AreaAttackAction class.
     *
     * @param weapon the weapon used to attack enemies
     */
    public AreaAttackAction(Weapon weapon) {
        this.weapon = weapon;
    }

    /**
     * Executes the area attack action, damaging all adjacent enemies or player within the vicinity.
     *
     * @param attacker the actor performing the attack
     * @param map the game map on which the attack is being performed
     * @return a string describing the result of the attack
     */
    @Override
    public String execute(Actor attacker, GameMap map) {
        //Gets the mapping of exits to enemies within the vicinity
        for (Exit exit: map.locationOf(attacker).getExits()){
            if (exit.getDestination().containsAnActor()){
                exitToActorMap.put(exit, exit.getDestination().getActor());
            }
        }

        //Executes the attack on all enemies within the vicinity
        String result = menuDescription(attacker);
        int damage = weapon.damage();
        for (Exit exit: exitToActorMap.keySet()){
            Actor target = exitToActorMap.get(exit);
            if (RandomNumberGenerator.getSuccessOrFailure(weapon.chanceToHit())){
                result += attacker + " " + weapon.verb() + " " + target + " at " + exit.getName() + " for " + damage + " damage. ";
                target.hurt(damage);
                if (!target.isConscious()) {
                    result += new DeathAction(attacker).execute(target, map);
                }
            } else {result += attacker + " misses " + target + ".\n";}
        }


        return result;
    }

    /**
     * Returns text describing the attack action.
     *
     * @param actor the actor performing the action
     * @return a string describing the action for display in the menu
     */
    @Override
    public String menuDescription (Actor actor){
        return actor + " attacks any thing in the surrounding with " + weapon + ". ";
    }

}


