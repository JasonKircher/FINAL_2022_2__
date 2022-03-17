package game.gameParts.cards.abilities.magical;

import game.gameParts.cards.monsters.Monster;
import game.gameParts.cards.monsters.MonsterType;
import game.gameParts.player.Runa;

/**
 * class which resembles the ability fire
 * @author upvlx
 * @version 0.1
 */
public class Fire extends OffensiveMagicAbility {
    /**
     * constructor
     * @param abilityLevel the ability level the ability is supposed to have
     */
    public Fire(int abilityLevel) {
        super(abilityLevel);
        this.name = "Fire";
    }

    @Override
    public int calculateDamage(int value, Object target) {
        if (target instanceof Runa) {
            return 12 * this.abilityLevel + 2;
        } else if (target instanceof Monster) {
            Monster monster = (Monster) target;
            int defaultDmg = (2 * this.abilityLevel + 4) * value + 2;
            return monster.getType() == MonsterType.Ice ? defaultDmg + 2 * this.abilityLevel : defaultDmg;
        }
        return 0;
    }

}
