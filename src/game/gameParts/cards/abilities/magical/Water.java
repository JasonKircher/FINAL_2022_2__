package game.gameParts.cards.abilities.magical;

import game.gameParts.cards.monsters.Monster;
import game.gameParts.cards.monsters.MonsterType;
import game.gameParts.player.Runa;

public class Water extends OffensiveMagicAbility{
    public Water(int abilityLevel) {
        super(abilityLevel);
        this.name = "Water";
    }

    @Override
    public int calculateDamage(int value, Object target) {
        if (target instanceof Monster monster) {
            int defaultDmg = (2 * this.abilityLevel + 4) * value;
            return monster.getType() == MonsterType.Lightning ? defaultDmg + 2 * this.abilityLevel : defaultDmg;
        } else if (target instanceof Runa) {
            return 8 * this.abilityLevel + 2;
        }
        return 0;
    }

}
