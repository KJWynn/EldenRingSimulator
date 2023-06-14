package game.actors.npc;
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
import game.behaviours.WanderBehaviour;
import game.capabilities.Status;
import game.reset.ResetManager;
import game.reset.Resettable;
import game.roles.Role;
import java.util.TreeMap;

/**
 * A class that represents an Ally.
 * @see game.actors.npc
 * @see game.reset.Resettable
 * @see game.roles.Role
 * @see game.actions.AttackAction
 * @see game.actions.DespawnAction
 * @see game.capabilities.Status
 * @see game.reset.ResetManager
 * @see game.behaviours.Behaviour
 * @author Khor Jia Wynn,Saw Tze Ying,Wong An Hong
 */
public class NonPlayableActor extends Actor implements Resettable {
    // hashmap that store the sequence of behaviour an Ally can perform
    private TreeMap<Integer, Behaviour> behaviours = new TreeMap<>();

    /**
     * Constructor.
     * @param role
     */
    public NonPlayableActor(String name,Character displayChar,Role role) {
        super(name, displayChar, role.getHIT_POINT());
        this.maxHitPoints = role.getHIT_POINT();
        addCapability(Status.HOSTILE_TO_ENEMY);
    }

    /**
     * List of actions other Actors can do to the Player.
     * @param otherActor the Actor that might be performing attack
     * @param direction  String representing the direction of the other Actor
     * @param map        current GameMap
     * @return ActionList of allowable actions
     */
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList list = super.allowableActions(otherActor, direction, map);
        //enemy/invader can attack ally
        if(!otherActor.hasCapability(Status.HOSTILE_TO_ENEMY)){
            if(otherActor.getWeaponInventory().size() != 0){
                for (WeaponItem weaponItem : otherActor.getWeaponInventory()) {
                    //normal weapon attack
                    list.add(new AttackAction(this, direction, weaponItem));
                    //special attack eg.area attack
                    if(weaponItem.getSkill(this,direction) != null){
                        list.add(weaponItem.getSkill(this,direction));
                    }
                }
            }
            list.add(new AttackAction(this, direction));
        }
        return list;
    }

    /**
     * Returns the Action to be performed on the current turn.
     * @param actions    collection of possible Actions for this Actor
     * @param lastAction The Action this Actor took last turn.
     * @param map        the map containing the Actor
     * @param display    the I/O object to which messages may be written
     * @return Action to be performed on the current turn
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        //Ally will only be removed when the player dies
        registerResettable(this);
        if(this.hasCapability(Status.WIPED)){
            return new DespawnAction(false);
        }

        behaviours.remove(1);
        behaviours.put(1, new AttackBehaviour());
        //add wander behaviour since when there is no player to attack,they will wander around
        behaviours.put(2,new WanderBehaviour());
        // loop through behaviours list to get the first action that is not null
        for (Integer key: behaviours.keySet()){
            Behaviour behaviour = behaviours.get(key);
            Action action = behaviour.getAction(this,map);
            if (action != null){
                return action;
            }
        }
        return new DoNothingAction();
    }

    /**
     * After the player died,Ally will be wiped out of the map.
     * @return String that shows the Ally is reset
     */
    @Override
    public String reset() {
        if(ResetManager.isPlayer_death()){
            this.addCapability(Status.WIPED);
            return this + " is reset. ";
        }
        return "";
    }

}
