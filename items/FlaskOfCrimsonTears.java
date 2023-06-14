package game.items;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.ConsumeAction;
import game.actions.HealAction;
import game.capabilities.Consumable;
import game.capabilities.HealCapable;
import game.capabilities.Status;
import game.capabilities.Upgradable;
import game.reset.Resettable;

/**
 * Class representing a Flask of Crimson Tears.
 * It is a consumable item that can be consumed by an actor.
 * It can heal the actor for 250 hit points.
 * It can be consumed twice.
 * @see Item
 * @see Consumable
 * @see Resettable
 * @see HealCapable
 * @see Upgradable
 * @see ConsumeAction
 * @author Khor Jia Wynn,Saw Tze Ying,Wong An Hong
 */
public class FlaskOfCrimsonTears extends Item implements Consumable, Resettable, HealCapable, Upgradable {

    /**
     * The current number of uses of this consumable
     */
    private int consumeTimes;

    /**
     * The maximum times this upgradable can be upgraded
     */
    public static final int MAXIMUM_UPGRADES = 5;

    /**
     * Boolean to indicate whether ConsumeAction has been added to allowableActions
     */
    private boolean addedToActions = false;

    /**
     * Maximum consume times of this consumable
     */
    private int max_consume_times;

    /**
     * Heal amount of this heal capable
     */
    private int heal_amount;

    /**
     * Returns the times this flask has been upgraded
     * @return the times this flask has been upgraded
     */
    public int getTimesUpgraded() {
        return timesUpgraded;
    }

    /**
     * Sets the upgrade times of this upgradable
     * @param timesUpgraded the new upgrade times
     */
    public void setTimesUpgraded(int timesUpgraded) {
        this.timesUpgraded = timesUpgraded;
    }

    /**
     * The times this upgradable has been upgraded
     */
    private int timesUpgraded = 0;


    /***
     * Constructor for FlaskOfCrimsonTears.
     */
    public FlaskOfCrimsonTears() {
        super("Flask of Crimson Tears", 'F', false);
        addAction(new ConsumeAction(this));
        addedToActions = true;
        heal_amount = 250;
        consumeTimes = 2;
        max_consume_times = 2;
        addCapability(Status.CRIMSON_TEARS);
    }

    /**
     * Returns the times flask of crimson tears has been consumed.
     *
     * @return an integer representing the times flask of crimson tears has been consumed
     */
    public int getConsumeTimes() {
        return consumeTimes;
    }

    /**
     * Sets the times flask of crimson tears has been consumed.
     *
     * @param consumeTimes an integer representing the time flask of crimson tears has been consumed
     */
    public void setConsumeTimes(int consumeTimes) {
        this.consumeTimes = consumeTimes;
    }

    /**
     * Returns the amount of hit points the flask of crimson tears can heal.
     *
     * @return an integer representing the amount of hit points the flask of crimson tears can heal
     */
    @Override
    public int getHealAmount() {
        return heal_amount;
    }

    /**
     * Gets flaskOfCrimsonTears heal amount action.
     *
     * @return a HealAction that can heal the actor for 250 hit points
     */
    @Override
    public Action getAction() {
        return new HealAction(getHealAmount(), this);
    }

    /**
     * Returns the number of times the flask of crimson tears has consumed.
     *
     * @return an integer representing the number of times the flask of crimson tears has consumed
     */
    @Override
    public int getAmountConsumed() {
        return 1;
    }

    /**
     * Returns the maximum number of times the flask of crimson tears can be consumed.
     *
     * @return an integer representing the maximum number of times the flask of crimson tears can be consumed
     */
    @Override
    public int getMaxConsumeTimes() {
        return getMaxStat();
    }


    /**
     * If the player has died and the game is being reset, reset the number of times the flask can be consumed to 2.
     * @return a string indicating the number of times the flask can be consumed has been reset to 2
     */
    @Override
    public String reset() {
        consumeTimes = getMaxStat();
        return "Number of Flask Of Crimson Tears has been reset to " + getMaxStat() + ". \n";
    }

    /**
     * Tick method for the flask of crimson tears.
     * Register it as a Resettable in ResetManager so that it is involved in next reset
     * @param currentLocation The location of the actor carrying this Item.
     * @param actor The actor carrying this Item.
     */
    @Override
    public void tick(Location currentLocation, Actor actor) {
        super.tick(currentLocation, actor);
        registerResettable(this);

        if (!addedToActions && getConsumeTimes() > 0){
            addAction(new ConsumeAction(this));
            addedToActions = true;
        }
        if (getConsumeTimes() == 0 && !getAllowableActions().isEmpty()){
            removeAction(getAllowableActions().get(0));
        }
    }

    /**
     * Returns the maximum number of times the flask of crimson tears can be consumed.
     * @return an integer representing the maximum number of times the flask of crimson tears can be consumed
     */
    @Override
    public int getMaxStat() {
        return max_consume_times;
    }

    /**
     * Sets the maximum number of times the flask of crimson tears can be consumed.
     * @param newStat
     */
    @Override
    public void setMaxStat(int newStat) {
        max_consume_times = newStat;
    }

    /**
     * Returns the maximum number of times the flask of crimson tears can be upgraded.
     * @return an integer representing the maximum number of times the flask of crimson tears can be upgraded
     */
    @Override
    public int getMaxUpgradeTimes() {
        return MAXIMUM_UPGRADES;
    }

    /**
     * Returns the amount of upgrade the flask of crimson tears can be upgraded.
     * @return an integer representing the amount of upgrade the flask of crimson tears can be upgraded
     */
    @Override
    public int getUpgradeAmount() {
        return 2;
    }

    /**
     * Upgrades the flask of crimson tears.
     * @return a string indicating the flask of crimson tears has been upgraded
     */
    @Override
    public String upgrade() {
        //upgrade flask's max uses, update upgrade times
        int originalAmount = this.getMaxStat();
        this.setMaxStat(originalAmount + getUpgradeAmount());

        if (this.getConsumeTimes() == 0) {
            this.resetAddedToActions();
        }

        this.setConsumeTimes(originalAmount + getUpgradeAmount());

        this.setTimesUpgraded(this.getTimesUpgraded()+1);

        return "Flask of Crimson Tears' uses has been upgraded to " + (originalAmount + getUpgradeAmount());
    }

    /**
     * Resets the addedToActions boolean to false.
     */
    public void resetAddedToActions(){
        addedToActions = false;
    }
}
