package game.gameParts.cards.abilities.magical;

import game.gameParts.cards.monsters.Monster;
import game.gameParts.cards.monsters.MonsterType;
import game.gameParts.player.Runa;

public class Fire extends OffensiveMagicAbility {
    public Fire(int abilityLevel) {
        super(abilityLevel);
        this.name = "Fire";
    }

    @Override
    public int calculateDamage(int value, Object target) {
        if (target instanceof Runa) {
            return 12 * this.abilityLevel + 2;
        } else if (target instanceof Monster monster) {
            int defaultDmg = (2 * this.abilityLevel + 4) * value + 2;
            return monster.getType() == MonsterType.Ice ? defaultDmg + 2 * this.abilityLevel : defaultDmg;
        }
        return 0;
    }

}
