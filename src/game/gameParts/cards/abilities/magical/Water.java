package game.gameParts.cards.abilities.magical;

import game.gameParts.cards.monsters.Monster;
import game.gameParts.cards.monsters.MonsterType;
import game.gameParts.player.Runa;

/**
 * resembles the ability "Water"
 * @author upvlx
 * @version 0.1
 */
public class Water extends OffensiveMagicAbility {
    /**
     * constructor
     * @param abilityLevel the ability level the ability is supposed to have
     */
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
