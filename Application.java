package game;

import java.util.List;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.FancyGroundFactory;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.positions.World;
import game.actors.Player;
import game.actors.npc.FingerReaderEnia;
import game.actors.npc.MerchantKale;
import game.actors.npc.Trader;
import game.displays.FancyMessage;
import game.items.GoldenRune;
import game.positions.*;
import game.roles.Astrologer;
import game.roles.Bandit;
import game.roles.Samurai;
import game.roles.Wretch;

/**
 * The main class to start the game.
 * Created by:
 * @author Adrian Kristanto
 * Modified by: Khor Jia Wynn,Saw Tze Ying,Wong An Hong
 */
public class Application {

	public static void main(String[] args) {

		World world = new World(new Display());

		FancyGroundFactory groundFactory = new FancyGroundFactory(new Dirt(), new Wall(), new Floor(),new GustOfWind(),
				new Graveyard(),new PuddleOfWater(),new SiteOfLostGrace(),new Cliff(),new Barrack(),new Cage(),new SummonSign(), new GoldenSeedTree());

		GameMapManager.setGroundFactory(groundFactory);
		GameMapManager.setWorld(world);
		List<String> map = GameMapManager.getInstance().getLIMGRAVE();
		GameMap stormveilCastleMap = GameMapManager.getInstance().getMapHashMap().get("STORMVEILCASTLE");
		GameMap limgraveMap = GameMapManager.getInstance().getMapHashMap().get("LIMGRAVE");
		GameMap roundtableHoldMap = GameMapManager.getInstance().getMapHashMap().get("ROUNDTABLEHOLD");
		GameMap bossRoomMap = GameMapManager.getInstance().getMapHashMap().get("BOSSROOM");
		stormveilCastleMap.at(5,0).setGround(new GoldenFogDoor(new Location(bossRoomMap,0,4),"Boss room"));
		limgraveMap.at(29,0).setGround(new GoldenFogDoor(new Location(roundtableHoldMap,9,10),"Round Table Hold"));
		limgraveMap.at(5,23).setGround(new GoldenFogDoor(new Location(stormveilCastleMap,37,23),"Stormveil Castle"));
		stormveilCastleMap.at(37,23).setGround(new GoldenFogDoor(new Location(limgraveMap,5,23),"Limgrave"));
		roundtableHoldMap.at(9,10).setGround(new GoldenFogDoor(new Location(limgraveMap,29,0),"Limgrave"));


		int xC=0;
		int yC=0;
		for (int y = 0; y < map.size(); y++) {
			for (int x = 0; x < map.get(0).length(); x++) {
				if (map.get(y).charAt(x) =='U'){
					xC = x;
					yC = y;
					break;
				}
			}
		}


		//testing GoldenRune and GoldenSeed
		limgraveMap.at(37, 10).addItem(new GoldenRune());
//		for (int i = 0; i < 16; i++) {
//			gameMap.at(35, 10).addItem(new GoldenSeed());
//		}

		// BEHOLD, ELDEN RING
		for (String line : FancyMessage.ELDEN_RING.split("\n")) {
			new Display().println(line);
			try {
				Thread.sleep(200);
			} catch (Exception exception) {
				exception.printStackTrace();
			}
		}

		Display roleDisplay = new Display();
		roleDisplay.println("Choose your player role: \n b.Bandit \n s.Samurai \n w.Wretch \n a.Astrologer");
		char playerRole = roleDisplay.readChar();
		try {
			if (playerRole=='b' || playerRole=='s' || playerRole=='w'|| playerRole=='a'){
				Player player = new Player(
						playerRole == 'b' ? new Bandit() : playerRole == 's' ? new Samurai() : playerRole =='w'?
								new Wretch(): new Astrologer(), xC, yC);
				world.addPlayer(player, limgraveMap.at(36, 10));
			}
		} catch (Exception exception) {
			exception.printStackTrace();
		}

		Trader trader = new MerchantKale();
		Trader enia = new FingerReaderEnia();
		limgraveMap.addActor(trader, limgraveMap.at(40,12));
		roundtableHoldMap.addActor(enia, roundtableHoldMap.at(10,1));
		world.run();

	}
}
