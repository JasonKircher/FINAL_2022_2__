package game.gameParts.cards.abilities.physical;

import game.gameParts.cards.abilities.OffensiveAbility;

/**
 * resembles a offensive physical Ability
 * @author upvlx
 * @version 0.1
 */
public abstract class PhysicalOffensiveAbility extends OffensiveAbility {
    /**
     * constructor
     * @param abilityLevel the ability level the ability is supposed to have
     */
    public PhysicalOffensiveAbility(int abilityLevel) {
        super(abilityLevel);
        this.physical = true;
    }
}
