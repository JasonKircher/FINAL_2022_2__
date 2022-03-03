package game.gameParts.cards.abilities.physical;

import game.gameParts.cards.abilities.DefensiveAbility;

public abstract class PhysicalDefensiveAbility extends DefensiveAbility {
    public PhysicalDefensiveAbility(int abilityLevel) {
        super(abilityLevel);
        this.physical = true;
    }
}
