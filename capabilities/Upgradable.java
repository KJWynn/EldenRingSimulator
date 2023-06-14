package game.capabilities;

/**
 * Interface representing the upgradable capability
 * @author Khor Jia Wynn,Saw Tze Ying,Wong An Hong
 */
public interface Upgradable {
    /**
     * the maximum stat of the actor
     */
    int getMaxStat();

    /**
     * Sets the maximum stat of the actor
     * @param newStat
     */
    void setMaxStat(int newStat);


    int getTimesUpgraded();

    /**
     * Returns the maximum times this upgradable can be upgraded
     * @return
     */
    int getMaxUpgradeTimes();


    /**
     * @return Returns true if this upgradable can be upgraded
     */
    default boolean canUpgrade(){
        return getTimesUpgraded() < getMaxUpgradeTimes();
    }


    /**
     * Returns the amount of stat increased after upgrading
     */

    int getUpgradeAmount();

    /**
     * Returns the action as a result of upgrading this upgradable
     */
    String upgrade();
}
