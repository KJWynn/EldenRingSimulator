package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.items.CurrencyManager;

/**
 * GenerateRunesAction class is an action class to generate runes for all the actors.
 * @see game.actors.enemies.Enemy
 * @see game.items.Rune
 * @see game.items.CurrencyManager
 * @author Khor Jia Wynn,Saw Tze Ying,Wong An Hong
 */
public class GenerateRunesAction extends Action {
    // The amount of runes to be generated
    private final int AMOUNT;
    // The amount of runes the actor has
    private int runeAmount;

    /**
     * Constructor.
     * @param amount The amount of runes to be generated.
     * @param runeAmount The amount of runes the actor has.
     */
    public GenerateRunesAction(int amount, int runeAmount){
        this.AMOUNT = amount;
        this.runeAmount = runeAmount;
    }

    /**
     * Generates runes for the actor.
     * The hashmap is updated to reflect the new amount of runes the actor has.
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return String describing how much runes an actor gets.
     */
    @Override
    public String execute(Actor actor, GameMap map) {

        int originalAmount = CurrencyManager.getInstance().getPlayerCurrencyAmount().get('$').getAmount();
        CurrencyManager.getInstance().getPlayerCurrencyAmount().get('$').setAmount(originalAmount + AMOUNT);


        return actor + " gets " + AMOUNT + " runes generated";
    }

    /**
     * Returns the menu description of actor's has used a Golden Rune.
     * @param actor The actor performing the action.
     * @return a String that indicate actor has used a Golden Rune and the amount of available runes left.
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " uses a Golden Rune(" + runeAmount + " available) to generate runes.";
    }
}
