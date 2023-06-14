package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.capabilities.Upgradable;
import game.capabilities.UpgradeCurrency;

/**
 * UpgradeAction class is an action class to upgrade an item.
 * @see game.capabilities.UpgradeCurrency
 * @see game.capabilities.Upgradable
 * @author Khor Jia Wynn,Saw Tze Ying,Wong An Hong
 */

public class UpgradeAction extends Action {

    /**
     * The upgrade currency used to upgrade the item
     */
    private final UpgradeCurrency upgradeCurrency;
    /**
     * The item to be upgraded
     */
    private final Upgradable upgradable;

    /**
     * Constructor for UpgradeAction
     * @param upgradeCurrency The upgrade currency used to upgrade the item
     * @param upgradable The item to be upgraded
     */
    public UpgradeAction(UpgradeCurrency upgradeCurrency, Upgradable upgradable) {
        this.upgradeCurrency = upgradeCurrency;
        this.upgradable = upgradable;
    }


    /**
     Executes the UpgradeAction
     @param actor The actor performing the UpgradeAction
     @param map The GameMap where the action is being performed
     @return A String containing the result of UpgradeAction.
     */

    @Override
    public String execute(Actor actor, GameMap map) {
        String returnString = upgradable.upgrade() +  " using " + upgradeCurrency.getAmountUsedForUpgrade() + " " + upgradeCurrency;
        upgradeCurrency.useForUpgrade();
        return returnString;
    }


    /**
     * Returns a string describing number of seeds used and number of seeds available
     * @param actor The actor performing the action.
     * @return String describing number of seeds used and number of seeds available
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " uses " + upgradeCurrency.getAmountUsedForUpgrade()+ " " + upgradeCurrency + "(" + upgradeCurrency.totalAmount() + " available) to upgrade " + upgradable;
    }

}
