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
 * @see game.roles.Role
 * @author Khor Jia Wynn,Saw Tze Ying,Wong An Hong
 */
public class Ally extends NonPlayableActor {
    /**
     * Constructor.
     * @param role
     */
    public Ally(Role role) {
        super("Ally", 'A', role);
        this.addWeaponToInventory(role.getWeaponItem());
    }

}
