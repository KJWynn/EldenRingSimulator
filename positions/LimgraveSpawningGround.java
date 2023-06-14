package game.positions;
import edu.monash.fit2099.engine.positions.Location;
import game.utils.RandomNumberGenerator;


/**
 * An abstract class that represents the spawning ground of enemies
 * @author Khor Jia Wynn,Saw Tze Ying,Wong An Hong
 */
public abstract class LimgraveSpawningGround extends SpawningGround{

    /**
     * Constructor.
     *
     * @param displayChar character to display for this type of terrain
     */
    public LimgraveSpawningGround(char displayChar) {
        super(displayChar);
    }

    /**
     * A method that return the spawn chance of East Enemy
     * @return the spawn chance of East Enemy
     */
    public abstract int getEastSpawnChance();

    /**
     * A method that return the spawn chance of West Enemy
     * @return the spawn chance of West Enemy
     */
    public abstract int getWestSpawnChance();

    /**
     * A method that check if there is any enemies in the spawning ground of the parameter location
     * @param location The location of this ground
     * @return EastEnemyFactory if the location is at the east of the map,
     * WestEnemyFactory if the location is at the west of the map, null otherwise
     */
    public EnemyFactory checkEastWest(Location location){
        int mapWidth = location.map().getXRange().max();
        int mapCentre = mapWidth / 2;
        // east
        if (location.x() >= mapCentre) {
            if (!location.containsAnActor() && RandomNumberGenerator.getSuccessOrFailure(getEastSpawnChance())) {
                return new EastEnemyFactory();
            }
        }
        //west
        else {
            if (!location.containsAnActor() && RandomNumberGenerator.getSuccessOrFailure(getWestSpawnChance())) {
                return new WestEnemyFactory();
            }
        }
        return null;
    }

}
