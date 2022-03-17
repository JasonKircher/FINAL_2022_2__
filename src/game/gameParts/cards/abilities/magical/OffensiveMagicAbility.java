package game.gameParts.cards.abilities.magical;

import game.gameParts.cards.abilities.OffensiveAbility;

/**
 * resembles a defensive Magic ability
 * @author upvlx
 * @version 0.1
 */
public abstract class OffensiveMagicAbility extends OffensiveAbility {
    /**
     * constructor
     * @param abilityLevel the ability level the ability is supposed to have
     */
    public OffensiveMagicAbility(int abilityLevel) {
        super(abilityLevel);
        this.physical = false;
        this.offensive = true;
    }
}
