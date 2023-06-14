package game.actors.npc;

import game.weapons.AxeOfGodrick;
import game.weapons.GraftedDragon;

/**
 * A class that represents a Finger Reader Enia>
 * A new trader which can accept the Remembrance of the Grafted from the player
 * to be exchanged for either the Axe of Godrick or Grafted Dragon.
 * @see game.actors.npc.Trader
 * @see game.weapons.AxeOfGodrick
 * @see game.weapons.GraftedDragon
 * @author Khor Jia Wynn,Saw Tze Ying,Wong An Hong
 */
public class FingerReaderEnia extends Trader{

    /**
     * Constructor.
     */
    public FingerReaderEnia() {
        super("Finger Reader Enia", 'E', 100000);
    }

    /**
     * Set the inventory of the trader.
     * The trader will have the Grafted Dragon and Axe of Godrick in its inventory.
     */
    @Override
    void setInventory() {
        this.getTradables().add(new GraftedDragon().getTradableType());
        this.getTradables().add(new AxeOfGodrick().getTradableType());
    }
}
