package game.behaviours;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actions.AttackAction;
import game.capabilities.EnemyType;
import game.capabilities.Status;
import game.utils.RandomNumberGenerator;

/**
 * AttackBehaviour class implements Behaviour interface.
 * Represents the behaviour of an enemy to attack another actor.
 * @author Khor Jia Wynn,Saw Tze Ying,Wong An Hong
 */
public class AttackBehaviour implements Behaviour{

    /**
     * Chance of using AreaAttack
     */
    private static final int SPECIAL_SKILL_CHANCE = 50;

    /**
     * Constructor.
     */
    public AttackBehaviour(){
    }

    /**
     * Returns an AttackAction if the actor is able to attack the target.
     *
     * @param actor the actor that is attacking
     * @param map the map that the actor is currently on
     * @return an AttackAction if the actor is able to attack the target, null otherwise
     */
    @Override
    public Action getAction(Actor actor, GameMap map) {
        for (Exit exit:map.locationOf(actor).getExits()){
            if (exit.getDestination().containsAnActor()){
                Actor target = exit.getDestination().getActor();
                String direction = exit.getName();

                // 50% chance to area attack
                if (RandomNumberGenerator.getSuccessOrFailure(SPECIAL_SKILL_CHANCE)){
                    // Checks that the enemy carries WeaponItem, then checks that the first weapon item in the enemy's inventory can perform area attack
                    // (currently Grossmesser & Scimitar can do area attack)
                    if (actor.getWeaponInventory().size()!= 0){
                        return actor.getWeaponInventory().get(0).getSkill(target, direction);
                    }
                }

                // check that enemy types are different, or that attacker and target are hostile to each other
                if((actor.hasCapability(Status.HOSTILE_TO_PLAYER) && target.hasCapability(Status.HOSTILE_TO_ENEMY)) ||
                        (actor.hasCapability(Status.HOSTILE_TO_ENEMY) && target.hasCapability(Status.HOSTILE_TO_PLAYER)) ||
                        (!target.findCapabilitiesByType(EnemyType.class).equals(actor.findCapabilitiesByType(EnemyType.class)))){
                    if (actor.getWeaponInventory().size()!= 0){
                        return new AttackAction(target, direction, actor.getWeaponInventory().get(0));
                    }
                    // using intrinsic weapon
                    else {
                        return new AttackAction(target, direction);
                    }
                }
            }
        }
        return null;
    }
}
