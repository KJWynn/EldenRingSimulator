package game.items;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Location;
import java.util.HashMap;

/**
 * A class that represents a currency manager.
 * @author Khor Jia Wynn,Saw Tze Ying,Wong An Hong
 */
public class CurrencyManager {
    /**
     * The singleton instance of the CurrencyManager.
     */
    private static CurrencyManager currencyManager;
    /**
     * The location where a rune was dropped.
     */
    private static Location droppedRuneLocation;

    /**
     * A mapping of actors to the amount of runes they drop(enemy)
     */
    private final HashMap<Actor, Integer> actorRuneAmountHashMap = new HashMap<>();

    /**
     * Stores Player's currencies. Maps a Currency's display character to the Currency
     */
    private final HashMap<Character, Currency> playerCurrencyAmount = new HashMap<>();


    /**
     * Constructor.
     * It is private because it is a singleton.
     */
    private CurrencyManager(){
    }

    /**
     * Returns the singleton instance of the CurrencyManager.
     *
     * @return The singleton instance of the CurrencyManager.
     */
    public static CurrencyManager getInstance(){
        if (currencyManager == null){
            currencyManager = new CurrencyManager();
        } return currencyManager;
    }

    /**
     * Handles the case where an enemy dies and drops runes that are collected by the player.
     * @param target The enemy that died.
     * @return A string describing the amount of runes dropped by the death enemy and collected by player.
     */

    public String enemyDies(Actor target){
        int enemyAmount = actorRuneAmountHashMap.get(target);

        int originalAmount = playerCurrencyAmount.get('$').getAmount();
        playerCurrencyAmount.get('$').setAmount(originalAmount+enemyAmount);

        return target + " drops " + enemyAmount + " runes, collected by player. " ;
    }



    /**
     * Returns the mapping of actors to the amount of runes they drop
     *
     * @return The mapping of actors to the amount of runes they drop.
     */
    public HashMap<Actor, Integer> getActorRuneAmountHashMap() {
        return actorRuneAmountHashMap;
    }


    /**
     * Returns the Player's currencies
     * @return The hash map which maps the Currency display character to the Currency
     */
    public HashMap<Character, Currency> getPlayerCurrencyAmount() {
        return playerCurrencyAmount;
    }


    /**
     * The getter to get the dropped rune location
     */
    public static Location getDroppedRuneLocation() {
        return droppedRuneLocation;
    }

    /**
     * The setter to set the dropped rune location
     */
    public static void setDroppedRuneLocation(Location droppedRuneLocation) {
        CurrencyManager.droppedRuneLocation = droppedRuneLocation;
    }
}
