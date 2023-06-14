package game.items;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.DropAction;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.ConsumeAction;
import game.actions.GenerateRunesAction;
import game.capabilities.Consumable;
import game.capabilities.CurrencyType;
import game.capabilities.GenerateRuneCapable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Class representing a Golden Rune.
 * It is a consumable rune that can be consumed by an actor.
 * It can be picked up or dropped, but Currency has portability false by default, so in PickUpCurrencyAction, if the Currency is GoldenRune, we must change portability
 * to True.
 * @see Currency
 * @see Consumable
 * @see GenerateRuneCapable
 * @see ConsumeAction
 * @author Khor Jia Wynn,Saw Tze Ying,Wong An Hong
 */
public class GoldenRune extends Currency implements Consumable, GenerateRuneCapable {

    /**
     * Check if the golden rune has been added to the actions list.
     */
    private boolean addedToActions = false;

    /**
     * Constructor.
     */
    public GoldenRune() {
        super("Golden Rune", '*', 1);
        addCapability(CurrencyType.GOLDEN_RUNE);
    }

    /**
     * Returns the remaining number of times the golden rune can be consumed.
     * @return an integer representing the remaining number of times the golden rune can be consumed.
     */
    @Override
    public int getConsumeTimes() {
        return getAmount();
    }

    /**
     * Action of generating a random number of runes between 200 and 10000
     * @return an action of generating a random number of runes
     */
    @Override
    public Action getAction() {
        int amount = getGeneratedRuneAmount(200, 10000);
        return new GenerateRunesAction(amount, getAmount());
    }

    /**
     * Set the remaining times of consuming golden rune.
     * @param consumeTimes the remaining times
     */
    @Override
    public void setConsumeTimes(int consumeTimes) {
        setAmount(consumeTimes);
    }

    /**
     * Returns the maximum times golden rune can be consumed.
     * @return an integer representing the maximum times golden rune can be consumed
     */
    @Override
    public int getMaxConsumeTimes() {
        return getAmount();
    }


    /**
     * Returns the amount of runes consumed during the consume action.
     * @return an integer representing the amount of runes consumed
     */
    @Override
    public int getAmountConsumed() {
        return 1;
    }

    /**
     * Tick method for Golden Rune. Allows ConsumeAction if have remaining GoldenRune
     * @param currentLocation The location of the actor encounter the Golden Rune.
     * @param actor The actor that encounter the Golden Rune such as PLayer.
     */
    @Override
    public void tick(Location currentLocation, Actor actor) {
        super.tick(currentLocation, actor);
        if (!addedToActions && getConsumeTimes() > 0){
            addAction(new ConsumeAction(this));
            addedToActions = true;
        }
        if (getConsumeTimes() == 0 && !getAllowableActions().isEmpty()){
            removeAction(getAllowableActions().get(0));
        }
    }

    /**
     * Reset addedToActions so that it can be picked up if the player drops the rune
     * @param actor the Player
     * @return the drop action
     */
    @Override
    public DropAction getDropAction(Actor actor) {
        if (!getAllowableActions().isEmpty()) {
            removeAction(getAllowableActions().get(0));
        }
        addedToActions = false;
        return super.getDropAction(actor);
    }

    /**
     * Returns the bounds of the random number of runes generated.
     * @return a list of integers representing the bounds of the random number of runes generated
     */
    @Override
    public List<Integer> getBounds() {
        return new ArrayList<>(Arrays.asList(200, 10000));
    }

}
