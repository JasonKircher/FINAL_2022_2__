package game.gameParts.cards.abilities.magical;

import game.gameParts.cards.monsters.Monster;
import game.gameParts.cards.monsters.MonsterType;
import game.gameParts.player.Runa;

/**
 * class that resembles the ability "Lightning"
 * @author upvlx
 * @version 0.1
 */
public class Lightning extends OffensiveMagicAbility {
    /**
     * constructor
     * @param abilityLevel the ability level the ability is supposed to have
     */
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
