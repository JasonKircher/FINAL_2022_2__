package game.gameParts.cards.abilities.physical.playerAbilities;

import game.gameParts.cards.abilities.physical.PhysicalOffensiveAbility;
import game.gameParts.cards.monsters.Monster;

/**
 * resembles the ability "Thrust"
 * @author upvlx
 * @version 0.1
 */
public class Thrust extends PhysicalOffensiveAbility {
    /**
     * constructor
     * @param abilityLevel the ability level the ability is supposed to have
     */
    public Thrust(int abilityLevel) {
        super(abilityLevel);
        this.name = "Thrust";
    }

    @Override
    public int calculateDamage(int value, Object target) {
        if (target instanceof Monster) {
            int defaultDmg = 6 * this.abilityLevel + value;
            return value < 6 ? defaultDmg : defaultDmg + 4 * this.abilityLevel;
        }
        return 0;
    }
}
