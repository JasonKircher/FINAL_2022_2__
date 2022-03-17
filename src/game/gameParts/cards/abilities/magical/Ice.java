package game.gameParts.cards.abilities.magical;

import game.gameParts.cards.monsters.Monster;
import game.gameParts.cards.monsters.MonsterType;
import game.gameParts.player.Runa;

/**
 * class that resembles the Ability "Ice"
 * @author upvlx
 * @version 0.1
 */
public class Ice extends OffensiveMagicAbility {
    /**
     * constructor
     * @param abilityLevel the ability level the ability is supposed to have
     */
    public Ice(int abilityLevel) {
        super(abilityLevel);
        this.name = "Ice";
    }

    @Override
    public int calculateDamage(int value, Object target) {
        if (target instanceof Monster) {
            Monster monster = (Monster) target;
            int defaultDmg = (2 * this.abilityLevel) * value + 2;
            return monster.getType() == MonsterType.Water ? defaultDmg + 2 * this.abilityLevel : defaultDmg;
        }
        else if (target instanceof Runa) {
            return 10 * this.abilityLevel + 2;
        }
        return 0;
    }
}
