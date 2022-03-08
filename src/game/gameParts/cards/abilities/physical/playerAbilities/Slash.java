package game.gameParts.cards.abilities.physical.playerAbilities;

import game.gameParts.cards.abilities.physical.PhysicalOffensiveAbility;
import game.gameParts.cards.monsters.Monster;

public class Slash extends PhysicalOffensiveAbility {
    public Slash(int abilityLevel) {
        super(abilityLevel);
        this.name = "Slash";
    }

    @Override
    public int calculateDamage(int value, Object target) {
        if (target instanceof Monster monster) {
            monster.deBuff();
            return 4 * this.abilityLevel + value;
        }
        return 0;
    }
}
