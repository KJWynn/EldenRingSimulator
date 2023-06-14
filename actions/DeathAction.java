package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.actors.enemies.PileOfBonesManager;
import game.capabilities.Status;
import game.items.CurrencyManager;
import game.displays.FancyMessage;
import game.reset.ResetManager;


/**
 * An action executed if an actor is killed.
 * Created by:
 * @author Adrian Kristanto
 * Modified by: Wong An Hong, Saw Tze Ying, Khor Jia Wynn
 *
 */
public class DeathAction extends Action {
    /**
     * The attacker
     */
    private Actor attacker;

    /**
     * Constructor for DeathAction class.
     * @param actor The actor that caused the death.
     */
    public DeathAction(Actor actor) {
        this.attacker = actor;
    }

    /**
     * If target is Player, execute reset.
     * Else if it is enemy, if target can drop inventory, the items & weapons carried by target
     * will be dropped to the location in the game map where the target was
     * If target was killed by player, update Player rune amount
     * If target can revive, let PileOfBonesManager handle logic.
     *
     * @param target The actor performing the action.
     * @param map The map the actor is on where the death takes place.
     * @return result of the action to be displayed on the UI
     */
    @Override
    public String execute(Actor target, GameMap map) {
        String result = "";

        // if it is the player who dies
        if (target.hasCapability(Status.PLAYER)){
            ResetManager.setPlayer_death(true);
            if (target.hasCapability(Status.FALL)){
                result += target + " fell off the cliff. ";
            }
            result += "\n" + FancyMessage.YOU_DIED;
            result += ResetManager.getInstance().run();
            return result;
        }


        ActionList dropActions = new ActionList();
        // drop all items if possible(invader/ally/some enemies drop them)
        if (!target.hasCapability(Status.CANNOT_DROP_INVENTORY) && !target.hasCapability(Status.PLAYER)) {
            for (Item item : target.getItemInventory())
                dropActions.add(item.getDropAction(target));
            for (WeaponItem weapon : target.getWeaponInventory()){
                dropActions.add(weapon.getDropAction(target));}
            for (Action drop : dropActions)
                drop.execute(target, map);
        }

        // Manage runes if enemy killed by player
        if (attacker.hasCapability(Status.PLAYER) && !target.hasCapability(Status.CANNOT_DROP_INVENTORY)) {
            result += CurrencyManager.getInstance().enemyDies(target);
        } else {
            // display message that actor is killed
            result += System.lineSeparator() + menuDescription(target);
        }

        // for skeletal enemies that can revive, let PileOfBonesManager handle(including removing actor from map)
        if (target.hasCapability(Status.CAN_REVIVE)) {
            PileOfBonesManager pileOfBonesManager = new PileOfBonesManager(target);
            result += pileOfBonesManager.addPileOfBones(map) + "\n"; // removes actor then adds pile of bones
        } else {
            // remove actor from map
            map.removeActor(target);
        }

        return result;
    }

    /**
     * Returns a description of this DeathAction that can be displayed in the game menu.
     *
     * @param actor The actor that got killed.
     * @return The actor either player or enemy that got killed.
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " is killed.";
    }
}
