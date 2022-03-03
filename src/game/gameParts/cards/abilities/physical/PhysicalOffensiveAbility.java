package game.gameParts.cards.abilities.physical;

import game.gameParts.cards.abilities.OffensiveAbility;

public abstract class PhysicalOffensiveAbility extends OffensiveAbility {
    public PhysicalOffensiveAbility(int abilityLevel) {
        super(abilityLevel);
        this.physical = true;
    }
}
