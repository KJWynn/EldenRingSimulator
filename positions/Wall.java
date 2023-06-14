package game.positions;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;

/**
 * A class that represents the wall inside a building.
 * Created by:
 * @author Riordan D. Alfredo
 * Modified by: Khor Jia Wynn
 *
 */
public class Wall extends Ground {

	/**
	 * Constructor
	 */
	public Wall() {
		super('#');
	}

	/**
	 * No actor can enter
	 * @param actor the Actor to check
	 * @return false
	 */
	@Override
	public boolean canActorEnter(Actor actor) {
		return false;
	}

	/**
	 * Blocks any thrown objects
	 * @return true
	 */
	@Override
	public boolean blocksThrownObjects() {
		return true;
	}
}
