package game.actors.enemies;

import edu.monash.fit2099.engine.actors.Actor;
import game.capabilities.EnemyType;
import game.capabilities.GenerateRuneCapable;
import game.capabilities.Status;
import game.reset.ResetManager;
import game.roles.Role;
import game.utils.RandomNumberGenerator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * A class that represents an Invader enemy.
 * @see game.actors.enemies.Enemy
 * @see game.capabilities.GenerateRuneCapable
 * @see game.roles.Role
 * @see game.utils.RandomNumberGenerator
 * @see game.capabilities.EnemyType
 * @author Khor Jia Wynn,Saw Tze Ying,Wong An Hong
 */
public class Invader extends Enemy implements GenerateRuneCapable {
    /**
     * Constructor.
     * @param role The role of the Invader.
     */
    public Invader(Role role) {
        super("Invader", 'ඞ', role.getHIT_POINT());
        this.maxHitPoints = role.getHIT_POINT();
        setEnemyType();
        setDespawnChance(0);
        setDropRuneAmount(this);
        this.addWeaponToInventory(role.getWeaponItem());
    }

    /**
     * set enemy type of Invader.
     */
    @Override
    public void setEnemyType() {
        addCapability(EnemyType.INVADER);
    }

    /**
     * get the amount of rune the Invader drops when defeated.
     * @return new array list the contains the amount of rune the Invader drops when defeated.
     */
    @Override
    public List<Integer> getBounds() {
        return new ArrayList<>(Arrays.asList(1358, 5578));
    }

    /**
     * get the amount of rune the Invader drops when defeated.
     * @return String that describes the Invader has been reset.
     */
    public String reset(){
        if(ResetManager.isPlayer_death()){
            this.addCapability(Status.WIPED);
            return this + " is reset";
        }
        return "";
    }
}
