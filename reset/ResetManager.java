package game.reset;

import java.util.ArrayList;
import java.util.List;

/**
 * A reset manager class that manages a list of resettables. Uses Singleton pattern.
 * Created by:
 * @author Adrian Kristanto
 * Modified by: Khor Jia Wynn
 *
 */
public class ResetManager {

    /**
     * Indicate that reset occured due to player death
     */
    private static boolean player_death = false;

    /**
     * The list of resettables
     */
    private List<Resettable> resettables;

    /**
     * The singleton instance of the ResetManager.
     */
    private static ResetManager instance;

    /**
     * Private constructor. Only called once when the static instance is initialized.
     */
    private ResetManager() {
        this.resettables = new ArrayList<>();
    }

    /**
     * Returns the static instance of PurchaseManager. If called for the first time, a new instance is created and assigned
     * to the static PurchaseManager variable and then returned.
     * @return The only instance that is accessible
     */
    public static ResetManager getInstance(){
        if (instance==null){
            instance = new ResetManager();
        }
        return instance;
    }

    /**
     * Resets the game by iterating through the list of resettable objects, calling each of their reset() methods.
     * After the reset, the list of resettables must be emptied because the resettable objects would be different
     * the next time a reset is performed.
     * @return String which describes the entire reset process.
     */
    public String run() {
        String result = "";
        for (Resettable resettable: resettables){
            result += resettable.reset();
        }

        // reset the list to empty list, because they are not relevant to the next ResetAction
        for (int i=resettables.size()-1; i>=0; i--){
            resettables.remove(resettables.get(i));
        }

        // reset player death to false
        if (player_death){
            player_death = false;
        }
        return result;
    }

    /**
     * Adds a Resettable to the list of resettables. Removes it if it was already in the list to prevent duplicates.
     * @param resettable A resettable, e.g. Enemy
     */
    public void registerResettable(Resettable resettable) {
        resettables.remove(resettable);
        resettables.add(resettable);
    }

    /**
     * Getter to get the player_death, which is true if player still alive else false
     */
    public static boolean isPlayer_death() {
        return player_death;
    }

    /**
     * Setter to set the player_death, which is true if player still alive else false
     */
    public static void setPlayer_death(boolean player_death) {
        ResetManager.player_death = player_death;
    }
}
