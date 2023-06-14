package game.positions;
import edu.monash.fit2099.engine.positions.FancyGroundFactory;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.World;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
/**
 * A class that represents a GameMapManger, which is responsible to Manage multiple gamemap.
 * @version 1.0
 * @author Khor Jia Wynn, Saw Tze Ying , Wong An Hong
 * @see game.positions.GoldenFogDoor
 * @see game.actions.TravelAction
 */
public class GameMapManager {

    /**
     * The singleton instance of the GameMapManager
     */
    private static GameMapManager instance;

    /**
     *  Private class variable that represent the groundFactory
     * */
    private static FancyGroundFactory groundFactory;

    /**
     *  Private class variable that represent the world
     * */
    private static World world;

    /**
     *  HashMap that map the string (name) to it's corresponding GameMap
     * */
    private HashMap<String,GameMap> mapHashMap = new HashMap<>();

    /**
     * Limgrave map
     * */
    private List<String> LIMGRAVE = Arrays.asList(
            "......................#.............#..........................+++.........",
            "......................#.............#.......................+++++..........",
            "......................#..___....____#.........................+++++........",
            "......................#...........__#............................++........",
            "......................#_____........#.............................+++......",
            "......................#............_#..............................+++.....",
            "......................######...######......................................",
            "...........................................................................",
            "...........................=...............................................",
            "........++++......................###___###................................",
            "........+++++++...................________#................................",
            "..........+++.....................#___U____..V.............................",
            "............+++...................#_______#................................",
            ".............+....................###___###.........V......................",
            "............++......................#___#..................................",
            "..............+...................=........................................",
            "..............++.................................................=.........",
            "..............................................++...........................",
            "..................++++......................+++...............######..##...",
            "#####___######....++...........................+++............#....____....",
            "_____________#.....++++..........................+..............__.....#...",
            "_____________#.....+....++........................++.........._.....__.#...",
            "_____________#.........+..+.....................+++...........###..__###...",
            "_____________#.............++..............................................");

    /**
     * StormveilCastle map
     * */
    private List<String> STORMVEILCASTLE = Arrays.asList(
            "...........................................................................",
            "..................<...............<........................................",
            "...........................................................................",
            "##############################################...##########################",
            "............................#................#.......B..............B......",
            ".....B...............B......#................#.............................",
            "...............................<.........<.................................",
            ".....B...............B......#................#.......B..............B......",
            "............................#................#.............................",
            "#####################..#############...############.####..#########...#####",
            "...............#++++++++++++#................#++++++++++++#................",
            "...............#++++++++++++...<.........<...#++++++++++++#................",
            "...............#++++++++++++..................++++++++++++#................",
            "...............#++++++++++++#................#++++++++++++#................",
            "#####...##########.....#############...#############..#############...#####",
            ".._______........................B......B........................B.....B...",
            "_____..._..____....&&........<..............<..............................",
            ".........____......&&......................................................",
            "...._______..................<..............<....................<.....<...",
            "#####....##...###..#####...##########___###############......##.....####...",
            "+++++++++++++++++++++++++++#...................#+++++++++++++++++++++++++++",
            "+++++++++++++++++++++++++++....................#+++++++++++++++++++++++++++",
            "+++++++++++++++++++++++++++#....................+++++++++++++++++++++++++++",
            "+++++++++++++++++++++++++++#...................#+++++++++++++++++++++++++++"
    );

    /**
     * RoundtableHold map
     * */
    private List<String> ROUNDTABLEHOLD = Arrays.asList(
            "##################",
            "#________________#",
            "#________________#",
            "#________________#",
            "#________________#",
            "#________________#",
            "#________________#",
            "#________________#",
            "#________________#",
            "#________________#",
            "########___#######"
    );
    /**
     * Bossroom map
     * */
    private List<String> BOSSROOM = Arrays.asList(
            "+++++++++++++++++++++++++",
            ".........................",
            ".........................",
            ".........................",
            ".........................",
            ".........................",
            ".........................",
            ".........................",
            "+++++++++++++++++++++++++"
    );

    /**
     Constructor.
     @param world The world of this game
     @param groundFactory  The ground factory for this game
     */
    public GameMapManager(World world, FancyGroundFactory groundFactory){
        GameMap limgraveMap = new GameMap(groundFactory,LIMGRAVE);
        GameMap stormveilCastleMap = new GameMap(groundFactory,STORMVEILCASTLE);
        GameMap roundtableHoldMap = new GameMap(groundFactory,ROUNDTABLEHOLD);
        GameMap bossRoomMap = new GameMap(groundFactory,BOSSROOM);

        mapHashMap.put("LIMGRAVE",limgraveMap);
        mapHashMap.put("STORMVEILCASTLE",stormveilCastleMap);
        mapHashMap.put("ROUNDTABLEHOLD",roundtableHoldMap);
        mapHashMap.put("BOSSROOM",bossRoomMap);

        world.addGameMap(limgraveMap);
        world.addGameMap(stormveilCastleMap);
        world.addGameMap(roundtableHoldMap);
        world.addGameMap(bossRoomMap);


    }
    /**
     * Returns the singleton instance of the GameMapManager
     * @return The singleton instance of the GameMapManager
     */
    public static GameMapManager getInstance(){
        if (instance==null){
            instance = new GameMapManager(world,groundFactory);
        }
        return instance;
    }

    /**
     A setter to set the world attribute
     @param world The world of this game
     */
    public static void setWorld(World world) {
        GameMapManager.world = world;
    }

    /**
     A getter to get the world attribute
     @return The world of this game
     */
    public World getWorld() {
        return world;
    }

    /**
     A setter to set the groundFactory attribute
     @param groundFactory The groundFactory of this game
     */
    public static void setGroundFactory(FancyGroundFactory groundFactory) {
        GameMapManager.groundFactory = groundFactory;
    }

    /**
     A getter to get the list of limgrave
     @return The list of limgrave
     */
    public List<String> getLIMGRAVE() {
        return LIMGRAVE;
    }

    /**
     A getter to get the HashMap that hash name to GameMap
     @return The HashMap that hash name to GameMap
     */
    public HashMap<String,GameMap> getMapHashMap(){
        return mapHashMap;
    }

}
