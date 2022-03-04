package game.gameParts.cards.abilities.physical.playerAbilities;

import game.gameParts.cards.abilities.OffensiveAbility;
import game.gameParts.cards.abilities.physical.PhysicalOffensiveAbility;
import game.gameParts.cards.monsters.Monster;

public class Slash extends PhysicalOffensiveAbility {
    public Slash(int abilityLevel) {
        super(abilityLevel);
        this.name = "Slash";
    }

    @Override
    public int calculateDamage(int value, Object target) {
        return 0;
    }
}
