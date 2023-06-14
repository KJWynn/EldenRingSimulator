package game.items;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.ConsumeAction;
import game.actions.UpgradeAction;
import game.capabilities.Consumable;
import game.capabilities.CurrencyType;
import game.capabilities.Status;
import game.capabilities.UpgradeCurrency;

/**
 * Class representing a GoldenSeed.
 * It is a consumable item that can be consumed by an actor.
 * @author Khor Jia Wynn,Saw Tze Ying,Wong An Hong
 */
public class GoldenSeed extends Currency implements Consumable, UpgradeCurrency {

    /**
     * Boolean to indicate whether ConsumeAction has been added to allowableActions
     */
    private boolean addedToActions = false;

    /**
     * Number of seeds required for ConsumeAction to be successful, it increases with each executed ConsumeAction
     */
    private static int seedsRequired = 1;

    /**
     * Since after 5 rounds the GoldenSeed will disappear,
     * this variable keeps track of the duration of the GoldenSeed on the ground
     */
    private int durationOnGround = 0;

    /**
     * The maximum play turn of the GoldenSeed on the ground
     */
    private static final int MAX_DURATION = 5;

    /**
     * The flask of crimson tears that is used to upgrade the GoldenSeed
     */
    private FlaskOfCrimsonTears flask;

    /**
     * Constructor.
     */
    public GoldenSeed() {
        super("Golden Seed", '6', 1);
        addCapability(CurrencyType.GOLDEN_SEED);
    }

    /**
     * Returns the current amount of this GoldenSeed
     * @return amount, an integer representing the current amount of this GoldenSeed
     */
    @Override
    public int getConsumeTimes() {
        return getAmount();
    }

    /**
     * Returns the special action which occurs from consuming this GoldenSeed
     * @return an instance of UpgradeAction
     */
    @Override
    public Action getAction() {
        return new UpgradeAction(this, flask);
    }

    /**
     * Sets the remaining times of this GoldenSeed to be consumed
     * @param consumeTimes the remaining times
     */
    @Override
    public void setConsumeTimes(int consumeTimes) {
        setAmount(consumeTimes);
    }

    /**
     * Returns the maximum number of times this GoldenSeed can be consumed
     * @return an integer representing the maximum number of times this GoldenSeed can be consumed
     */
    @Override
    public int getMaxConsumeTimes() {
        return getAmount();
    }


    /**
     * Returns the amount of Golden Seeds consumed during the ConsumeAction. This increases by one with each consumption.
     * @return seedsRequired, an integer representing the required number of seeds for next ConsumeAction
     */
    @Override
    public int getAmountConsumed() {
        return seedsRequired;
    }

    /**
     * Sets duration of this GoldenSeed on the ground.
     * After one play turn, the duration increases by one.
     * If the duration is greater than or equal to MAX_DURATION, the GoldenSeed will disappear.
     * @param currentLocation The location of the ground on which we lie.
     */
    @Override
    public void tick(Location currentLocation) {
        super.tick(currentLocation);
        durationOnGround += 1;
        if (!(durationOnGround < MAX_DURATION)){
            currentLocation.removeItem(this);
        }
    }

    /**
     * Handle add or remove consume action from GoldenSeed allowable actions.
     * @param currentLocation The location of the actor carrying this Item.
     * @param actor The actor carrying this Item.
     */
    @Override
    public void tick(Location currentLocation, Actor actor) {
        super.tick(currentLocation, actor);
        // check flask
        if (flask == null) {
            for (Item item : actor.getItemInventory()) {
                if (item.hasCapability(Status.CRIMSON_TEARS)) {
                    flask = (FlaskOfCrimsonTears) item;
                    break;
                }
            }
        }

        if (!addedToActions && getAmount() >= getAmountConsumed() && flask.canUpgrade()){
            addAction(new ConsumeAction(this));
            addedToActions = true;
        }
        /**
         * To remove golden seed from the allowable actions if:
         *  1. If not enough golden seed for upgrade
         *  OR
         *  2. If flask cannot upgrade because it has been upgraded to the maximum level
         *  OR
         *  3. If the GoldenSeed has been consumed for the maximum times
         *  AND
         *  4. the GoldenSeed allowable actions is not empty
         */
        if ((getAmount() < getAmountConsumed() || !flask.canUpgrade() || getConsumeTimes() == 0) && !(getAllowableActions().isEmpty())){
            removeAction(getAllowableActions().get(0));
        }
    }

    /**
     * Returns the amount of Golden Seeds required for ConsumeAction to be successful
     * @return seedsRequired, an integer representing the required number of seeds for next ConsumeAction
     */
    @Override
    public int getAmountUsedForUpgrade() {
        return getAmountConsumed();
    }

    /**
     * Returns the total amount of Golden Seeds we have currently
     * @return an integer representing the total amount of Golden Seeds
     */
    @Override
    public int totalAmount() {
        return getAmount();
    }

    /**
     * Increase the number of seeds required for ConsumeAction by 1, called each time ConsumeAction is successful
     */
    @Override
    public void useForUpgrade() {
        // increase number of golden seeds required for next ConsumeAction
        seedsRequired += 1;
    }
}
