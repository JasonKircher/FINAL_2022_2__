package game.gameParts.cards.abilities.physical.playerAbilities;

import game.gameParts.cards.abilities.physical.PhysicalOffensiveAbility;


public class Thrust extends PhysicalOffensiveAbility {
    public Thrust(int abilityLevel) {
        super(abilityLevel);
        this.name = "Thrust";
    }

    @Override
    public int calculateDamage(int value, Object target) {
        return 6 * this.abilityLevel;
    }
}
