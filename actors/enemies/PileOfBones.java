package game.actors.enemies;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actions.ReviveAction;
import game.capabilities.GenerateRuneCapable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * PileOfBones class that represents a PileOfBones enemy.
 * PileOfBones is a subclass of Skeletal, and it implements DropRune.
 * If the Pile of Bones is not hit within the three turns, the Heavy Skeletal Swordsman or Skeletal Bandit
 * will be revived with full health.
 * @author Khor Jia Wynn,Saw Tze Ying,Wong An Hong
 */
public class PileOfBones extends Skeletal implements GenerateRuneCapable {
    /**
     * Predecessor is either HeavySkeletalSwordsman or SkeletalBandit
     */
    private Actor predecessor;
    /**
     * Turn counter to keep track of number of turns PileOfBones remains on map. For reviving predecessor
     */
    private int turnCounter = 0;

    /**
     * Constructor
     * @param predecessor The Actor that dropped this PileOfBones(HeavySkeletalSwordsman or SkeletalBandit)
     */
    public PileOfBones(Actor predecessor) {
        super("Pile of bones", 'X', 1);
        // PileOfBones can drop predecessor's weapon
        predecessor.getWeaponInventory().get(0).togglePortability();
        this.addWeaponToInventory(predecessor.getWeaponInventory().get(0));
        this.predecessor = predecessor;
        setDropRuneAmount(this);
    }


    /**
     * Overriding method since PileOfBones can only perform ReviveAction. Also registers itself as Resettable
     * @param actions    collection of possible Actions for Enemy, presumably Attacks only
     * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
     * @param map        the map containing the Actor
     * @param display    the I/O object to which messages may be written
     * @return action can be DoNothingAction or a ReviveAction
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        registerResettable(this);

        turnCounter += 1;
        if (turnCounter == 3){
            return new ReviveAction(map.locationOf(this), predecessor);
        }
        return new DoNothingAction();
    }

    /**
     * get the amount of rune the PileOfBones drops when defeated.
     * @return new array list the contains the amount of rune the PileOfBones drops when defeated.
     */
    @Override
    public List<Integer> getBounds() {
        return new ArrayList<>(Arrays.asList(35, 892));
    }


}
