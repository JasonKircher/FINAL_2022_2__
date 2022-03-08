package game.gameParts.cards.abilities.physical.monsterAbilities;

import game.gameParts.cards.abilities.physical.PhysicalOffensiveAbility;
import game.gameParts.player.Runa;

public class Scratch extends PhysicalOffensiveAbility {
    public Scratch(int abilityLevel) {
        super(abilityLevel);
        this.name = "Scratch";
    }

    @Override
    public int calculateDamage(int value, Object target) {
        if (target instanceof Runa runa) {
            runa.deBuff();
            return 5 * this.abilityLevel;
        }
        return 0;
    }
}
