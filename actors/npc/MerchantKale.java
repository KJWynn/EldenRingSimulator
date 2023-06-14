package game.actors.npc;

import game.weapons.*;

/**
 * A class that represents a Merchant Kale.
 * @see game.actors.npc.Trader
 * @see game.weapons.Club
 * @see game.weapons.Uchigatana
 * @see game.weapons.GreatKnife
 * @see game.weapons.Scimitar
 * @author Khor Jia Wynn,Saw Tze Ying,Wong An Hong
 */
public class MerchantKale extends Trader{

    /**
     * Constructor.
     */
    public MerchantKale() {
        super("Merchant Kale", 'K', 100000);
    }

    /**
     * Set the inventory of the trader.
     * The trader will have the Club, Uchigatana, Great Knife and Scimitar in its inventory.
     */
    @Override
    void setInventory() {
        this.getPurchasables().add(new Club().getPurchasableType());
        this.getPurchasables().add(new Uchigatana().getPurchasableType());
        this.getPurchasables().add(new GreatKnife().getPurchasableType());
        this.getPurchasables().add(new Scimitar().getPurchasableType());
    }



}
