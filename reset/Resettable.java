package game.reset;

/**
 * A resettable interface
 * Created by:
 * @author Adrian Kristanto
 * Modified by: Khor Jia Wynn
 */
public interface Resettable {
    /**
     * Abstract method to be implemented by Resettable classes, e.g. reset attribute value, despawn, etc
     * @return String describing what happened in the reset process
     */
    String reset();

    /**
     * Registers the resettable in the ResetManager so that it will be involved when a reset is performed
     * @param resettable The resettable that needs to be involved in the next reset
     */
    default void registerResettable(Resettable resettable){
        ResetManager.getInstance().registerResettable(resettable);
    }
}
