package game.gameParts.cards.abilities.physical.playerAbilities;

import game.gameParts.cards.abilities.OffensiveAbility;
import game.gameParts.cards.abilities.physical.PhysicalOffensiveAbility;

public class Slash extends PhysicalOffensiveAbility {
    public Slash(int abilityLevel) {
        super(abilityLevel);
        this.name = "Slash";
    }

    @Override
    public int calculatePlayerDamage(int value) {
        return 4 * this.abilityLevel + value;
    }

    @Override
    public int calculateMonsterDamage() {
        return 0;
    }
}
