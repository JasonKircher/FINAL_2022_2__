package game.gameParts.cards.abilities.physical.monsterAbilities;

import game.gameParts.cards.abilities.physical.PhysicalOffensiveAbility;
import game.gameParts.player.Runa;

public class Claw extends PhysicalOffensiveAbility {
    public Claw(int abilityLevel) {
        super(abilityLevel);
        this.name = "Claw";
    }

    @Override
    public int calculateDamage(int value, Object target) {
        if (target instanceof Runa runa) {
            runa.deBuff();
            return this.abilityLevel * 6;
        }
        return 0;
    }
}
