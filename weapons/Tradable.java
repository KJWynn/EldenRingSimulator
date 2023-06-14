package game.weapons;

import edu.monash.fit2099.engine.weapons.WeaponItem;
/**
 * Tradable interface represents an item that a Trader can give to the Player in exchange for a Currency
 * @author Khor Jia Wynn,Saw Tze Ying,Wong An Hong
 * */
public interface Tradable {

    /**
     * function to return the weapon item instance of the weapon item that implement this interface
     * @return the weapon item instance
     * */
    WeaponItem getWeaponItem();

    /**
     * function to return the  instance of the weapon item that implement this interface as Tradable type
     * @return the weapon item instance as Tradable
     * */
    Tradable getTradableType();
}
