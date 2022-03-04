package game.gameParts.cards.abilities.magical;

import game.gameParts.cards.abilities.OffensiveAbility;

public abstract class OffensiveMagicAbility extends OffensiveAbility {
    public OffensiveMagicAbility(int abilityLevel) {
        super(abilityLevel);
        this.physical = false;
    }
}
