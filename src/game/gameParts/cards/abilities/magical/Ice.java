package game.gameParts.cards.abilities.magical;

import game.gameParts.cards.monsters.Monster;
import game.gameParts.cards.monsters.MonsterType;
import game.gameParts.player.Runa;

public class Ice extends OffensiveMagicAbility {
    public Ice(int abilityLevel) {
        super(abilityLevel);
        this.name = "Ice";
    }

    @Override
    public int calculateDamage(int value, Object target) {
        if (target instanceof Monster monster) {
            int defaultDmg = (2 * this.abilityLevel) * value + 2;
            return monster.getType() == MonsterType.Water ? defaultDmg + 2 * this.abilityLevel : defaultDmg;
        }
        else if (target instanceof Runa) {
            return 10 * this.abilityLevel + 2;
        }
        return 0;
    }
}
