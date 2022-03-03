package game.gameParts.cards.abilities.physical.playerAbilities;

import game.gameParts.cards.abilities.OffensiveAbility;
import game.gameParts.cards.abilities.physical.PhysicalOffensiveAbility;

public class Thrust extends PhysicalOffensiveAbility {
    public Thrust(int abilityLevel) {
        super(abilityLevel);
        this.name = "Thrust";
    }

    @Override
    public int calculatePlayerDamage(int value) {
        return value >= 6 ? 10 * this.abilityLevel + value : 6 * this.abilityLevel + value;
    }

    @Override
    public int calculateMonsterDamage() {
        return 0;
    }
}
