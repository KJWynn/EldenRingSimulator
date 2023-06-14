package game.weapons;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.actions.AreaAttackAction;
import game.capabilities.Status;
/**
 * A class that represent the weapon called AxeOfGodrick
 * @see game.actors.npc.FingerReaderEnia
 * @author Khor Jia Wynn,Saw Tze Ying,Wong An Hong
 */
public class AxeOfGodrick extends WeaponItem implements Tradable, Sellable{
    /**
     * Constructor.
     */
    public AxeOfGodrick() {
        super("Axe of Godrick", 'T', 142, "swings", 84);
        addSellableCapability();
    }

    /**
     * A method that returns the sell price of the AxeOfGodrick weapon
     * @return 100 (sell price of AxeOfGodrick weapon)
     */
    @Override
    public int sellPrice() {
        return 100;
    }

    /**
     * A method that returns the weapon item of the AxeOfGodrick weapon
     * @return WeaponItem type of AxeOfGodrick weapon
     */
    @Override
    public WeaponItem getWeaponItem() {
        return this;
    }

    /**
     * A method that adds the sellable capability to the AxeOfGodrick weapon
     */
    @Override
    public void addSellableCapability() {
        addCapability(Status.SELLABLE);
    }
    /**
     * A method that returns the AxeOfGodrick weapon as Tradable type
     * @return AxeOfGodrick weapon as Tradable type
     */
    @Override
    public Tradable getTradableType() {
        return this;
    }

    /**
     * A method that returns the special skills of this weapon
     * @param target target actor
     * @param direction direction of the target actor
     * @return sellAction if the target is trader and areaAttackAction
     *       if the target is hostile to player, null otherwise
     */
    @Override
    public Action getSkill(Actor target, String direction) {
        if (target.hasCapability(Status.TRADER)){return new SellAction(this, target);}
        else if (target.hasCapability(Status.HOSTILE_TO_PLAYER)) {
            return new AreaAttackAction(this);
        }
        else return null;
    }
}



