package game.gameParts.cards.abilities.physical.playerAbilities;

import game.gameParts.cards.abilities.OffensiveAbility;
import game.gameParts.cards.abilities.physical.PhysicalOffensiveAbility;

public class Swing extends PhysicalOffensiveAbility {
    public Swing(int abilityLevel) {
        super(abilityLevel);
        this.name = "Swing";
    }

    @Override
    public int calculatePlayerDamage(int value) {
        return 5 * this.abilityLevel + value;
    }

    @Override
    public int calculateMonsterDamage() {
        return 0;
    }
}
