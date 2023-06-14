package game.actors.enemies;
import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.actions.AttackAction;
import game.actions.DespawnAction;
import game.behaviours.AttackBehaviour;
import game.behaviours.Behaviour;
import game.behaviours.FollowBehaviour;
import game.behaviours.WanderBehaviour;
import game.capabilities.EnemyType;
import game.capabilities.Status;
import game.reset.Resettable;
import game.utils.RandomNumberGenerator;

import java.util.TreeMap;


/**
 * Abstract class representing an enemy in the game. Defines the behaviour of all enemies in the game
 * Exception: PileOfBones which overrides PlayTurn()
 * It extends Actor class and implements Resettable interface.
 * @see Actor
 * @see Resettable
 * @author Khor Jia Wynn,Saw Tze Ying,Wong An Hong
 */
public abstract class Enemy extends Actor implements Resettable{
    /**
     * Enemy default despawn chance
     */
    private static final int DEFAULT_DESPAWN_CHANCE = 10;

    /**
     * Chance to randomly despawn
     */
    private int despawnChance;

    /**
     * a hashmap to map the priority of behaviours to the behaviours
     */
    private TreeMap<Integer, Behaviour> behaviours = new TreeMap<>();

    /**
     * Constructor for enemy
     *
     * @param name        the name of the Actor
     * @param displayChar the character that will represent the Actor in the display
     * @param hitPoints   the Actor's starting hit points
     */
    public Enemy(String name, char displayChar, int hitPoints) {
        super(name, displayChar, hitPoints);
        setEnemyType();
        addCapability(Status.HOSTILE_TO_PLAYER);
        setDespawnChance(DEFAULT_DESPAWN_CHANCE);
    }

    /**
     * Setter for despawn chance if enemy has a different despawn chance
     * @param despawnChance
     */
    public void setDespawnChance(int despawnChance){
        this.despawnChance = despawnChance;
    }

    /**
     * Requires subclasses to add EnemyType to capability set
     */
    public abstract void setEnemyType();

    /**
     * allowableActions method that check all the action that all the other actors can do to the current enemy
     * this method overrides the allowableActions method in Actor class in the engine package.
     * Only do this for Player. If attacker is another Enemy, it is handled in playTurn(). See playTurn()
     * @param otherActor the Actor that can do some actions to the current enemy.
     * @param direction the direction of the otherActor can do to the current enemy.
     * @param map the map that player and enemy is in this turn.
     * @return a new collection of the Actions that the otherActor can do to the current enemy.
     */

    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList list = super.allowableActions(otherActor, direction, map);
        // player attacks this enemy
        if (otherActor.hasCapability(Status.HOSTILE_TO_ENEMY)) {
            // attacks player can perform
            if (otherActor.getWeaponInventory().size()!= 0) {
                for (WeaponItem weaponItem: otherActor.getWeaponInventory()) {
                    // single attack
                    list.add(new AttackAction(this, direction, weaponItem));
                    // special attack (including area attack)
                    if (weaponItem.getSkill(this, direction)!=null){
                        list.add(weaponItem.getSkill(this, direction));
                    }
                }
            }
            // attack using intrinsic weapon
            list.add(new AttackAction(this, direction));
        }
        return list;
    }



    /**
     * Methods that executes the enemy's action in every turn.
     * Priority: Attack actions more important than behaviours
     * 1. attack if player or other enemy is next to each other
     * 2. follow if player next to enemy
     * 3. wander if enemy has place to wander
     * 4. do nothing if enemy has no place to wander because when they spawn,they might be crowded together
     * @param actions    collection of possible Actions for Enemy, presumably Attacks only
     * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
     * @param map        the map containing the Actor
     * @param display    the I/O object to which messages may be written
     * @return action to be performed by the enemy
     */

    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display){

        // register itself in resetManager so that it is involved in next reset
        registerResettable(this);

        // chance to randomly despawn if not following Player
        if ((RandomNumberGenerator.getSuccessOrFailure(despawnChance)&& !this.hasCapability(Status.FOLLOWING))){
            return new DespawnAction(true);
        }

        if (this.hasCapability(Status.WIPED)){
            return new DespawnAction(false);
        }


        // reset attack behaviours
        behaviours.remove(1);

        behaviours.put(1, new AttackBehaviour());

        // add FollowBehaviour if player is next to enemy
        for (Exit exit : map.locationOf(this).getExits()) {
            if (exit.getDestination().containsAnActor() && exit.getDestination().getActor().hasCapability(Status.HOSTILE_TO_ENEMY)) {
                this.addCapability(Status.FOLLOWING);
                behaviours.put(3, new FollowBehaviour(exit.getDestination().getActor()));
            }
        }


        // add WanderBehaviour into the TreeMap last option
        behaviours.put(4, new WanderBehaviour());
        // iterate through available behaviours to get an action, according to priority(attack > follow > wander)
        for (Integer key : behaviours.keySet()) {
            Behaviour behaviour = behaviours.get(key);
            Action action = behaviour.getAction(this, map);
            if (action != null){
                return action;
            }

        }
        return new DoNothingAction();

    }

    /**
     * Method to remove the enemy from the map when reset occurs. Can be overridden if enemy does not despawn during reset.
     * @return message that describes which enemy has been despawned
     */
    @Override
    public String reset() {
        this.addCapability(Status.WIPED);
        return this + " is reset. ";
    }
}
