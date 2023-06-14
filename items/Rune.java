package game.items;

import edu.monash.fit2099.engine.positions.Location;
import game.capabilities.CurrencyType;
import game.reset.ResetManager;
import game.reset.Resettable;

/**
 * A class that represents rune currency.
 * @see Currency
 * @see game.capabilities.CurrencyType
 * @see game.reset.Resettable
 * @see game.reset.ResetManager
 * @see game.items.CurrencyManager
 * @author Khor Jia Wynn,Saw Tze Ying,Wong An Hong
 */
public class Rune extends Currency implements Resettable {
    /**
     * Constructor.
     */
    public Rune() {
        super("Rune", '$',0);
        addCapability(CurrencyType.RUNE);
    }

    /**
     * A method that register rune as an item that needs to be reset in every tick.
     * @param currentLocation The location of the ground on which we lie.
     */
    @Override
    public void tick(Location currentLocation) {
        super.tick(currentLocation);
        registerResettable(this);
    }

    /**
     * A method that reset the rune if player dies.
     * @return String that describes the rune has been reset.
     */
    @Override
    public String reset() {
        String result = "";
        // remove existing rune on map if player dies
        if (ResetManager.isPlayer_death() && CurrencyManager.getDroppedRuneLocation()!=null) {
            CurrencyManager.getDroppedRuneLocation().removeItem(this);
            result += this.getAmount() + " runes has been lost. ";
        }
        return result;
    }

}
