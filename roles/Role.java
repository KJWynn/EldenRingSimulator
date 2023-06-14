package game.roles;

import edu.monash.fit2099.engine.weapons.WeaponItem;
/**
 * A class that represents the Role of the player
 * @author Khor Jia Wynn, Saw Tze Ying , Wong An Hong
 * @version 1.0
 * @see game.actors.Player
 * @see Bandit
 * @see Samurai
 * @see Wretch
 */
public abstract class Role {


    /**
     * Name of the role
     */
    private String name;

    /**
     * The starting weapon of the role
     */
    private WeaponItem weaponItem;

    /**
     * The starting Hit Point of the role
     */
    private final int HIT_POINT;

    /**
     * Constructor.
     * @param weaponItem which represent the weapon that corresponding to the role
     * @param hitPoint the hit point of the roles
     */
    public Role(String name, WeaponItem weaponItem, int hitPoint) {
        this.name = name;
        this.weaponItem = weaponItem;
        HIT_POINT = hitPoint;
    }

    /**
     * A method that returns the weapon item of the role
     * @return weaponItem
     */
    public WeaponItem getWeaponItem() {
        return weaponItem;
    }

    /**
     * A method that returns the hit point of the role
     * @return hit point of the role
     */
    public int getHIT_POINT() {
        return HIT_POINT;
    }

    /**
     * A method that returns the name of the role
     * @return name of the role
     */
    @Override
    public String toString() {
        return name;
    }
}
