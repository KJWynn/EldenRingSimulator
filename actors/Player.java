package game.actors;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.displays.Menu;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.actions.DeathAction;
import game.behaviours.AttackBehaviour;
import game.items.*;
import game.positions.GameMapManager;
import game.roles.Role;
import game.reset.Resettable;
import game.capabilities.Status;

/**
 * Class representing the Player. It implements the Resettable interface.
 * It carries around a club to attack a hostile creature in the Lands Between.
 * Depending on the Role of the Player, it has a different starting special weapon and a different starting hitpoints
 * Created by:
 * @author Adrian Kristanto
 * Modified by:Khor Jia Wynn,Saw Tze Ying,Wong An Hong
 */
public class Player extends Actor implements Resettable {

	/**
	 * The last map the player was found on. Allows reset to remove the Player from map
	 */
	private GameMap lastMap;


	/**
	 * Previous location. May be same as current position if Player did not Move
	 */
	private Location previousLocation;

	/**
	 * Used to display the options during the Player's turn
	 */
	private final Menu menu = new Menu();

	/**
	 * Site of lost grace x coordinate
	 */
	private int xCoordinate;
	/**
	 * Site of lost grace y coordinate
	 */
	private int yCoordinate;



	/**
	 * Constructor.
	 * @param role the Role of the Player
	 * @param x_c x coordinate of the Player
	 * @param y_c y coordinate of the Player
	 */
	public Player(Role role, int x_c, int y_c) {
		super("Tarnished", '@',role.getHIT_POINT());
		this.maxHitPoints = role.getHIT_POINT();
		this.addCapability(Status.HOSTILE_TO_ENEMY);
		this.addCapability(Status.PLAYER);
		this.addWeaponToInventory(role.getWeaponItem());
		this.addItemToInventory(new FlaskOfCrimsonTears());
		this.addItemToInventory(new RemembranceOfGrafted());
//		this.addItemToInventory(new Rune());

		this.xCoordinate = x_c;
		this.yCoordinate = y_c;

		// initialize player's starting rune amounts as 0
//		CurrencyManager.getInstance().getActorRuneAmountHashMap().put(this, 0);
		CurrencyManager.getInstance().getPlayerCurrencyAmount().put('$', new Rune());
		CurrencyManager.getInstance().getPlayerCurrencyAmount().put('O', new RemembranceOfGrafted());
	}

	/**
	 * Sets the actions other Actors can do to the Player. Sets the actions Enemies can do to player
	 * using AttackBehaviour
	 * @param otherActor the Actor that might be performing attack
	 * @param direction  String representing the direction of the other Actor
	 * @param map        current GameMap
	 * @return
	 */
	public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
		ActionList list = super.allowableActions(otherActor, direction, map);

		if (otherActor.hasCapability(Status.HOSTILE_TO_PLAYER)) {
			list.add((new AttackBehaviour()).getAction(otherActor, map));
		}
		return list;
	}

	/**
	 * Player's turn.
	 * Updates Player's last map and location
	 * Registers Player as a Resettable
	 * If Trader is in vicinity, adds SellAction
	 * @param actions    collection of possible Actions for this Actor
	 * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
	 * @param map        the map containing the Actor
	 * @param display    the I/O object to which messages may be written
	 * @return Action chosen by player
	 */
	@Override
	public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {

		// registers Player as resettable
		registerResettable(this);

		// updates Player's current location and map
		/**
		 * The last location the player was found on before death. Used to drop Rune
		 */
		Location currentLocation = map.locationOf(this);
		lastMap = map;

		if (this.hasCapability(Status.FALL)){
			return new DeathAction(this);
		}

		// if lastAction was a MoveActorAction, update previous location
		if (previousLocation != currentLocation){
			previousLocation = currentLocation;
		}


		// allows SellAction if Trader is in vicinity
		String direction;
		Actor trader;
		for (int i=0; i< map.locationOf(this).getExits().size(); i++){
			if (map.locationOf(this).getExits().get(i).getDestination().containsAnActor() && map.locationOf(this).getExits().get(i).getDestination().getActor().hasCapability(Status.TRADER)){
				direction = map.locationOf(this).getExits().get(i).getName();
				trader = map.locationOf(this).getExits().get(i).getDestination().getActor();
				for (WeaponItem weaponItem: getWeaponInventory()){
					if (weaponItem.hasCapability(Status.SELLABLE)){
						Action sellAction = weaponItem.getSkill(trader, direction);
						if (sellAction != null) {
							actions.add(sellAction);
						}
					}
				}
			}
		}


		// Handle multi-turn Actions
		if (lastAction.getNextAction() != null)
			return lastAction.getNextAction();

		System.out.println(this + "("+ this.hitPoints + "/" +this.maxHitPoints + ")," +
				"runes: " + CurrencyManager.getInstance().getPlayerCurrencyAmount().get('$').getAmount());
		// return/print the console menu
		return menu.showMenu(this, actions, display);
	}

	/**
	 * Player's reset
	 * Drops runes, resets Player's hitpoints, spawns Player in SiteOfLostGrace
	 * @return String describing Player's reset process
	 */
	@Override
	public String reset() {
		String string = "";

		if (!this.isConscious() || this.hasCapability(Status.FALL)) {


			Currency playerRune = CurrencyManager.getInstance().getPlayerCurrencyAmount().get('$');
//			removeItemFromInventory(playerRune);
			this.previousLocation.addItem(playerRune);
			playerRune.resetAddedToActions();

			CurrencyManager.setDroppedRuneLocation(this.previousLocation);
			// update Player's current rune amount to 0
			CurrencyManager.getInstance().getPlayerCurrencyAmount().put(playerRune.getDisplayChar(), new Rune());
			string += this + " dropped " + playerRune.getAmount() + " runes. ";


			removeCapability(Status.FALL);
		}

		// Player hit point will be reset to maximum
		this.resetMaxHp(this.maxHitPoints);
		string += "The player's hitpoints has been restored. \n";

		lastMap.removeActor(this);
		//  Add player to site of lost grace
		GameMapManager.getInstance().getWorld().addPlayer(this,GameMapManager.getInstance().getMapHashMap().get("LIMGRAVE").at(xCoordinate,yCoordinate));
		string += "The player respawns at site of lost grace. ";
		return string;
	}

	/**
	 * Player's intrinsic weapon
	 * @return Player's intrinsic weapon
	 */
	@Override
	public IntrinsicWeapon getIntrinsicWeapon() {
		return new IntrinsicWeapon(11,"punches");
	}

}
