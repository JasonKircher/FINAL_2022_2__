package game.gameParts.cards.abilities.physical.playerAbilities;

import game.gameParts.cards.abilities.OffensiveAbility;
import game.gameParts.cards.abilities.physical.PhysicalOffensiveAbility;
import game.gameParts.cards.monsters.Monster;

public class Pierce extends PhysicalOffensiveAbility {
    public Pierce(int abilityLevel) {
        super(abilityLevel);
        this.name = "Pierce";
    }

    @Override
    public int calculateDamage(int value, Object target) {
        if (target instanceof Monster) {
            int defaultDmg = 7 * this.abilityLevel + value;
            return value < 6 ? defaultDmg : defaultDmg + 5 * this.abilityLevel;
        }
        return 0;
    }
}
