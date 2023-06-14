package game.capabilities;

import edu.monash.fit2099.engine.actors.Actor;
import game.items.CurrencyManager;
import game.utils.RandomNumberGenerator;

import java.util.List;

/**
 * DropRune interface.
 * Represents the capability of an actor to drop rune.
 * Actor that has this capability can drop rune when it is killed will implement this interface.
 * @author Khor Jia Wynn,Saw Tze Ying,Wong An Hong
 */
public interface GenerateRuneCapable {

    /**
     * Returns tuple of the lower bound and upper bound of the rune amount to be generated
     * @return list of two integers(lower bound and upper bound)
     */
    List<Integer> getBounds();


    /**
     * In the RuneManager, sets the amount of rune the actor drops when it is killed.
     *
     * @param actor the actor that drops rune
     */
    default void setDropRuneAmount(Actor actor){
        int lowBound = getBounds().get(0);
        int highBound = getBounds().get(1);
        CurrencyManager.getInstance().getActorRuneAmountHashMap().put(actor, getGeneratedRuneAmount(lowBound, highBound));
    }


    /**
     * Returns the random amount of rune generated within the specified bounds
     *
     * @return integer the amount of rune generated
     */
    default int getGeneratedRuneAmount(int lowBound, int highBound){
        return RandomNumberGenerator.getRandomInt(lowBound, highBound);
    }
}
