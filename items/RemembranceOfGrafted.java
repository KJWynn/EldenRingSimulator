package game.items;

import game.capabilities.CurrencyType;

/**
 * Class representing the currency Remembrance of the Grafted.
 * @author Khor Jia Wynn,Saw Tze Ying,Wong An Hong
 */
public class RemembranceOfGrafted extends Currency{
    /**
     * Constructor.
     */
    public RemembranceOfGrafted() {
        super("Remembrance of the Grafted", 'O', 1);
        addCapability(CurrencyType.REMEMBRANCE_OF_GRAFTED);
    }

}
