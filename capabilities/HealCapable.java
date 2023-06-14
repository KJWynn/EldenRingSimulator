package game.capabilities;

/**
 * Interface representing the heal capability, applicable to items like FlaskOfCrimsonTears
 * @author Khor Jia Wynn,Saw Tze Ying,Wong An Hong
 */
public interface HealCapable {

    /**
     * Returns the hitpoints restored
     * @ integer representing the number of hitpoints restored
     */
    int getHealAmount();
}
