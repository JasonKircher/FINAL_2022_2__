package game.gameParts.cards.abilities.physical;

import game.gameParts.cards.abilities.DefensiveAbility;

/**
 * resembles a defensive physical ability
 * @author upvlx
 * @version 0.1
 */
public abstract class PhysicalDefensiveAbility extends DefensiveAbility {
    /**
     * constructor
     * @param abilityLevel the ability level the ability is supposed to have
     */
    public PhysicalDefensiveAbility(int abilityLevel) {
        super(abilityLevel);
        this.physical = true;
    }
}
