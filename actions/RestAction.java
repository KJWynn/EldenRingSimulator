package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.positions.SiteOfLostGrace;
import game.reset.ResetManager;

/**

 * A class that represents the action of resting at a site of lost grace in the game.
 * @author Khor Jia Wynn,Saw Tze Ying,Wong An Hong
 */
public class RestAction extends Action {
    /**
     * The SiteOfLostGrace location where the actor will rest.
     */
    private SiteOfLostGrace location;

    /**
     * Constructor.
     * @param location the SiteOfLostGrace where the actor will rest.
     */
    public RestAction(SiteOfLostGrace location){
        this.location = location;
    }

    /**
     * Executes the resting action for the actor at the specified location, and returns a string
     * describing the action that was taken.
     * @param actor the Player who is resting.
     * @param map the GameMap where the resting action takes place.
     * @return a string describing the resting action that was taken.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        String result = "";
        result += actor + " rests at " + location.getName() + ". ";
        result += ResetManager.getInstance().run();
        return result;
    }

    /**
     * Returns a string describing the resting action that can be displayed in the game menu.
     * @param actor the player who is resting.
     * @return a string describing the resting action that can be displayed in the game menu.
     */
    @Override
    public String menuDescription(Actor actor) {
        return "Rest in Site of Lost Grace";
    }

}
