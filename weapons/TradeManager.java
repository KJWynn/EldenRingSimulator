package game.weapons;

import java.util.ArrayList;
import java.util.List;
/**
 * This TradeManager class is created to manage the logic while trading
 * @author Khor Jia Wynn, Saw Tze Ying , Wong An Hong
 * @version 1.0
 * @see game.actors.npc.Trader
 * */
public class TradeManager {
    /**
     * The singleton instance of the TradeManager.
     */
    private static TradeManager tradeManager;
    /**
     * The list which contain the weapon that are purchasable.
     */
    private final List<Purchasable> purchasableWeapons = new ArrayList<>();

    /**
     * A private constructor for the TradeManager class
     */
    private TradeManager(){
        setPurchasables();
    }

    /**
     * A static method of creating new Trade Manager instance when tradeManager is null and return it
     * else just return Trade Manager instance
     * @return TradeManager instance
     */
    public static TradeManager getInstance(){
        if (tradeManager == null){
            tradeManager = new TradeManager();
        } return tradeManager;
    }

    /**
     * A method that put the weapon that are sellable in to the purchasableWeapons.
     * We use .getPurchasableType() so that the weapon is Purchasable type.
     */
    private void setPurchasables(){
        purchasableWeapons.add(new Club().getPurchasableType());
        purchasableWeapons.add(new Uchigatana().getPurchasableType());
        purchasableWeapons.add(new GreatKnife().getPurchasableType());
        purchasableWeapons.add(new Scimitar().getPurchasableType());
    }

    /**
     * A getter to get the purchasableWeapons
     * @return purchasableWeapons
     */
    public List<Purchasable> getPurchasableWeapons() {
        return purchasableWeapons;
    }
}
