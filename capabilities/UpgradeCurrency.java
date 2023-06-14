package game.capabilities;
/**
 * Interface representing the upgrade currency capability
 * @author Khor Jia Wynn,Saw Tze Ying,Wong An Hong
 */

public interface UpgradeCurrency {
    /**
     * Returns the amount of the currency used for upgrading
     */
    int getAmountUsedForUpgrade();

    /**
     * Returns the total amount of this upgrade currency
     */
    int totalAmount();

    /**
     * Increases the amount of this upgrade currency required for upgrading
     */
    void useForUpgrade();
}
