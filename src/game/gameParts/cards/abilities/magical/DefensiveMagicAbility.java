package game.gameParts.cards.abilities.magical;

import game.gameParts.cards.abilities.DefensiveAbility;

public abstract class DefensiveMagicAbility extends DefensiveAbility {
    public DefensiveMagicAbility(int abilityLevel) {
        super(abilityLevel);
        this.physical = false;
    }
}
