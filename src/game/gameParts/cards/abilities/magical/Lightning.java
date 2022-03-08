package game.gameParts.cards.abilities.magical;

import game.gameParts.cards.monsters.Monster;
import game.gameParts.cards.monsters.MonsterType;
import game.gameParts.player.Runa;

public class Lightning extends OffensiveMagicAbility {
    public Lightning(int abilityLevel) {
        super(abilityLevel);
        this.name = "Lightning";
    }

    @Override
    public int calculateDamage(int value, Object target) {
        if (target instanceof Monster monster) {
            int defaultDmg = (2 * this.abilityLevel + 5) * value + 2;
            return monster.getType() == MonsterType.Fire ? defaultDmg + 2 * this.abilityLevel : defaultDmg;
        }
        else if (target instanceof Runa) {
            return 14 * this.abilityLevel + 2;
        }
        return 0;
    }
}
