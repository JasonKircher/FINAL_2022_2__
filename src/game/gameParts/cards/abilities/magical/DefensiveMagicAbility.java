package game.gameParts.cards.abilities.magical;

import game.gameParts.cards.abilities.DefensiveAbility;

/**
 * class that resembles a defensive magic ability
 * @author upvlx
 * @version 0.1
 */
public abstract class DefensiveMagicAbility extends DefensiveAbility {
    /**
     * constructor
     * @param abilityLevel the ability level the ability is supposed to have
     */
    public DefensiveMagicAbility(int abilityLevel) {
        super(abilityLevel);
        this.physical = false;
    }
}
